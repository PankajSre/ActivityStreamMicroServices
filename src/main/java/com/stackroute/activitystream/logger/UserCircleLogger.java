package com.stackroute.activitystream.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;



@Aspect
@Component
@EnableAspectJAutoProxy
public class UserCircleLogger {

	public static final Logger logger=LoggerFactory.getLogger(UserCircleLogger.class);
	

	@Before("execution(public * addCircle(..))")
	public void saveUser(JoinPoint pointcut)
	{
		logger.info("We are Trying to Save the Circle  :"+pointcut.getSignature().getName());
		
	}
	@AfterReturning("execution(public * addCircle(..))")
	public void saveUserafter(JoinPoint pointcut)
	{
		logger.info("Circle is saving  :"+pointcut.getSignature().getName());
		
		
	}
	
	@Before("execution(public * addUserToCircle(..,..)) && args(emailId,circleName)")
	public void addUserToCircleBefore(JoinPoint joinPoint,String emailId,String circleName)
	{
		logger.info("Before Getting User by Email  :"+joinPoint.getSignature().getName());
		logger.info("Before Getting User by Email  :"+emailId);
		logger.info("Before Getting CircleName by Email  :"+circleName);
		
	}
	@After("execution(public * addUserToCircle(..,..)) && args(emailId,circleName)")
	public void addUserToCircleAfter(JoinPoint joinPoint,String emailId,String circleName)
	{
		logger.info("After Getting User by Email  :"+joinPoint.getSignature().getName());
		logger.info("After Getting User by Email  :"+emailId);
		logger.info("After Getting CircleName by Email  :"+circleName);
		
	}
	@Before("execution(public * deleteUserFromCircle(..,..)) && args(emailId,circleName)")
	public void deleteUserFromCircleBefore(JoinPoint joinPoint,String emailId,String circleName)
	{
		logger.info("Before Getting User by Email  :"+joinPoint.getSignature().getName());
		logger.info("Before Getting User by Email  :"+emailId);
		logger.info("Before Getting CircleName by Email  :"+circleName);
		
	}
	@After("execution(public * deleteUserFromCircle(..,..)) && args(emailId,circleName)")
	public void deleteUserFromCircleAfter(JoinPoint joinPoint,String emailId,String circleName)
	{
		logger.info("After Getting User by Email  :"+joinPoint.getSignature().getName());
		logger.info("After Getting User by Email  :"+emailId);
		logger.info("After Getting CircleName by Email  :"+circleName);
		
	}
	
	@Before("execution(public * deleteCircle(..))")
	public void deleteCircleBefore(JoinPoint joinPoint)
	{
		logger.info("Before Deleting the Circle  :"+joinPoint.getSignature().getName());
		
	}
	@After("execution(public * deleteCircle(..))")
	public void deleteCircleAfter(JoinPoint joinPoint)
	{
		logger.info("After Deleting the Circle  :"+joinPoint.getSignature().getName());
		
	}
	
	@Before("execution(public * getAllCircles())")
	public void getAllCirclesBefore(JoinPoint joinPoint)
	{
		logger.info("Before Get All Circles  :"+joinPoint.getSignature().getName());
		
	}
	@After("execution(public * getAllCircles())")
	public void getAllCirclesAfter(JoinPoint joinPoint)
	{
		logger.info("After Getting All Circles  :"+joinPoint.getSignature().getName());
		
	}
	

	
	
	
	
}
