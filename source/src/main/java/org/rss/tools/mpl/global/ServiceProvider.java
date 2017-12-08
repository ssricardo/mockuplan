package org.rss.tools.mpl.global;

import java.util.HashMap;
import java.util.Map;

/**
 * Responsible by providing an implementation, given an interface 
 * @author ricardo saturnino
 */
public final class ServiceProvider {

	private static final ReferenceWrapper REGISTER = new ReferenceWrapper();
	
	private ServiceProvider() {
	}

	public static <T> void register(Class<T> klazz, T instance) {
		REGISTER.put(klazz, instance);
	}

	public static <T> T get (Class<T> key) {
		T object = REGISTER.get(key);
		if (object == null) {
			throw new IllegalStateException("No instance was provieded for " + key);
		}
		return object;
	}

	final static class ReferenceWrapper {
		private final Map<Class<?>, Object> map = new HashMap<>();
		
		public <T> void put(Class<T> key, T value) {
			this.map.put(key, value);
		}
		
		/** Enforced by wrapped put */
		@SuppressWarnings("unchecked")
		public <T> T get(Class<T> key) {
			return (T) this.map.get(key);
		}
	}
}
