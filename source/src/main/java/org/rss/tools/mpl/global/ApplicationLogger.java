package org.rss.tools.mpl.global;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logging Aspect. 
 * @author ricardo saturnino
 */
@Aspect
public class ApplicationLogger {

	private static final Logger LOG = LoggerFactory.getLogger(ApplicationLogger.class);
	
	@Pointcut("execution(* org.rss.tools.mpl.parsing..*(..))")
	public void allMethods() {}
	
	@Around("allMethods()")
	public Object aroundAll(ProceedingJoinPoint pjp) throws Throwable {
		LOG.debug("Start {}.{}", pjp.getSignature().getDeclaringType(), pjp.getSignature().getName());
		if (LOG.isTraceEnabled()) {
			LOG.trace(Arrays.toString(pjp.getArgs()));
		}

		try {
			return pjp.proceed();
		} finally {
			LOG.trace("Method {} complete", pjp.getSignature().getName());
		}
	}
	
	// ---------------------- Main Logger ------------------------

	@Before("execution(* org.rss.tools.mpl.Main.main(..))")
	public void startMain() {
		LOG.info("Starting Mockuplan process......................");
	}

	@After("execution(* org.rss.tools.mpl.Main.main(..))")
	public void allDone() {
		LOG.info("Execution ended ......................");
	}

	@Before("call(* org.rss.tools.mpl.Main.processParse(..))")
	public void startParse() {
		LOG.info("* Executing PARSE phase......................");
	}

	@Before("call(* org.rss.tools.mpl.Main.processRender(..))")
	public void startRender() {
		LOG.info("* Executing RENDER phase......................");
	}
}
