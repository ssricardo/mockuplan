package org.rss.tools.tevim.global

/**
 * Holds constants for Program parameters
 *
 * @author ricardo saturnino
 */
enum class Parameter private constructor(val text: String, val isValueRequired: Boolean) {

    // Remember to update params.md altogether with this

    INPUT("-i", true),
    OUTPUT("-o", true),
    HELP("-help", false),
    TEMP("-temp", false),
    SHOW_BROWSER("-show", false),

    // ---------------------------------------------------

    DEBUG("-debug", false),

    TRACE("-trace", false);


    companion object {

        fun getInstance(value: String): Parameter {
            for (param in values()) {
                if (param.text == value) {
                    return param
                }
            }
            throw UnsupportedOperationException("Parameter not supported: $value")
        }
    }

}
