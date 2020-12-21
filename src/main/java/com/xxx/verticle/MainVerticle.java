package com.xxx.verticle;

import com.xxx.common.handlerfactory.RouterHandlerFactory;
import com.xxx.common.util.DeployVertxServer;
import com.xxx.common.util.VertxUtil;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;

/**
 * fat-jar打包方式使用此入口
 *
 * @author Ian
 * @date 2020/12/18 14:59
 */
public class MainVerticle extends AbstractVerticle {

    @Override
    public void start() {
        VertxUtil.init(vertx);
        Router router = new RouterHandlerFactory("com.xxx.web").createRouter();
        DeployVertxServer.startDeploy(router, "com.xxx.service", 8088, 1, "dev");
    }
}
