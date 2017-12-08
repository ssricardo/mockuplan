package org.rss.tools.mpl.render;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.rss.tools.mpl.render.html.PredefinedStyle;

public class FileRenderer {

	private static final String OUTPUT_FORMAT = ".html";

	private final Path directory;
	
	private final String fileName;

	private final List<RenderResponse> responseList;

	public FileRenderer(List<RenderResponse> responses, Path dir, String name) {
		super();
		if (responses == null || dir == null || name == null) {
			throw new IllegalArgumentException("Null values found preparing to process files");
		}
		this.responseList = responses;
		this.directory = dir;
		this.fileName = name;
	}

	public Path render() throws IOException {
		
		if (responseList == null || responseList.isEmpty()) {
			return null;
		}
		
		int i = 0;
		for (RenderResponse res : responseList) {
			StringBuilder sb = new StringBuilder(fileName);
			if (i > 0) {
				sb.append(i);
			}
			
			InputStream in;
			switch (res.getType()) {
			case StringData:
				Path currentFile = Files.createFile(directory.resolve(sb.append(OUTPUT_FORMAT).toString()));
				Files.write(currentFile, ((String) res.getData()).getBytes(), 
						StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
				i++;
				break;
			
			case FileReference:
				in = getClass().getResourceAsStream((String) res.getData());
				Files.copy(in, directory.resolve((String) res.getData()), StandardCopyOption.REPLACE_EXISTING);
				break;
				
			case PredefinedStyle:
				PredefinedStyle style = (PredefinedStyle) res.getData();
				in = getClass().getResourceAsStream(style.getInternalRef());
				Files.copy(in, directory.resolve(style.getFileName()), StandardCopyOption.REPLACE_EXISTING);
				break;

			default:
				throw new UnsupportedOperationException("Unespected response type. I don't know how to treat it.");
			}
		}
		return directory.resolve(fileName.concat(OUTPUT_FORMAT));
	}

}
