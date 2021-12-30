/**
 *
 *  @author Olesiński Wojciech S22368
 *
 */

package zad2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Anagrams {
	private Map<String, List<String>> anagrams;
	
	public Anagrams(String fname) {
		List<String> words = new ArrayList<String>();
		
		try (Scanner scanner = new Scanner(new File(fname))) {
			while (scanner.hasNext()) words.add(scanner.next());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		anagrams = new HashMap<>();
		List<String> tempList;
		boolean added;
		
		for (String word: words) {
			added = false;
			
			for (String key: anagrams.keySet()) {
				if (isAnagram(word, key)) {
					anagrams.get(key).add(word);
					added = true;
				}
			}
			
			if (!added) {
				tempList = new ArrayList<String>();
				tempList.add(word);
				anagrams.put(word, tempList);
			}
		}
		
	}
	
	public List<List<String>> getSortedByAnQty() {
		return anagrams.values().stream().sorted((l1, l2) -> l2.size() - l1.size()).collect(Collectors.toList());
	}
	
	public String getAnagramsFor(String word) {
		String result = word + ": ";
		for (String key: anagrams.keySet()) {
			if (isAnagram(word, key)) {
				result = result + anagrams.get(key)
											.stream()
											.filter(s -> !s.equals(word))
											.collect(Collectors.toList())
											.toString();
			}
		}
		
		
		return result;
	}
	
	public boolean isAnagram(String word1, String word2) {
		if (word1.length() != word2.length()) return false;
		IntStream stream1;
		IntStream stream2;
		Set<Integer> set = word1.chars().boxed().collect(Collectors.toSet());
		for (Integer letter: set) {
			stream1 = word1.chars();
			stream2 = word2.chars();
			if (stream1.filter(c -> c == letter).count() != stream2.filter(c -> c == letter).count()) {
				return false;
			}
		}
		return true;
	}
	
}  
