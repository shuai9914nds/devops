package com.devops.base.aspect;

import com.devops.base.annotation.MyLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * @author: liushuai
 * @date: 2020/12/16
 * @description：
 */
@Aspect
@Component
@Slf4j
public class MyLogAspect implements Ordered {

//    @Resource
//    private RestTemplate restTemplate;


    //定义切点
    //在注解的位置切入代码
    @Pointcut("@annotation(com.devops.base.annotation.MyLog)")
    public void logPointCut() {
    }


    //切面 配置通知
    @Before("logPointCut()")
    public void insertSysLog() {
        System.out.println("走到注解了");
        log.error("！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
    }

    @AfterReturning("logPointCut()")
    public String insertSyslog(JoinPoint joinPoint) {
        log.info("=====================开始执行后置通知==================");
        try {
            String targetName = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] arguments = joinPoint.getArgs();
            Class<?> targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();
            String operation = "";
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class<?>[] clazzs = method.getParameterTypes();
                    if (clazzs.length == arguments.length) {
                        operation = Optional.ofNullable(method.getAnnotation(MyLog.class)).map(MyLog::operation).orElse("");// 操作人
                        break;
                    }
                }
            }
            StringBuilder paramsBuf = new StringBuilder();
            for (Object arg : arguments) {
                paramsBuf.append(arg);
                paramsBuf.append("&");
            }

            // *========控制台输出=========*//
            log.info("[X用户]执行了[" + operation + "],类:" + targetName + ",方法名：" + methodName + ",参数:"
                    + paramsBuf.toString());
            log.info("=====================执行后置通知结束==================");
            String url = "http://localhost:8003/log/log";
//            SysLog sysLog = new SysLog();
//            sysLog.setMethodName(methodName);
//            sysLog.setOperateContent(operation);
//            sysLog.setOperateType(101);
//            sysLog.setParam(paramsBuf.toString());
            //将日志插入数据库
//            restTemplate.put(url, sysLog);
        } catch (Throwable e) {
            log.info("around " + joinPoint + " with com.devops.base.exception : " + e.getMessage());
        }
        return "";
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
