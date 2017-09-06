package com.stackroute.activitystream.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MessageLogger {

	public static final Logger logger=LoggerFactory.getLogger(MessageLogger.class);
    
	@Before("execution(public * sendMessage())")
	public void sendMessageBefore(JoinPoint joinPoint)
	{
		logger.info("Before Sending the Message :"+joinPoint.getSignature().getName());
		
	}
	@After("execution(public * sendMessage())")
	public void sendMessageAfter(JoinPoint joinPoint)
	{
		logger.info("After sending the Message  :"+joinPoint.getSignature().getName());
		
	}
	
	@Before("execution(public * getAllMessages(java.lang.String)) && args(emailId)")
	public void getAllMessagesBefore(JoinPoint joinPoint,String emailId)
	{
		logger.info("Before Getting all the Messages :"+joinPoint.getSignature().getName());
		logger.info("The user is :"+emailId);
		
	}
	@After("execution(public * getAllMessages(java.lang.String)) && args(emailId)")
	public void getAllMessagesAfter(JoinPoint joinPoint,String emailId)
	{
		logger.info("After Getting all the Messages  :"+joinPoint.getSignature().getName());
		logger.info("The user is :"+emailId);
		
	}
	
	
	 
		@Before("execution(public * getMessageById(java.lang.Integer)) && args(messageId)")
		public void messageByIdBefore(JoinPoint joinPoint,int messageId)
		{
			logger.info("Before Sending the Message :"+joinPoint.getSignature().getName());
			logger.info("The Message id is :"+messageId);
			
		}
	 @AfterReturning(
		      pointcut = "execution(* com.stackroute.activitystream.dao.MessageDAO.getMessageById(..))",
		      returning= "result")
		   public void logAfterReturning(JoinPoint joinPoint, Object result) {

			logger.info("logAfterReturning() is running!");
			logger.info("Method Mane is  : " + joinPoint.getSignature().getName());
			logger.info("Method returned value is : " + result);

		   }
}
