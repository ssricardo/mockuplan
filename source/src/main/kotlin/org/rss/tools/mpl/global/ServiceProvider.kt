package org.rss.tools.mpl.global

import java.util.*

/**
 * Responsible by providing an implementation, given an interface
 * @author ricardo saturnino
 */
object ServiceProvider {

    private val REGISTER = ReferenceWrapper()

    fun <T:Any> register(klazz: Class<T>, instance: T) {
        REGISTER.put(klazz, instance)
    }

    operator fun <T> get(key: Class<T>): T {
        return REGISTER[key] ?: throw IllegalStateException("No instance was provieded for $key")
    }

    internal class ReferenceWrapper {
        private val map = HashMap<Class<*>, Any>()

        fun <T:Any> put(key: Class<T>, value: T) {
            this.map[key] = value
        }

        /** Enforced by wrapped put  */
        operator fun <T> get(key: Class<T>): T? {
            return (this.map[key] as? T)
        }
    }
}
