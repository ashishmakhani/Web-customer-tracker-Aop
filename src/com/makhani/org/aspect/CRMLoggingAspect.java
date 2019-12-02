package com.makhani.org.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
@EnableAspectJAutoProxy
public class CRMLoggingAspect {
	
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	//pointcut declarations
	
	@Pointcut("execution(* com.makhani.org.controller.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution(* com.makhani.org.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* com.makhani.org.dao.*.*(..))")
	private void forDaoPackage() {}

	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {}
	
	
	//Add @Before advice
	
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinpoint) {
		
		String theMethod = theJoinpoint.getSignature().toShortString();
		
		myLogger.info("==>> In @Before : Calling method: " + theMethod);
		
		//GetArguments
		
		Object[] args = theJoinpoint.getArgs();
		
		for(Object tempArg:args) {
			
			myLogger.info("==>> Argument: " + tempArg);
			
		}
	}
	
	@AfterReturning("forAppFlow()")
	public void afterReturning(JoinPoint theJoinpoint, Object theResult) {
		
		String theMethod = theJoinpoint.getSignature().toShortString();
		
		myLogger.info("==>> In @AfterReturning : Calling method: " + theMethod);
	
		myLogger.info("==>> Result: " + theResult);
}
	
}