package org.rss.tools.mpl.reader;

import java.io.IOException;
import java.io.InputStream;

/**
 * Base of reader for multiple sources (implementations)
 * 
 * @author ricardo saturnino
 */
public interface Reader {

	InputStream read(String key) throws IOException;
}
