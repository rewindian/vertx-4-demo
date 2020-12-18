package com.xxx.service.impl;

import com.xxx.common.base.BaseAsyncService;
import com.xxx.service.ProductService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ian
 * @date 2020/12/17 17:03
 */
public class ProductServiceImpl extends BaseAsyncService implements ProductService {

    private Map<String, JsonObject> products = new HashMap<>();

    {
        this.setUpInitialData();
    }

    @Override
    public void handleGetProduct(String productId, Handler<AsyncResult<JsonObject>> resultHandler) {
        resultHandler.handle(Future.succeededFuture(products.get(productId)));
    }

    private void addProduct(JsonObject product) {
        products.put(product.getString("id"), product);
    }

    private void setUpInitialData() {
        addProduct(new JsonObject().put("id", "prod3568").put("name", "Egg Whisk").put("price", 3.99).put("weight", 150));
        addProduct(new JsonObject().put("id", "prod7340").put("name", "Tea Cosy").put("price", 5.99).put("weight", 100));
        addProduct(new JsonObject().put("id", "prod8643").put("name", "Spatula").put("price", 1.00).put("weight", 80));
    }
}
