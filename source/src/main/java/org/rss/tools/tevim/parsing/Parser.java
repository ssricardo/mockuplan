package org.rss.tools.tevim.parsing;

import java.io.IOException;
import java.io.InputStream;

/**
 * Generic contract for parsers
 * @author ricardo saturnino
 * @param <T> parse result
 * 
 */
public interface Parser<T> {

	/**
	 * Tries to parse the input and produce a generic-typed output  
	 * @param input
	 * @return type depends on the implementation
	 * @throws IOException
	 */
	T parse(InputStream input) throws IOException;
}
