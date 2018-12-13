package org.rss.tools.mpl.reader

import java.io.IOException
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption

/**
 * Reads configuration from a file in local file system.
 * Uses a reference for baseDirectory, useful for reading a relative template file
 *
 * @author ricardo saturnino
 */
class FileReader(private val baseDirectory: Path) : Reader {

    @Throws(IOException::class)
    override fun read(key: String): InputStream {
        // path is relative, use it based on baseDirectory; if it's absolute, replace the older
        val path = baseDirectory.resolve(key)
        return Files.newInputStream(path, StandardOpenOption.READ)
    }

}
