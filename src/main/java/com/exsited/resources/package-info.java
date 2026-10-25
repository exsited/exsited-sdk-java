/**
 * The 91 v4 resource families, one subpackage each, over the paging block they all
 * share; a family may depend on {@code client}, {@code dto}, {@code errors} and
 * {@code http}, and on another family's {@code _dto} views only for a path both serve,
 * never on another family's class, which is why a type two families both need moves
 * up to here.
 */
package com.exsited.resources;
