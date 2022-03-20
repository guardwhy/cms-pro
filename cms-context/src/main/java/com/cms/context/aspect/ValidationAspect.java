package com.cms.context.aspect;

import com.cms.context.foundation.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

/**
 * @author guardwhy
 * @date 2022/3/20 17:32
 * AOP切入编程
 */
@Component
@Aspect
public class ValidationAspect {
    /**
     * 切入点
     */
    @Pointcut("@annotation(com.cms.core.annotation.DoValid)")
    public void pointcut(){

    }

    /***
     * 环绕通知
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        for (Object arg: point.getArgs()){
            if(arg instanceof BeanPropertyBindingResult){
                // 进行验证
                BindingResult bindingResult = (BindingResult) arg;
                if(bindingResult.hasErrors()){
                     return Result.failed(bindingResult.getFieldError().getDefaultMessage());
                }
            }
        }
        // 返回结果值
        return point.proceed();
    }
}
