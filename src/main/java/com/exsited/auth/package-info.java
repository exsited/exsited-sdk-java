/**
 * Getting an access token, refreshing it and caching it in memory or on disk behind a
 * cross-process lock; it may depend on {@code config}, {@code http}, {@code json},
 * {@code errors} and {@code Redaction}, and is the only package here that writes a file
 * a second process can read.
 */
package com.exsited.auth;
