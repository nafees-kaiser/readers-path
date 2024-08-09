package com.readerspath.backend.util;

import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

public final class Convertion {
    public static <K, T> T covertToView(K object, Class<T> view){
        return new SpelAwareProxyProjectionFactory().createProjection(view, object);
    }
}
