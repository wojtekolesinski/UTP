/**
 *
 *  @author Olesiński Wojciech S22368
 *
 */

package zad1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

  public static void main(String[] args) {
	  try {
		  Files.list(Paths.get("..")).forEach(f -> System.out.println(f.getFileName()));
		Files.lines(Paths.get("../Towary.txt.")).limit(1000).forEach(System.out::println);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}
