/**
 *
 *  @author Olesiński Wojciech S22368
 *
 */

package zad1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finder {
	
	private String fileName;
	
	public Finder(String fileName) {
		this.fileName = fileName;
	}
	
	public int getIfCount() {
		Pattern pattern = Pattern.compile("(\\bif\\b)");
		Pattern comment = Pattern.compile("//");
		Pattern string = Pattern.compile("\"");
		
		int ifCount = 0;
		try {
			for (String line: Files.readAllLines(Paths.get(fileName))) {
				Matcher matcher = pattern.matcher(line);
				
				while (matcher.find()) {
					Matcher commentMatcher = comment.matcher(line.subSequence(0, matcher.start(1)));
					
					Matcher stringMatcher = string.matcher(line.subSequence(0, matcher.start(1)));
					int stringSymbolsCount = 0;
					while (stringMatcher.find()) stringSymbolsCount++;
					
					if (!commentMatcher.find() && stringSymbolsCount % 2 == 0) {
//						System.out.println(line.subSequence(0, matcher.end(1)));
						ifCount++;
					}
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
			for (String line: Files.readAllLines(Paths.get(fileName))) {
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
