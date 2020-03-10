package com.airline.aspect;


import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger LOGGER = Logger.getLogger(LoggingAspect.class);

    @Around("execution(* com.airline.repository.*.*(..))")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start();
        LOGGER.info("Method " + pjp.getSignature().getName() + " start");
        Object output = pjp.proceed();
        LOGGER.info("Method execution completed.");
        sw.stop();
        LOGGER.info("Method " + pjp.getSignature().getName() + " finished");
        LOGGER.info("Method execution time: " + sw.getTotalTimeMillis() + " milliseconds.");
        return output;
    }
}
// with web add in dispatcher-servlet