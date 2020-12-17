package aspect;

import com.test.anno.annotation.ServiceLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

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
    @Pointcut("@annotation(annotation.MyLog)")
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
                        operation = method.getAnnotation(ServiceLog.class).operation();// 操作人
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
        } catch (Throwable e) {
            log.info("around " + joinPoint + " with exception : " + e.getMessage());
        }
        return "";
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
