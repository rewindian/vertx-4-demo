package com.xxx.web;

import com.xxx.common.annotaions.RouteHandler;
import com.xxx.common.annotaions.RouteMapping;
import com.xxx.common.annotaions.RouteMethod;
import com.xxx.common.base.BaseRestApi;
import com.xxx.common.model.JsonResult;
import com.xxx.common.util.AsyncServiceUtil;
import com.xxx.service.ProductService;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;


@RouteHandler(value = "restApi")
public class RestApi extends BaseRestApi {

    private ProductService productService = AsyncServiceUtil.getAsyncServiceInstance(ProductService.class);

    @RouteMapping(value = "/listProductById/:productId", method = RouteMethod.GET)
    public Handler<RoutingContext> listProductById() {
        return ctx -> {
            String productId = ctx.pathParam("productId");
            if (productId == null) {
                sendError(400, ctx);
            } else {
                productService.handleGetProduct(productId, ar -> {
                    if (ar.succeeded()) {
                        JsonObject product = ar.result();
                        fireJsonResponse(ctx, new JsonResult(product));
                    } else {
                        sendError(404, ctx);
                    }
                });
            }
        };
    }


}
