package com.example.author.exception.handler;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

	private static final Logger logger = Logger.getLogger(AsyncExceptionHandler.class);
	
	@Override
	public void handleUncaughtException(Throwable ex, Method method, Object... params) {
		logger.info("Logging that an uncaught exception has occured during asynchronous call for method "+ method.getName());
		logger.info("The root cause is "+ ex.getMessage());
		logger.error(ex.getMessage(), ex);
	}

}
