package com.sd.sdzx.common.exception;

import com.sd.sdzx.model.vo.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.build(null , 201,"出现了异常") ;
    }
    @ExceptionHandler(value = GuiguException.class)     // 处理自定义异常
    @ResponseBody
    public Result error(GuiguException exception) {
        exception.printStackTrace();
        return Result.build(null , exception.getResultCodeEnum()) ;
    }
}