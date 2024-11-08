package org.baas.baascore.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class SubscribeAop {

    @Pointcut("execution(* org.baas.controller..*.*(..))")
    public void validationApiKeys(JoinPoint joinPoint){

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request =requestAttributes.getRequest();

        String accessKey = request.getHeader("accessKey");
        String secretKey = request.getHeader("secretKey");
        if(!validateKeys(accessKey,secretKey))
            log.error("{}의 메서드의 accessKey, secretKey가 맞질 않습니다",method.getName());
            throw new RuntimeException("accessKey와 secretKey를 확인 해 주세요");
    }
}
