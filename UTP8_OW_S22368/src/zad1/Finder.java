/**
 *
 *  @author Olesiński Wojciech S22368
 *
 */

package zad1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finder {
	
	private String fileName;
	
	public Finder(String fileName) {
		this.fileName = fileName;
	}
	
	public int getIfCount() {
		Pattern pattern = Pattern.compile("(?<!//\\s*)if");
		int ifCount = 0;
		try {
			for (String line: Files.readAllLines(Path.of(fileName))) {
				Matcher matcher = pattern.matcher(line);
				
				while (matcher.find()) {
					ifCount++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ifCount;
	}
	
	public int getStringCount(String string) {
		Pattern pattern = Pattern.compile(string);
		int ifCount = 0;
		try {
			for (String line: Files.readAllLines(Path.of(fileName))) {
				Matcher matcher = pattern.matcher(line);
				
				while (matcher.find()) {
					ifCount++;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ifCount;
	}
	
	
}    
