package com.xxx.common.base;

import com.xxx.common.model.JsonResult;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

/**
 * @author Ian
 * @date 2020/12/17 17:30
 */
public abstract class BaseRestApi {

    protected static void fireJsonResponse(RoutingContext ctx, JsonResult jsonResult) {
        JsonObject jsonObject = JsonObject.mapFrom(jsonResult);
        ctx.response().putHeader("content-type", "application/json; charset=utf-8").setStatusCode(200).end(jsonObject.encode());
    }

    protected static void fireTextResponse(RoutingContext ctx, String text) {
        ctx.response().putHeader("content-type", "text/html; charset=utf-8").end(text);
    }

    protected static void sendError(int statusCode, RoutingContext ctx) {
        ctx.response().setStatusCode(statusCode).end();
    }
}
