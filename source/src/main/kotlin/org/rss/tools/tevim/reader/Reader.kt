package org.rss.tools.tevim.reader

import java.io.IOException
import java.io.InputStream

/**
 * Base reader, allowing multiple sources (implementations)
 *
 * @author ricardo saturnino
 */
interface Reader {

    @Throws(IOException::class)
    fun read(key: String): InputStream
}
