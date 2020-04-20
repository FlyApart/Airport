package com.airport.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

	@Around("execution(* com.airport.*.*.*(..))")
	public Object controllers (ProceedingJoinPoint pjp) throws Throwable {
		StopWatch sw = new StopWatch ();
		sw.start ();
		log.info ("Method " + pjp.getSignature ()
		                            .getName () + " start");
		Object output = pjp.proceed ();
		log.info ("Method execution completed.");
		sw.stop ();
		log.info ("Method " + pjp.getSignature ()
		                            .getName () + " finished");
		log.info ("Method execution time: " + sw.getTotalTimeMillis () + " milliseconds.");
		return output;
	}
}
