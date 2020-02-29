package com.changgou.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author DL_Wu
 * @Date 2020/2/29 11:41
 * @Version 1.0
 *
 *  @ControllerAdvice注解，全局捕获异常类，
 *  只要作用在@RequestMapping上，所有的异常都会被捕获
 */
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value =  Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return new Result(true, StatusCode.ERROR,e.getMessage());
    }

}
