/**
 *
 *  @author Olesiński Wojciech S22368
 *
 */

package zad2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Main {
  public static void main(String[] args) throws IOException {
	  Map<String, List<String>> map = new BufferedReader(new InputStreamReader(new URL("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt").openStream()))
	  															.lines()
																.collect(Collectors.groupingBy(str -> {
																	char[] arr = str.toCharArray();
																	Arrays.sort(arr);
																	return String.copyValueOf(arr);
																}, LinkedHashMap::new, Collectors.toList()));
	  	  
	  map.values()
	  		.stream()
			.filter(e -> e.size() == map.values()
										.stream()
										.mapToInt(v -> v.size())
										.max()
										.getAsInt())
			.forEach(e -> System.out.println(e.stream().collect(Collectors.joining(" "))));

  }
}
