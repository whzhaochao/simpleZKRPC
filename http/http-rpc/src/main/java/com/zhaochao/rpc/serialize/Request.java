package com.zhaochao.rpc.serialize;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

/**
 * Created on 2015/8/17.
 */
public class Request implements Serializable
{
    private Class clazz;

    private String method;

    private Object param;

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public Object invoke(Object bean) throws Exception
    {
        return clazz.getMethod(method, param.getClass()).invoke(bean,param);
    }
}
