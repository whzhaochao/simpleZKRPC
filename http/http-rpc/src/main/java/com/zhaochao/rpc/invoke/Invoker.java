package com.zhaochao.rpc.invoke;

import com.zhaochao.rpc.exception.RpcException;

import java.io.OutputStream;

/**
 * Created by version_z on 2015/8/22.
 */
public interface Invoker
{
	
	/**
	 * 
	 * @param request request message
	 * @param url consummer config
	 * @return
	 * @throws RpcException
	 */
    String request(String request,String url) throws RpcException;

 
    /**
     * 
     * @param response  response message 
     * @param outputStream
     * @throws RpcException
     */
    void response(String response,OutputStream outputStream) throws RpcException;
}
