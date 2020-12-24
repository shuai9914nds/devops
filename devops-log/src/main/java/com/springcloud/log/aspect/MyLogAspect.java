package com.springcloud.log.aspect;

import com.log.api.LoginFeignApi;
import com.log.api.entity.SysLog;
import com.springcloud.log.annotation.MyLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import utils.JacksonUtil;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @author: liushuai
 * @date: 2020/12/16
 * @description：
 */
@Aspect
@Component
public class MyLogAspect {

    @Resource
    private LoginFeignApi loginFeignApi;

    //定义切点
    //在注解的位置切入代码
    @Pointcut("@annotation(com.springcloud.log.annotation.MyLog)")
    public void logPointCut() {
    }

    //切面 配置通知
    @AfterReturning("logPointCut()")
    public void insertSysLog(JoinPoint joinPoint) {
        //保存日志
        SysLog sysLog = new SysLog();

        //从切面织入点处通过反射机制织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (null != method) {
            String value = myLog.value();
            sysLog.setOperateContent(value);
        }

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        sysLog.setMethodName(methodName + "." + methodName);

        //请求的参数
        Object[] args = joinPoint.getArgs();
        //将参数转化为json
        String params = JacksonUtil.toJSon(args);
        sysLog.setParam(params);
        loginFeignApi.insertSysLog(sysLog);

    }


}
