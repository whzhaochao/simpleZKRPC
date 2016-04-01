package com.zhaochao.rpc.serialize.json;

import com.alibaba.fastjson.JSON;
import com.zhaochao.rpc.exception.RpcException;
import com.zhaochao.rpc.exception.RpcExceptionCodeEnum;
import com.zhaochao.rpc.serialize.Parser;
import com.zhaochao.rpc.serialize.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 2015/8/17.
 */
public class JsonParser implements Parser
{
    private static final Logger logger = LoggerFactory.getLogger(JsonParser.class);

    public static final Parser parser = new JsonParser();

    public Request reqParse(String param) throws RpcException {
        try
        {
            logger.debug("call param {}", param);
            return (Request)JSON.parse(param);
        }
        catch (Exception e)
        {
            logger.error("convert error param = {}", param, e);
            throw new RpcException("",e, RpcExceptionCodeEnum.DATA_PARSER_ERROR.getCode(),param);
        }
    }

    public <T> T rsbParse(String result)
    {
        return (T)JSON.parse(result);
    }
}
