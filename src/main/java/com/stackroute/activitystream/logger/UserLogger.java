package com.stackroute.activitystream.logger;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class UserLogger {

	public static final Logger logger=LoggerFactory.getLogger(UserLogger.class);
	
	@Before("loginPointcut()")
	public void loggerBeforeLogin()
	{
		logger.info("You are entering the User detials ");
	}
	@After("loginPointcut()")
	public void loggerAfterLogin()
	{
		logger.info("You have Successfully loggedIn ");
	}
	
	@Pointcut("execution(public * getAllUsers())")
	public void loginPointcut()
	{
		
	}
	@Before("execution(public * saveUser(..))")
	public void saveUser(JoinPoint pointcut)
	{
		logger.info("User is about to save  :"+pointcut.getSignature().getName());
		
	}
	@AfterReturning("execution(public * saveUser(..))")
	public void saveUserafter(JoinPoint pointcut)
	{
		logger.info("User is saved  :"+pointcut.getSignature().getName());
		
		
	}
	
	@Before("execution(public * getUserByEmailId(..))")
	public void getUserByEmailBefore(JoinPoint joinPoint)
	{
		logger.info("Before Getting User by Email  :"+joinPoint.getSignature().getName());
		
	}
	@After("execution(public * getUserByEmailId(java.lang.String)) && args(emailId)")
	public void getUserByEmailAfter(String emailId)
	{
		logger.info("After Getting User by Email  :"+emailId);
		
	}
	
	@Before("execution(public * updateUser(..))")
	public void updateUserBefore(JoinPoint joinPoint)
	{
		logger.info("Before Updating the User  :"+joinPoint.getSignature().getName());
		
	}
	@After("execution(public * updateUser(..))")
	public void updateUserAfter(JoinPoint joinPoint)
	{
		logger.info("After Updating the User  :"+joinPoint.getSignature().getName());
		
	}
	
	@Before("execution(public * deleteUser(..))")
	public void deleteUserBefore(JoinPoint joinPoint)
	{
		logger.info("Before Deleting the User  :"+joinPoint.getSignature().getName());
		
	}
	@After("execution(public * deleteUser(..))")
	public void deleteUserAfter(JoinPoint joinPoint)
	{
		logger.info("After Deleting the User  :"+joinPoint.getSignature().getName());
		
	}
	
	
	
	
	
	
}
