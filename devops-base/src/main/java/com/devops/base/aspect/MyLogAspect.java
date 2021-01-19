package com.devops.base.aspect;

import com.devops.base.annotation.MyLog;
import com.devops.base.common.Constant;
import com.devops.base.common.Result;
import com.devops.base.entity.SysLog;
import com.devops.base.enums.LogEnum;
import com.devops.base.enums.UserStateEnum;
import com.devops.base.utils.BeanConverter;
import com.devops.base.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;
import java.util.Map;
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


    //定义切点
    //在注解的位置切入代码
    @Pointcut("@annotation(com.devops.base.annotation.MyLog)")
    public void logPointCut() {
    }


    //切面 配置通知
    @Before("logPointCut()")
    public void insertSysLog() {
        System.out.println("走到注解了");
    }

    @AfterReturning(returning = "object", pointcut = "logPointCut()")
    public void insertSyslog(JoinPoint joinPoint, Object object) {
        Result result = (Result) object;
        if (!result.getSuccess()) {
            return;
        }
        String token = JWTUtil.getToken();
        log.info("=====================开始执行后置通知==================");
        try {
            String targetName = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] arguments = joinPoint.getArgs();
            Class<?> targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();
            String operation = "";
            int type = 0;
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class<?>[] clazzs = method.getParameterTypes();
                    if (clazzs.length == arguments.length) {
                        operation = Optional.ofNullable(method.getAnnotation(MyLog.class)).map(MyLog::operation).orElse("");// 操作人
                        type = Optional.ofNullable(method.getAnnotation(MyLog.class)).map(MyLog::type).orElse(0);// 操作人
                        break;
                    }
                }
            }
            StringBuilder paramsBuf = new StringBuilder();

            RestTemplate restTemplate = new RestTemplate();
            // *========控制台输出=========*//
            String url = "http://localhost:8007/log/log";
            SysLog sysLog = new SysLog();
            sysLog.setMethodName(methodName);
            for (Object arg : arguments) {
                paramsBuf.append(arg);
                paramsBuf.append("&");
            }
            String params = paramsBuf.toString();
            if (StringUtils.isBlank(token)) {
                if (LogEnum.LOGIN.getCode() == type) {
                    Map<String, Object> map = BeanConverter.Obj2Map(arguments[0]);
                    sysLog.setUsername(map.get("username").toString());
                    sysLog.setOperateType(type);
                    sysLog.setOperateContent(operation);
                }
            } else {
                String userName = JWTUtil.getUserName(token);
                Integer uid = JWTUtil.getUid(token);
                sysLog.setUid(uid);
                sysLog.setUsername(userName);
                if (Constant.UPADTE_USER_STATE == type) {
                    Map<String, Object> map = BeanConverter.Obj2Map(arguments[0]);
                    //修改用户状态
                    if (map.get("state").equals(UserStateEnum.DISABLE.getCode())) {
                        sysLog.setOperateType(LogEnum.USER_LOCK.getCode());
                        sysLog.setOperateContent("锁定用户");
                    }
                    if (map.get("state").equals(UserStateEnum.LOCKED.getCode())) {
                        sysLog.setOperateType(LogEnum.USER_DISABLE.getCode());
                        sysLog.setOperateContent("禁用用户");
                    }
                    if (map.get("state").equals(UserStateEnum.NORMAL.getCode())) {
                        sysLog.setOperateType(LogEnum.USER_NORMAL.getCode());
                        sysLog.setOperateContent("用户状态恢复正常");
                    }
                } else {
                    sysLog.setOperateType(type);
                    sysLog.setOperateContent(operation);

                }
            }
            sysLog.setParam(params);
//          将日志插入数据库
            restTemplate.put(url, sysLog);
        } catch (Throwable e) {
            log.info("around " + joinPoint + " with com.devops.base.exception : " + e.getMessage());
        }
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
