package org.rss.tools.mpl.reader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Reads configuration from a file in local file system
 * @author ricardo
 */
public class FileReader implements Reader {

	private final Path baseDirectory;
	
	public FileReader(Path baseDirectory) {
		super();
		if (baseDirectory == null) {
			throw new IllegalArgumentException("Base directory must be specified");
		}
		this.baseDirectory = baseDirectory;
	}

	@Override
	public InputStream read(String key) throws IOException {
		Path path = baseDirectory.resolve(key);		// path is relative, use it based on baseDirectory; if it's absolute, replace the older
		return Files.newInputStream(path, StandardOpenOption.READ);
	}

}
