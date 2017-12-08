package org.rss.tools.mpl.global;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.rss.tools.mpl.validation.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

	@Pointcut("call(* org.rss.tools.mpl.parsing..*(..))")
	public void parsePhase() {};
	
	@Pointcut("within(org.rss.tools.mpl.Main)")
	public void fromMain() {};
	
	@Pointcut("execution(* org.rss.tools.mpl.Main.main(*))")
	public void mainCall() {};
	
	// --------------------
	
	@Around("mainCall()")
	public Object globalHandler(ProceedingJoinPoint pjp) throws Throwable {
		try {
			return pjp.proceed();
		} catch (HandledException e) {
			LOGGER.debug("Exception already handled");
			return null;
		} catch (Exception e) {
			LOGGER.error("------------------------------------------------------");
			LOGGER.error("An unexpected error has ocurred. ", e);
			return null;
		} 
	}
	
	// ------------------------- Parsing ----------------------------
	
	@AfterThrowing(value = "fromMain() && parsePhase()", throwing = "e")
	public void throwOnParse(JoinPoint thisJoinPoint, Exception e) {
		LOGGER.debug("Error on Parse phase. ", e.getLocalizedMessage());
		handleAppException(thisJoinPoint, e);
	}

	// ------------------------- Render ----------------------------
	
	@AfterThrowing(value = "call(* org.rss.tools.mpl.render..*(..))", throwing = "e")
	public void afterThrowRender(JoinPoint thisJoinPoint, Exception e) {
		LOGGER.debug("Error on Render phase. ", e.getLocalizedMessage());
		handleAppException(thisJoinPoint, e);
	}
	
	// --------------------------------------------------------------
	private void handleAppException(JoinPoint thisJoinPoint, Exception e) {
		if (e instanceof AppException) {
			AppException pse = (AppException) e;
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Location: ", thisJoinPoint.getSignature());
				LOGGER.debug(Arrays.toString(thisJoinPoint.getArgs()));
			}

			printApplicationException(pse.getError().getMessage(), pse.getError().getHowFix(), pse.getParameters());
			throw new HandledException(e);
		}
	}
	
	private void printApplicationException(String error, String fixTip, Object... parameters) {
		LOGGER.error("------------------ An Error ocurred ------------------");
		LOGGER.error(String.format("%-15s: %s", "Error", error)); 
		if (parameters != null && parameters.length > 0) {
			LOGGER.error(String.format("%-15s: %s", "Parameters", Arrays.toString(parameters)));
		}
		LOGGER.error(String.format("%-15s: %s", "Possible fix", fixTip));
		LOGGER.error("------------------------------------------------------");
	}

	public static class HandledException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public HandledException(Throwable cause) {
			super(cause);
		}
		
	}
}
