/**
 *
 *  @author Olesiński Wojciech S22368
 *
 */

package zad1;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Main {
  public static void main(String[] args) {
	  
	  Function<String, List<String>> flines = path -> {
		  List<String> lines = new ArrayList<String>();
		  BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		  br.lines().forEach(lines::add);
		  return lines;
	  };
	  
	  Function<List<String>, String> join = list -> String.join(" " , list);
	  
	  Function<String, List<Integer>> collectInts = text -> {
		  List<Integer> ints = new ArrayList<>();
		  Matcher matcher = Pattern.compile("[0-9]+").matcher(text);
		  
		  while (matcher.find()) ints.add(Integer.parseInt(matcher.group()));
		  return ints;
	  };
	  
	  
//	  Arrays.stream(text.split(" "))
//											.filter(s -> s.matches("[0-9]+"))
//											.map(Integer::parseInt)
//											.collect(Collectors.toList());
//		  
	  Function<List<Integer>, Integer> sum = list -> list.stream()
			  										.reduce(0, (total, element) -> total + element);
	  
    /*<--
     *  definicja operacji w postaci lambda-wyrażeń:
     *  - flines - zwraca listę wierszy z pliku tekstowego
     *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
     *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
     *  - sum - zwraca sumę elmentów listy liczb całkowitych
     */

    String fname = System.getProperty("user.home") + "/LamComFile.txt"; 
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);  
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

  }
}
