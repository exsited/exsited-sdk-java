package com.exsited.client;

import com.exsited.Dto;
import com.exsited.errors.SdkError;
import com.exsited.errors.SdkUsageError;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/** A lazy walk of a paginated v4 listing, in either the cursor or the offset shape. */
public final class Pagination {

    private Pagination() {}

    /** Set by the pager from each page, so a caller's own is refused. */
    public static final String CURSOR_PARAM = "cursor";
    public static final String OFFSET_PARAM = "offset";
    public static final String LIMIT_PARAM = "limit";

    /** The v4 envelope key holding a page's records. */
    public static final String DEFAULT_RECORD_KEY = "data";

    /** The offset shape's end marker: the literal string, not a null. */
    public static final String NO_NEXT_PAGE = "NULL";

    /** One {@link ApiResponse} per page; {@code queryParams} go with every request. */
    public static Iterable<ApiResponse<Dto>> iterPages(
            ExsitedClient client, String path, Map<String, Object> queryParams) {
        return iterPages(client, path, queryParams, null, true);
    }

    /** As above, capped at {@code maxPages}; a value below 1 is refused. */
    public static Iterable<ApiResponse<Dto>> iterPages(
            ExsitedClient client, String path, Map<String, Object> queryParams,
            Integer maxPages, boolean raiseOnError) {
        return pages(client, path, queryParams, maxPages, raiseOnError);
    }

    /**
     * One {@link ApiResponse} per page, capped at {@code maxPages}, raising as the config says.
     *
     * @deprecated use {@link #iterPages(ExsitedClient, String, Map, Integer, boolean)}
     */
    @Deprecated
    public static Iterable<ApiResponse<Dto>> iterPages(
            ExsitedClient client, String path, Map<String, Object> queryParams, Integer maxPages) {
        return pages(client, path, queryParams, maxPages, null);
    }

    /** Each record of a listing across every page, the envelope stripped. */
    public static Iterable<Map<String, Object>> iterRecords(
            ExsitedClient client, String path, String recordKey,
            Map<String, Object> queryParams) {
        return iterRecords(client, path, recordKey, queryParams, null);
    }

    /** As above, capped at {@code maxPages}; a value below 1 is refused. */
    @SuppressWarnings("unchecked")
    public static Iterable<Map<String, Object>> iterRecords(
            ExsitedClient client, String path, String recordKey,
            Map<String, Object> queryParams, Integer maxPages) {
        Iterable<Object> records = records(client, path, recordKey, queryParams, maxPages, true, true);
        return () -> (Iterator<Map<String, Object>>) (Iterator<?>) records.iterator();
    }

    /**
     * Every record of a listing, fetched before this returns.
     *
     * @deprecated use {@link #iterRecords(ExsitedClient, String, String, Map, Integer)}
     */
    @Deprecated
    public static List<Object> iterRecords(
            ExsitedClient client, String path, Map<String, Object> queryParams,
            String recordKey, Integer maxPages) {
        List<Object> out = new ArrayList<>();
        for (Object record : records(client, path, recordKey, queryParams, maxPages, null, false)) {
            out.add(record);
        }
        return out;
    }

    private static Iterable<ApiResponse<Dto>> pages(
            ExsitedClient client, String path, Map<String, Object> queryParams,
            Integer maxPages, Boolean raiseOnError) {
        Map<String, Object> base = queryParams == null ? Map.of() : new LinkedHashMap<>(queryParams);
        for (String owned : List.of(CURSOR_PARAM, OFFSET_PARAM)) {
            if (base.containsKey(owned)) {
                throw new SdkUsageError("queryParams: \"" + owned
                        + "\" is owned by the pager, which sets it from each page; to resume "
                        + "from a saved position call the endpoint directly with it");
            }
        }
        if (maxPages != null && maxPages < 1) {
            throw new SdkUsageError("maxPages: expected a positive integer or null, got " + maxPages);
        }
        return () -> new PageIterator(client, path, base, maxPages, raiseOnError);
    }

    private static Iterable<Object> records(
            ExsitedClient client, String path, String recordKey, Map<String, Object> queryParams,
            Integer maxPages, Boolean raiseOnError, boolean objectsOnly) {
        if (recordKey == null || recordKey.isBlank()) {
            throw new SdkUsageError(
                    "recordKey: expected the envelope key holding the records, like \""
                            + DEFAULT_RECORD_KEY + "\"");
        }
        Iterable<ApiResponse<Dto>> walk = pages(client, path, queryParams, maxPages, raiseOnError);
        return () -> new RecordIterator(walk.iterator(), recordKey, objectsOnly);
    }

    /**
     * The cursor for the next page, or null when this is not the cursor shape or it has ended.
     *
     * <p>{@code has_more} wins: a last page can carry a stale {@code next_cursor}.
     */
    public static String readNextCursor(Map<String, Object> block) {
        if (block.containsKey("has_more") && !Boolean.TRUE.equals(block.get("has_more"))) {
            return null;
        }
        Object cursor = block.get("next_cursor");
        return cursor instanceof String text && !text.isBlank() ? text : null;
    }

    /**
     * The offset for the next page, or null at the end.
     *
     * <p>Computed, never read from {@code next_page}, whose link can point at another path.
     */
    public static Long readNextOffset(Map<String, Object> block, int recordsOnPage) {
        if (!block.containsKey("next_page")) {
            return null;
        }
        Object next = block.get("next_page");
        if (next == null) {
            return null;
        }
        if (next instanceof String text
                && (text.isBlank() || text.strip().toUpperCase(Locale.ROOT).equals(NO_NEXT_PAGE))) {
            return null;
        }
        long current = block.get(OFFSET_PARAM) instanceof Number number ? number.longValue() : 0L;
        long step = block.get(LIMIT_PARAM) instanceof Number number && number.longValue() > 0
                ? number.longValue()
                : recordsOnPage;
        return step <= 0 ? null : current + step;
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> paginationOf(Object body) {
        if (!(body instanceof Map<?, ?> map)) {
            return null;
        }
        Object block = map.get("pagination");
        return block instanceof Map ? (Map<String, Object>) block : null;
    }

    private static final class PageIterator implements Iterator<ApiResponse<Dto>> {
        private final ExsitedClient client;
        private final String path;
        private final Map<String, Object> base;
        private final Integer maxPages;
        private final Boolean raiseOnError;
        private final Set<String> seenCursors = new LinkedHashSet<>();

        private Map<String, Object> nextParams;
        private int pagesYielded;
        private boolean finished;

        PageIterator(ExsitedClient client, String path, Map<String, Object> base,
                Integer maxPages, Boolean raiseOnError) {
            this.client = client;
            this.path = path;
            this.base = base;
            this.maxPages = maxPages;
            this.raiseOnError = raiseOnError;
            this.nextParams = new LinkedHashMap<>(base);
        }

        @Override
        public boolean hasNext() {
            return !finished && (maxPages == null || pagesYielded < maxPages);
        }

        @Override
        public ApiResponse<Dto> next() {
            if (!hasNext()) {
                throw new NoSuchElementException("the listing is exhausted");
            }
            ApiResponse<Dto> response = client.sendGetRequest(path, RequestOptions.builder()
                    .queryParams(nextParams).raiseOnError(raiseOnError).build());
            pagesYielded++;

            if (!response.succeeded()) {
                // Only with raiseOnError off: yielded for inspection, with no next page to trust.
                finished = true;
                return response;
            }
            Map<String, Object> block = paginationOf(response.getMap());
            if (block == null) {
                finished = true;
                return response;
            }

            String cursor = readNextCursor(block);
            if (cursor != null) {
                if (!seenCursors.add(cursor)) {
                    throw new SdkError(
                            "pagination loop: the API handed back the same next_cursor a second "
                                    + "time, so following it again would never terminate; the "
                                    + "pages already yielded are good");
                }
                nextParams = new LinkedHashMap<>(base);
                nextParams.put(CURSOR_PARAM, cursor);
                return response;
            }

            int onPage = 0;
            if (response.getMap() instanceof Map<?, ?> map
                    && map.get(DEFAULT_RECORD_KEY) instanceof List<?> list) {
                onPage = list.size();
            }
            Long offset = readNextOffset(block, onPage);
            if (offset == null) {
                finished = true;
                return response;
            }
            nextParams = new LinkedHashMap<>(base);
            nextParams.put(OFFSET_PARAM, offset);
            return response;
        }
    }

    private static final class RecordIterator implements Iterator<Object> {
        private final Iterator<ApiResponse<Dto>> pages;
        private final String recordKey;
        private final boolean objectsOnly;
        private Iterator<?> currentPage = Collections.emptyIterator();
        private int pagesRead;

        RecordIterator(Iterator<ApiResponse<Dto>> pages, String recordKey, boolean objectsOnly) {
            this.pages = pages;
            this.recordKey = recordKey;
            this.objectsOnly = objectsOnly;
        }

        @Override
        public boolean hasNext() {
            while (!currentPage.hasNext()) {
                if (!pages.hasNext()) {
                    return false;
                }
                ApiResponse<Dto> page = pages.next();
                // A failed page raises as itself, never as a complaint about recordKey.
                page.raiseForStatus();
                boolean firstPage = pagesRead++ == 0;
                currentPage = readRecords(page.getMap(), recordKey, firstPage, objectsOnly).iterator();
            }
            return true;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException("the listing is exhausted");
            }
            return currentPage.next();
        }
    }

    /** One page's records; a later page with no list under the key ends the records. */
    private static List<?> readRecords(
            Object body, String recordKey, boolean firstPage, boolean objectsOnly) {
        Object found = body instanceof Map<?, ?> map ? map.get(recordKey) : null;
        if (found instanceof List<?> list) {
            if (objectsOnly) {
                requireRecordObjects(list, recordKey);
            }
            return list;
        }
        if (!firstPage) {
            return List.of();
        }
        String available = body instanceof Map<?, ?> map
                ? String.join(", ", map.keySet().stream().map(String::valueOf).sorted().toList())
                : "a " + (body == null ? "null" : body.getClass().getSimpleName()) + " body";
        throw new SdkUsageError("recordKey \"" + recordKey
                + "\": this page has no list under that key; it carries " + available);
    }

    /** Refused before the unchecked cast hands the list on as records. */
    private static void requireRecordObjects(List<?> records, String recordKey) {
        int position = 0;
        for (Object record : records) {
            if (record != null && !(record instanceof Map<?, ?>)) {
                throw new SdkUsageError("recordKey \"" + recordKey + "\": element "
                        + position + " of the list under that key is a "
                        + record.getClass().getSimpleName() + " rather than a record "
                        + "object; iterPages walks a listing whose entries are not "
                        + "objects, iterRecords cannot");
            }
            position++;
        }
    }
}
