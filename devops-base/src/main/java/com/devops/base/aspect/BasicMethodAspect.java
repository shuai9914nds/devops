package com.devops.base.aspect;

import com.devops.base.annotation.BasicMethod;
import com.devops.base.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author: liushuai
 * @date: 2021/7/13
 * @description：打印接口入参、执行时间、返回结果
 */
@Aspect
@Component
@Slf4j
public class BasicMethodAspect implements Ordered {

    /**
     * 打印参数
     */
    public static final int PRINT_PARAM = 1;

    /**
     * 打印方法执行时间
     */
    public static final int METHOD_TIME = 2;

    /**
     * 打印接口返回结果
     */
    public static final int PRINT_RESULT = 3;


    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("@annotation(com.devops.base.annotation.BasicMethod)")
    public void basicMethodPointCut() {
    }


    @Before("basicMethodPointCut() && @annotation(basicMethod)")
    public void doBefore(JoinPoint joinPoint, BasicMethod basicMethod) {
        log.info("-----------《注解》{}接口,开始-----------", basicMethod.operation());
        int[] type = basicMethod.type();
        startTime.set(System.currentTimeMillis());
        if (ArrayUtils.contains(type, PRINT_PARAM)) {
            Object[] args = joinPoint.getArgs();
            Signature signature = joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            String[] parameterNames = methodSignature.getParameterNames();
            for (int i = 0, len = parameterNames.length; i < len; i++) {
                log.info("「参数名」：{}，「参数值」：{}", parameterNames[i], JacksonUtil.toJSon(args[i]));
            }
        }

    }

    @Override
    public int getOrder() {
        return 0;
    }

    @AfterReturning(returning = "ret", pointcut = "basicMethodPointCut() && @annotation(basicMethod)")
    public void doAfterReturning(Object ret, BasicMethod basicMethod) {
        int[] type = basicMethod.type();
        if (ArrayUtils.contains(type, PRINT_RESULT)) {
            log.info("{}接口,返回值:{}", basicMethod.operation(), JacksonUtil.toJSon(ret));
        }
        if (ArrayUtils.contains(type, METHOD_TIME)) {
            log.info("{}接口,执行时间:{}ms", basicMethod.operation(), (System.currentTimeMillis() - startTime.get()));
        }
        log.info("-----------《注解》{}接口,结束-----------", basicMethod.operation());
    }
}
