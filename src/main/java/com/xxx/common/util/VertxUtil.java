package com.xxx.common.util;

import io.vertx.core.Vertx;

import java.util.Objects;

/**
 * 保存vertx实例
 *
 * @author Ian
 * @date 2020/12/17
 */
public final class VertxUtil {

    private static Vertx singletonVertx;

    private VertxUtil() {

    }

    public static void init(Vertx vertx) {
        Objects.requireNonNull(vertx, "未初始化Vertx");
        singletonVertx = vertx;
    }

    public static Vertx getVertxInstance() {
        Objects.requireNonNull(singletonVertx, "未初始化Vertx");
        return singletonVertx;
    }
}
