package com.xxx.service;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

/**
 * @author Ian
 * @date 2020/12/17 16:56
 */
@ProxyGen
public interface ProductService {

    void handleGetProduct(String productId, Handler<AsyncResult<JsonObject>> resultHandler);
}
