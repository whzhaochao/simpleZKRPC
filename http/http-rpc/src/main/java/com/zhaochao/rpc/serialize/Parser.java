package com.zhaochao.rpc.serialize;

import com.zhaochao.rpc.exception.RpcException;

/**
 * Created on 2015/8/17.
 */
public interface Parser
{
    
	/**
	 * 
	 * @param param
	 * @return
	 * @throws RpcException
	 */
    Request reqParse(String param) throws RpcException;

    /**
     *
     * @param result
     * @return
     */
    public <T> T rsbParse(String result);
}
