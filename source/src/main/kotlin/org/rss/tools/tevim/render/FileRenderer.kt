package org.rss.tools.tevim.render

import org.rss.tools.tevim.render.html.PredefinedStyle

import java.io.IOException
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import java.nio.file.StandardOpenOption

class FileRenderer(private val responseList: List<RenderResponse>?,
                   private val directory: Path, private val fileName: String) {

    init {
        require(responseList != null && directory != null && fileName != null)
    }

    @Throws(IOException::class)
    fun render(): Path? {

        if (responseList == null || responseList.isEmpty()) {
            return null
        }

        var i = 0
        for (res in responseList) {
            val sb = StringBuilder(fileName)
            if (i > 0) {
                sb.append(i)
            }

            val iStream: InputStream
            when (res.type) {
                ResponseType.StringData -> {
                    val currentFile = Files.createFile(directory.resolve(sb.append(OUTPUT_FORMAT).toString()))
                    Files.write(currentFile, (res.data as String).toByteArray(),
                            StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
                    i++
                }

                ResponseType.FileReference -> {
                    iStream = javaClass.getResourceAsStream(res.data as String)
                    Files.copy(iStream, directory.resolve(res.data as String), StandardCopyOption.REPLACE_EXISTING)
                }

                ResponseType.PredefinedStyle -> {
                    val style = res.data as PredefinedStyle
                    iStream = javaClass.getResourceAsStream(style.internalRef)
                    Files.copy(iStream, directory.resolve(style.fileName), StandardCopyOption.REPLACE_EXISTING)
                }

                else -> throw UnsupportedOperationException("Unespected response type. I don't know how to treat it.")
            }
        }
        return directory.resolve(fileName + OUTPUT_FORMAT)
    }

    companion object {

        private const val OUTPUT_FORMAT = ".html"
    }

}
