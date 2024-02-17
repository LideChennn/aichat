package cn.edu.gdpu.aichat.util.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

//@Aspect
//@Component
public class MyAspect {

    @Pointcut("execution(public * cn.edu.gdpu.chatgpt.service.BookService.*(..))")
    public void pt(){}

    @Around("pt()")
    public Object around(ProceedingJoinPoint joinPoint){
        Object proceed = null;
        try {
            System.out.println("前置通知");
            proceed = joinPoint.proceed();
            System.out.println("返回后通知");
        } catch (Throwable e) {
            System.out.println("异常通知");
            throw new RuntimeException(e);
        }
        System.out.println("后置通知");
        return proceed;
    }

}

