package org.rss.tools.mpl.validation

/**
 * ApplicationException. Contains reference to AppErrors and parameter
 * @author ricardo saturnino
 */
class AppException : IllegalStateException {

    var error: AppValidation? = null

    var parameters: Array<out Any>? = null

    constructor(error: AppValidation, cause: Throwable) : super(error.message, cause) {
        this.error = error
    }

    constructor(error: AppValidation, vararg params: Any) : super() {
        this.error = error
        this.parameters = params
    }

    companion object {

        /**
         *
         */
        @JvmStatic
        private val serialVersionUID = 1L
    }

}
