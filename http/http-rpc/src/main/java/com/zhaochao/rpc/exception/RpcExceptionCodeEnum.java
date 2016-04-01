package com.zhaochao.rpc.exception;

/**
 * Created on 2015/8/18.
 */
public enum RpcExceptionCodeEnum
{
    DATA_PARSER_ERROR("DATA_PARSER_ERROR","DATA_PARSER_ERROR"),
    NO_BEAN_FOUND("NO_BEAN_FOUND","NO_BEAN_FOUND"),
    INVOKE_REQUEST_ERROR("INVOKE_REQUEST_ERROR","INVOKE_REQUEST_ERROR"),
    NO_PROVIDERS("NO_PROVIDERS","NO_PROVIDERS"),
    ;

    private String code;
    private String msg;

    RpcExceptionCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
