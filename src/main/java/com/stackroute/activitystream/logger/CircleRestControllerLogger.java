package com.stackroute.activitystream.logger;

import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Aspect
public class CircleRestControllerLogger {

	public static final Logger log=LoggerFactory.getLogger(CircleRestControllerLogger.class);
	
	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
	public void controller() {
	}

	@Pointcut("execution(public ResponseEntity<?> getAllCircles()")
	protected void allMethod() {
	}
	
	@Pointcut("execution(* *.*(..))")
	protected void getAllCircles() {
	}
    
	@Pointcut("execution(public * *(..))")
	protected void loggingPublicOperation() {
	}

	@Pointcut("execution(* *.*(..))")
	protected void loggingAllOperation() {
	}

	@Pointcut("within(com.stackroute.activitystream.*)")
	private void logAnyFunctionWithinResource() {
	}

	
	@Before("getAllCircles() && controller() && allMethod() && args(..,request)")
	public void logBefore(JoinPoint joinPoint, HttpServletRequest request) {

		log.info("Entering in Method :  " + joinPoint.getSignature().getName());
		log.info("Class Name :  " + joinPoint.getSignature().getDeclaringTypeName());
		log.info("Arguments :  " + Arrays.toString(joinPoint.getArgs()));
		log.info("Target class : " + joinPoint.getTarget().getClass().getName());

		if (null != request) {
			log.info("Start Header Section of request ");
			log.info("Method Type : " + request.getMethod());
			Enumeration<String> headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String headerName = headerNames.nextElement();
				String headerValue = request.getHeader(headerName);
				log.info("Header Name: " + headerName + " Header Value : " + headerValue);
			}
			log.info("Request Path info :" + request.getServletPath());
			log.info("End Header Section of request ");
		}
	}
	
	@AfterReturning(pointcut = "controller() && allMethod()", returning = "result")
	public void logAfter(JoinPoint joinPoint, Object result) {
		String returnValue = this.getValue(result);
		log.info("Method Return value : " + returnValue);
	}
	
	@AfterThrowing(pointcut = "controller() && allMethod()", throwing = "exception")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
		log.error("An exception has been thrown in " + joinPoint.getSignature().getName() + " ()");
		log.error("Cause : " + exception.getCause());
	}
	
	@Around("controller() && allMethod()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		
		long start = System.currentTimeMillis();
		try {
			String className = joinPoint.getSignature().getDeclaringTypeName();
			String methodName = joinPoint.getSignature().getName();
			Object result = joinPoint.proceed();
			long elapsedTime = System.currentTimeMillis() - start;
			log.info("Method " + className + "." + methodName + " ()" + " execution time : "
					+ elapsedTime + " ms");
		
			return result;
		} catch (IllegalArgumentException e) {
			log.error("Illegal argument " + Arrays.toString(joinPoint.getArgs()) + " in "
					+ joinPoint.getSignature().getName() + "()");
			throw e;
		}
	}
	private String getValue(Object result) {
		String returnValue = null;
		if (null != result) {
			if (result.toString().endsWith("@" + Integer.toHexString(result.hashCode()))) {
				returnValue = ReflectionToStringBuilder.toString(result);
			} else {
				returnValue = result.toString();
			}
		}
		return returnValue;
	}
}

