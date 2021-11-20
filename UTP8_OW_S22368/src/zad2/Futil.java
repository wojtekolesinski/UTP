package zad2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;


public class Futil {
		
	public static void processDir(String dirname, String resultFileName) {
		try {
			Files.walkFileTree(Path.of(dirname), new SimpleFileVisitor<Path>(){
				
				private List<String> lines;

				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					if (file.getFileName().toString().endsWith(".txt")) {
						if (lines == null) {
							lines = new ArrayList<String>();
						}
						
						lines.addAll(Files.readAllLines(file, Charset.forName("windows-1250")));
					}
					return super.visitFile(file, attrs);
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					Path resultPath = Paths.get("./" + resultFileName);
					if (!Files.exists(resultPath)) {
						Files.createFile(resultPath);
					}
					
					Files.write(resultPath, lines, StandardCharsets.UTF_8);
					return super.postVisitDirectory(dir, exc);
				}});
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}