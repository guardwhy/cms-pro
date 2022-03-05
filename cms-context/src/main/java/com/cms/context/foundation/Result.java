package com.cms.context.foundation;

import java.io.Serializable;

/**
 * @author guardwhy
 * @date 2022/3/5 11:47
 * 验证信息
 */
public class Result<T extends Serializable> implements Serializable{
    // 状态码
    private int restCode;
    // 状态信息
    private String restInfo;
    // 状态数据
    private T data;

    // 构造器
    public Result(int restCode) {
        this.restCode = restCode;
    }

    public Result(int restCode, String restInfo) {
        this.restCode = restCode;
        this.restInfo = restInfo;
    }

    public Result(int restCode, String restInfo, T data) {
        this.restCode = restCode;
        this.restInfo = restInfo;
        this.data = data;
    }

    public Result(int restCode, T data) {
        this.restCode = restCode;
        this.data = data;
    }

    public Result(String restInfo, T data) {
        this.restInfo = restInfo;
        this.data = data;
    }


    public int getRestCode() {
        return restCode;
    }

    public void setRestCode(int restCode) {
        this.restCode = restCode;
    }

    public String getRestInfo() {
        return restInfo;
    }

    public void setRestInfo(String restInfo) {
        this.restInfo = restInfo;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <W extends Serializable> Result<W> success() {
        return new Result<>(200, "成功");
    }

    public static <W extends Serializable> Result<W> success(W data) {
        return new Result<>(200, data);
    }

    public static <W extends Serializable> Result<W> success(String restInfo, W data) {
        return new Result<>(restInfo, data);
    }

    public static <W extends Serializable> Result<W> success(int code, String restInfo, W data) {
        return new Result<>(code, restInfo, data);
    }

    public static <W extends Serializable> Result<W> failed() {
        return new Result<>(500);
    }

    public static <W extends Serializable> Result<W> failed(String restInfo) {
        return new Result<>(500, restInfo);
    }

    public static <W extends Serializable> Result<W> failed(int code,String restInfo) {
        return new Result<>(code, restInfo);
    }
}
