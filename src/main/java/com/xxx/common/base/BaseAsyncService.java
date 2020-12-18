package com.xxx.common.base;

/**
 * @author Xu Haidong
 * @date 2018/8/15
 */
public abstract class BaseAsyncService {

    public String getAddress() {
        String className = this.getClass().getName();
        return className.substring(0, className.lastIndexOf("Impl")).replace(".impl", "");
    }

    public Class getAsyncInterfaceClass() throws ClassNotFoundException {
        String className = this.getClass().getName();
        return Class.forName(className.substring(0, className.lastIndexOf("Impl")).replace(".impl", ""));
    }
}
