package com.zhaochao.rpc.serialize;

/**
 * Created on 2015/8/17.
 */
public interface Formater
{

	/**
	 * 
	 * @param clazz
	 * @param method
	 * @param param
	 * @return
	 */
    String reqFormat(Class clazz,String method,Object param);

    
    /**
     * 
     * @param param
     * @return
     */
    String rsbFormat(Object param);
}
