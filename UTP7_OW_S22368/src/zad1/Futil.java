package zad1;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Futil {
		
	public static void processDir(String dirname, String resultFileName) {
		try {
			Stream<String> lines = Files.walk(Paths.get(dirname))
				.filter(Files::isRegularFile)
				.filter(path -> path.toString().endsWith("txt"))
				.flatMap(path -> {
					try {
						return Files.lines(path, Charset.forName("windows-1250"));
					} catch (IOException e) {
						e.printStackTrace();
						return null;
					}
				})
				.filter(line -> line != null);
			
			Path resultPath = Paths.get("./" + resultFileName);
			if (!Files.exists(resultPath)) {
				Files.createFile(resultPath);
			}
			Files.write(resultPath, (Iterable<String>)lines::iterator, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
