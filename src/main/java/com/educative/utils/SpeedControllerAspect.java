package com.educative.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Speed Controller class ensure that user can
 * slow down execution explicitly to see the UI simulation
 */
@Aspect
public class SpeedControllerAspect {

	private static final long INTERIM_SLEEP = Long
			.parseLong(ConfigurationManager.getInstance().getProperty("interim.sleep"));

	@Pointcut("execution(public * org.openqa.selenium.SearchContext+.findElement*(org.openqa.selenium.By))")
	public void findElementsMehod() {
	}

	@Pointcut("execution(public * org.openqa.selenium.Alert.*(..))")
	public void alertMehod() {
	}

	@Pointcut("execution(public * org.openqa.selenium.interactions.Actions.doubleClick(..)) || execution(public * org.openqa.selenium.interactions.Actions.contextClick(..))")
	public void actionClassMethodMehod() {
	}

	@Around("findElementsMehod() || alertMehod() || actionClassMethodMehod()")
	public Object findElementsMehod(final ProceedingJoinPoint joinPoint) throws Throwable {
		Thread.sleep(INTERIM_SLEEP);
		return joinPoint.proceed();

	}

	@AfterReturning(pointcut = "execution(public void org.openqa.selenium.interactions.Action.perform())")
	public void afterPerform() {
		try {
			Thread.sleep(INTERIM_SLEEP);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
