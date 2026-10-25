package com.exsited;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The wire key of a field whose Java name cannot follow the spelling rule in {@link Dto}.
 *
 * <p>A Java keyword ({@code default}) or a camelCase key the API sends ({@code primaryId}),
 * which the rule would otherwise read as {@code primary_id}.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface WireName {
    /** The key as the API spells it. */
    String value();
}
