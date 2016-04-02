package com.zhaochao.rpc.proxy;

import com.zhaochao.rpc.container.Container;
import com.zhaochao.rpc.container.HttpContainer;
import com.zhaochao.rpc.exception.RpcException;
import com.zhaochao.rpc.exception.RpcExceptionCodeEnum;
import com.zhaochao.rpc.invoke.HttpInvoker;
import com.zhaochao.rpc.invoke.Invoker;
import com.zhaochao.rpc.invoke.ProviderConfig;
import com.zhaochao.rpc.serialize.Formater;
import com.zhaochao.rpc.serialize.Parser;
import com.zhaochao.rpc.serialize.Request;
import com.zhaochao.rpc.serialize.json.JsonFormater;
import com.zhaochao.rpc.serialize.json.JsonParser;
import org.mortbay.jetty.handler.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by version_z on 2015/8/22.
 */
public class ProviderProxyFactory extends AbstractHandler
{
    private static final Logger logger = LoggerFactory.getLogger(ProviderProxyFactory.class);

    private Map<Class,Object> providers = new ConcurrentHashMap<Class, Object>();

    private static ProviderProxyFactory factory;

    private Parser parser = JsonParser.parser;

    private Formater formater = JsonFormater.formater;

    private Invoker invoker = HttpInvoker.invoker;

    public ProviderProxyFactory(Map<Class,Object> providers)
    {
        if (Container.container == null)
        {
            new HttpContainer(this).start();
        }
        for (Map.Entry<Class,Object> entry: providers.entrySet())
        {
            register(entry.getKey(), entry.getValue(),null);
        }
        factory = this;
    }

    public ProviderProxyFactory(Map<Class,Object> providers,ProviderConfig providerConfig)
    {
        if (Container.container == null)
        {
            new HttpContainer(this,providerConfig).start();
        }
        for (Map.Entry<Class,Object> entry: providers.entrySet())
        {
            register(entry.getKey(), entry.getValue(),providerConfig);
        }
        factory = this;
    }

    public void register(Class clazz,Object object,ProviderConfig config)
    {
        providers.put(clazz, object);
        if (config != null){
            config.register(clazz);
        }
        logger.info("{} register service success!", clazz.getSimpleName());
    }


    public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch) throws IOException, ServletException
    {
        String reqStr = request.getParameter("data");
        try
        {

            Request rpcRequest = parser.reqParse(reqStr);
            Object result = rpcRequest.invoke(ProviderProxyFactory.getInstance().getBeanByClass(rpcRequest.getClazz()));
            invoker.response(formater.rsbFormat(result),response.getOutputStream());
        }
        catch (RpcException e)
        {
            e.printStackTrace();
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
    }






    public Object getBeanByClass(Class clazz) throws RpcException
    {
        Object bean =  providers.get(clazz);
        if (bean != null)
        {
            return bean;
        }
        throw new RpcException(RpcExceptionCodeEnum.NO_BEAN_FOUND.getCode(),clazz);
    }

    public static ProviderProxyFactory getInstance()
    {
        return factory;
    }




}
