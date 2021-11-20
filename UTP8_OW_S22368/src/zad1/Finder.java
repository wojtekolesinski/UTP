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
import java.util.stream.Collectors;

public class Finder {
	
	private String fileName;
	
	public Finder(String fileName) {
		this.fileName = fileName;
	}
	
	public int getIfCount() {
		Pattern pattern = Pattern.compile("if");
		int ifCount = 0;
		try {
			for (String line: Files.readAllLines(Path.of(fileName))) {
				Matcher matcher = pattern.matcher(line);
				
				while (matcher.find()) {
					ifCount++;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ifCount;
	}
	
	
}    
