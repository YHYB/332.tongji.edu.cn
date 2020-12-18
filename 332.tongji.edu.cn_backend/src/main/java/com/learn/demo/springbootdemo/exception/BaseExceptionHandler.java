package com.learn.demo.springbootdemo.exception;
import com.learn.demo.springbootdemo.common.Result;
import com.learn.demo.springbootdemo.common.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @description: [comment]统一异常处理类
 * @author chenbin
 * @version: V1.0
 *
 */
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exception(Exception e){
        e.printStackTrace();
        if(e instanceof BusinessException){
            return Result.ERROR(e.getMessage());
        }
        return Result.ERROR("执行出错");
    }

}
