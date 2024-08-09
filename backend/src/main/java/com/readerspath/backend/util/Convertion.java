package com.readerspath.backend.util;

import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.util.List;

public final class Convertion {
    public static <K, T> T covertToView(K object, Class<T> view) {
        return new SpelAwareProxyProjectionFactory().createProjection(view, object);
    }

    public static <K, T> List<T> convertToViewList(List<K> objects, Class<T> view) {
        return objects.stream()
                .map(object -> new SpelAwareProxyProjectionFactory().createProjection(view, object))
                .toList();
    }
}
