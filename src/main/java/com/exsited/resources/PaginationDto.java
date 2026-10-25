package com.exsited.resources;

import com.exsited.Dto;

/**
 * The paging block, in any of its three shapes.
 *
 * <p>One class covers them all, because a single response only ever fills one of them
 * and the others read null: cursor ({@link #has_more} present), offset
 * ({@link #records} present) or the external database listings' ({@link #has_next}
 * present).
 *
 * <p>Field names are the wire names, so they are snake_case rather than camelCase.
 */
public final class PaginationDto extends Dto {

    /** The field the cursor and offset shapes both carry. */
    public Long limit;

    /** Authoritative over {@link #next_cursor}, which can be stale on the last page. */
    public Boolean has_more;
    public String next_cursor;
    public String prev_cursor;
    public PaginationLinksDto links;

    /** Total matching records, not the page's length. */
    public Long records;
    public Long offset;
    /**
     * <b>The literal string {@code "NULL"} at the end of a listing, not null.</b> Compare
     * against the string; a truthiness test follows {@code "NULL"} as though it were a link.
     */
    public String next_page;
    /**
     * <b>{@code ""} or null, and never a URL</b>, even on a page that has a previous one. There
     * is no backward paging on this shape.
     */
    public String previous_page;

    /** The external database listings page with these three instead. */
    public Boolean has_next;
    public Boolean has_prev;
    public Long per_page;
}
