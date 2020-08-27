package cc.mrbird.febs.common.logging.starter.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author xuefrye
 */
@Slf4j
@Aspect
@Component
public class ControllerLogAspect {
    @Around("(@within(org.springframework.stereotype.Controller)" +
            "|| @within(org.springframework.web.bind.annotation.RestController))" +
            "&& execution(public * cc.mrbird..*.controller..*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {

        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        long beginTime = System.currentTimeMillis();
        Object returnValue = null;
        Exception ex = null;
        try {
            returnValue = pjp.proceed();
            return returnValue;
        } catch (Exception e) {
            ex = e;
            throw e;
        } finally {
            long cost = System.currentTimeMillis() - beginTime;
            if (ex != null) {
                log.error("[class: {}][method: {}][cost: {}ms][args: {}][发生异常]",
                        className, methodName, pjp.getArgs(), ex);
            } else {
                log.info("[class: {}][method: {}][cost: {}ms][args: {}][return: {}]",
                        className, methodName, cost, pjp.getArgs(), returnValue);
            }
        }

    }
}
