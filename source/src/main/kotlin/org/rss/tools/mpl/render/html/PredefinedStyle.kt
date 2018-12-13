package org.rss.tools.mpl.render.html

/**
 * Predefined style classes
 * @author ricardo saturnino
 */
enum class PredefinedStyle private constructor(val internalRef: String, val fileName: String) {
    DEFAULT("/css/default.css", "default.css"),
    ANDROID("/css/default.css", "default.css"), // TODO create this configuration
    IOS("/css/default.css", "default.css")            // TODO create this configuration

}