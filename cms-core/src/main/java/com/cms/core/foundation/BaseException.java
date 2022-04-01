package com.cms.core.foundation;

import java.text.MessageFormat;

/**
 * @author guardwhy
 * @date 2022/3/29 20:31
 */
public class BaseException extends RuntimeException{
    /**
     * 状态码
     */
    protected Integer code;
    /**
     * 错误信息
     */
    protected String msg;

    /**
     * 带有错误码和错误消息的构造函数
     * @param code          错误码
     * @param msg           错误信息
     */
    public BaseException(Integer code,String msg){
        /**
         * 屏蔽栈信息
         */
        super(msg,null, false, false);
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return  MessageFormat.format("{0}[{1}]",this.code,this.msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
