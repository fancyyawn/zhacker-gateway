package top.zhacker.gateway.passport.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zlf
 * 2017-03-18 12:31
 */
@Aspect
@Component
public class HttpLogAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpLogAspect.class);


    @Pointcut("execution(public * top.zhacker.gateway.passport.controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //url method ip
        logger.info("http request url={}，method={}，ip={}", request.getRequestURL(), request.getMethod(), request.getRemoteAddr());

        //类方法 参数
        logger.info("class_method={}, args={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @After("log()")
    public void doAfter() {
       // logger.info("利用AOP记录每次请求完成后的信息");
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        logger.info("http response={}", object.toString());
    }

}
