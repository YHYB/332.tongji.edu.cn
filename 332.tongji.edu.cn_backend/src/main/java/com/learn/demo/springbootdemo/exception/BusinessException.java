package com.learn.demo.springbootdemo.exception;

/**
 *
 * @description: 自定义异常
 * @author chenbin
 * @version: V1.0
 *
 */
public class BusinessException extends Exception {

    public BusinessException(){
        super();
    }
    public BusinessException(String message){
        super(message);
    }
}
