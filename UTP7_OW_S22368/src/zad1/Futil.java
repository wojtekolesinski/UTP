package zad1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Futil {
		
	public static void processDir(String dirname, String resultFileName) {
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(resultFileName), StandardCharsets.UTF_8, StandardOpenOption.APPEND)){
			Files.walk(Paths.get(dirname))
				.filter(Files::isRegularFile)
				.filter(path -> path.toString().endsWith("txt"))
				.forEachOrdered(path -> {
					try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("windows-1250"))) {
						reader.lines().forEachOrdered(line -> {
							try {
								writer.write(line + "\n");
							} catch (IOException e) {
								e.printStackTrace();
							}
						});
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
		} catch (NoSuchFileException e){	
			try {
				Files.createFile(Paths.get(resultFileName));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * try ( BufferedWriter writer = Files.newBufferedWriter(
	 * Paths.get(resultFileName), StandardCharsets.UTF_8, StandardOpenOption.APPEND)
	 * ) {
	 * 
	 * File baseDir = new File(dirname);
	 * 
	 * for (File file: baseDir.listFiles()) { if (file.isDirectory()) {
	 * processDir(file.getAbsolutePath(), resultFileName); } else { try
	 * (BufferedReader reader = Files.newBufferedReader(file.toPath(),
	 * Charset.forName("windows-1250"))) { String line; while((line =
	 * reader.readLine()) != null) { writer.write(line); } } catch (IOException e) {
	 * // TODO Auto-generated catch block e.printStackTrace(); } } }
	 * 
	 * } catch (IOException e1) { // TODO Auto-generated catch block
	 * e1.printStackTrace(); }
	 */
}
