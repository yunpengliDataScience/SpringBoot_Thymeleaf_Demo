package org.dragon.yunpeng.thymeleaf.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	@After("execution(* org.dragon.yunpeng.thymeleaf.repositories.UserRepository.*(..))")
	public void logAfter() {
		logger.info("UserRepository's method is called.");
	}

	// Set up a Pointcut for CrudRepository save related methods
	@Pointcut("execution( * org.springframework.data.repository.CrudRepository.save*(..)) || "
			+ "execution( * org.springframework.data.repository.CrudRepository.delete*(..))")
	public void repositoryClassMethods() {
	}

	@Around("repositoryClassMethods()")
	public void inspectCrudRepositorySaveMethod(ProceedingJoinPoint pjp) throws Throwable {

		logger.info("Method with signature: {" + pjp.getSignature().toString() + "} is called.");

		Object[] args = pjp.getArgs();

		for (Object arg : args) {
			logger.info("Argument Object: " + arg.toString());
		}

		Object returnedObj = pjp.proceed();

		if (returnedObj != null) {
			logger.info("Returned Object: " + returnedObj.toString());
		}
	}
}
