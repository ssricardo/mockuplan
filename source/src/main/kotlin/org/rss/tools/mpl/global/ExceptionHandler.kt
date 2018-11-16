package org.rss.tools.mpl.global

import java.util.Arrays

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.rss.tools.mpl.validation.AppException
import org.slf4j.LoggerFactory

@Aspect
class ExceptionHandler {

    @Pointcut("call(* org.rss.tools.mpl.parsing..*(..))")
    fun parsePhase() {
    }

    @Pointcut("within(org.rss.tools.mpl.Main)")
    fun fromMain() {
    }

    @Pointcut("execution(* org.rss.tools.mpl.Main.main(*))")
    fun mainCall() {
    }

    // --------------------

    @Around("mainCall()")
    @Throws(Throwable::class)
    fun globalHandler(pjp: ProceedingJoinPoint): Any? {
        try {
            return pjp.proceed()
        } catch (e: HandledException) {
            LOGGER.debug("Exception already handled")
            return null
        } catch (e: Exception) {
            LOGGER.error("------------------------------------------------------")
            LOGGER.error("An unexpected error has ocurred. ", e)
            return null
        }

    }

    // ------------------------- Parsing ----------------------------

    @AfterThrowing(value = "fromMain() && parsePhase()", throwing = "e")
    fun throwOnParse(thisJoinPoint: JoinPoint, e: Exception) {
        LOGGER.debug("Error on Parse phase. ", e.localizedMessage)
        handleAppException(thisJoinPoint, e)
    }

    // ------------------------- Render ----------------------------

    @AfterThrowing(value = "call(* org.rss.tools.mpl.render..*(..))", throwing = "e")
    fun afterThrowRender(thisJoinPoint: JoinPoint, e: Exception) {
        LOGGER.debug("Error on Render phase. ", e.localizedMessage)
        handleAppException(thisJoinPoint, e)
    }

    // --------------------------------------------------------------
    private fun handleAppException(thisJoinPoint: JoinPoint, e: Exception) {
        if (e is AppException) {
            if (LOGGER.isDebugEnabled) {
                LOGGER.debug("Location: ", thisJoinPoint.signature)
                LOGGER.debug(Arrays.toString(thisJoinPoint.args))
            }

            printApplicationException(e.error!!.message, e.error!!.howFix, *e.parameters ?: emptyArray())
            throw HandledException(e)
        }
    }

    private fun printApplicationException(error: String, fixTip: String?, vararg parameters: Any) {
        with(LOGGER) {
            error("------------------ An Error ocurred ------------------")
            error(String.format("%-15s: %s", "Error", error))
            if (parameters.isNotEmpty()) {
                error(String.format("%-15s: %s", "Parameters", Arrays.toString(parameters)))
            }
            error(String.format("%-15s: %s", "Possible fix", fixTip))
            error("------------------------------------------------------")
        }
    }

    class HandledException(cause: Throwable) : RuntimeException(cause) {
        companion object {

            /**
             *
             */
            private const val serialVersionUID = 1L
        }

    }

    companion object {

        private val LOGGER = LoggerFactory.getLogger(ExceptionHandler::class.java)
    }
}
