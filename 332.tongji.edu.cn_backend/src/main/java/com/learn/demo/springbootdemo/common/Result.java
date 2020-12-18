package com.learn.demo.springbootdemo.common;

/**
 * @author chenbin
 * @description: 工具类
 * @version: V1.0
 */
public class Result {

    private boolean flag;
    private Integer code;
    private String message;
    private Object data;

    public Result(boolean flag, Integer code, String message, Object data) {
        super();
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(boolean flag, Integer code, String message) {
        super();
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public static Result SUCCESS(String message, Object data) {
        return new Result(true, StatusCode.OK, message, data);
    }

    public static Result SUCCESS(String message) {
        return new Result(true, StatusCode.OK, message, null);
    }
    public static Result ERROR(String message) {
        return new Result(false, StatusCode.OK, message, null);
    }

    public static Result ERROR(Integer code, String message) {
        return new Result(false, code, message, null);
    }

    public static Result ERROR(Integer code, String message, Object data) {
        return new Result(false, code, message, data);
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
