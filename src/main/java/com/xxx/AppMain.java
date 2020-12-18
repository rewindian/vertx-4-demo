package com.xxx;

import com.xxx.common.handlerfactory.RouterHandlerFactory;
import com.xxx.common.util.DeployVertxServer;
import com.xxx.common.util.VertxUtil;
import io.vertx.config.ConfigRetriever;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 程序入口
 */
public class AppMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppMain.class);

    public static void main(String[] args) {
        Vertx tempVertx = Vertx.vertx();
        ConfigRetriever retriever = ConfigRetriever.create(tempVertx);
        //获取conf/config.json中的配置
        retriever.getConfig(ar -> {
            tempVertx.close();
            JsonObject result = ar.result();
            LOGGER.info("配置读取成功：" + result.encode());
            JsonObject serverConfig = result.getJsonObject("server");
            JsonObject vertxConfig = result.getJsonObject("vertx");
            JsonObject customConfig = result.getJsonObject("custom");
            Vertx vertx = Vertx.vertx(new VertxOptions(vertxConfig));
            VertxUtil.init(vertx);
            Router router = new RouterHandlerFactory(customConfig.getString("routerLocations"), serverConfig.getString("contextPath")).createRouter();
            DeployVertxServer.startDeploy(router, customConfig.getString("handlerLocations"), serverConfig.getInteger("port"),
                    customConfig.getInteger("asyncServiceInstances"));
        });

    }
}