package com.cms.context.advice;


import com.cms.context.constant.ConstantsPool;
import com.cms.context.foundation.Result;
import com.cms.context.utils.UtilsHttp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @author guardwhy
 * @date 2022/3/28 23:52
 * 统一异常处理
 */
@ControllerAdvice
@Slf4j
public class UnificationExceptionHandler {
    /***
     * 方法参数异常处理
     * @param exception
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Result<String> constraintViolationExceptionHandler(ConstraintViolationException exception){
        // 获取到其信息
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        // 遍历操作
        for(ConstraintViolation<?> item:constraintViolations){
            log.info(item.getPropertyPath().toString()+item.getMessage());
            return UtilsHttp.responseExceptionHandler(item.getMessage(),"/error.do");
        }
        return UtilsHttp.responseExceptionHandler(ConstantsPool.EXCEPTION_NETWORK_ERROR, "/error.do");
    }
}
