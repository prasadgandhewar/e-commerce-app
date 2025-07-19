package com.ecomm.project.user_service.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggableAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggableAspect.class);

    @Autowired
    private LoggerLoki loggerLoki;

    @Pointcut("@annotation(com.ecomm.project.user_service.logging.Loggable) || @within(com.ecomm.project.user_service.logging.Loggable)")
    public void loggableMethods() {
    }

    @Before("loggableMethods()")
    public void logBefore(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = signature.getDeclaringType().getSimpleName();
        String methodName = signature.getName();

        logger.info("➡️ Entering {}.{}() with args = {}", className, methodName, Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "loggableMethods()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = signature.getDeclaringType().getSimpleName();
        String methodName = signature.getName();

        logger.info("✅ Exiting {}.{}() with result = {}", className, methodName, result);
    }


    @AfterThrowing(pointcut = "loggableMethods()", throwing = "ex")
    public void logException(JoinPoint joinPoint, Throwable ex) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = signature.getDeclaringType().getSimpleName();
        String methodName = signature.getName();

        loggerLoki.log(ex.getMessage());
        logger.error("❌ Exception in {}.{}(): {}", className, methodName, ex.getMessage(), ex);
    }
}
