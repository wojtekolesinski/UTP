/**
 *
 *  @author Olesiński Wojciech S22368
 *
 */

package zad2;


import java.util.*;
import java.util.stream.*;

public class Main {

  public static void main(String[] args) {
    // Lista destynacji: port_wylotu port_przylotu cena_EUR 
    List<String> dest = Arrays.asList(
      "bleble bleble 2000",
      "WAW HAV 1200",
      "xxx yyy 789",
      "WAW DPS 2000",
      "WAW HKT 1000"
    );
    double ratePLNvsEUR = 4.30;
    List<String> result = dest.stream()
    		.filter(s -> s.equals(s.toUpperCase()) && s.split(" ")[0].length() == 3 && s.split(" ")[1].length() == 3)
    		.map(s -> String.format("to %s - price in PLN:\t%.0f", s.split(" ")[1], Double.parseDouble(s.split(" ")[2]) * ratePLNvsEUR))
    		.collect(Collectors.toList());

    for (String r : result) System.out.println(r);
  }
}
