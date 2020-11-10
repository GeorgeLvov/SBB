package com.tsystems.javaschool.SBB.utils;


import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

public class CollectionUtils {

    public static <E> E getFirst(final Collection<E> collection) {
        if (isEmpty(collection)) {
            throw new NoSuchElementException();
        }
        return collection.iterator().next();
    }

    public static <E> E getLast(final List<E> list) {
        if (isEmpty(list)) {
            throw new NoSuchElementException();
        }
      return list.listIterator(list.size()).previous();
    }

    public static <E> E removeLast(final List<E> list) {
        return isNotEmpty(list) ? list.remove(list.size() - 1) : null;
    }

    public static boolean isEmpty(final Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(final Collection<?> collection) {
        return !isEmpty(collection);
    }
}
