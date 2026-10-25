package com.exsited.auth;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/** Keeps the token for the life of the process. */
public final class MemoryTokenStore implements TokenStore {

    private final Map<String, TokenRecord> records = new ConcurrentHashMap<>();
    private final Object lock = new Object();

    @Override
    public TokenRecord read(String namespace) {
        return records.get(namespace);
    }

    @Override
    public void write(String namespace, TokenRecord record) {
        records.put(namespace, record);
    }

    @Override
    public void clear(String namespace) {
        records.remove(namespace);
    }

    @Override
    public <T> T withLock(String namespace, Supplier<T> work) {
        // One process, so a monitor is the whole of it.
        synchronized (lock) {
            return work.get();
        }
    }
}
