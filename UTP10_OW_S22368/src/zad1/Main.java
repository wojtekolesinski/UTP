/**
 *
 *  @author Olesiński Wojciech S22368
 *
 */

package zad1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

  public static void main(String[] args) {
	  Magazyn magazyn = new Magazyn();
	  Czytnik czytnik = new Czytnik(magazyn, Paths.get("../Towary.txt"));
	  SumaWag suma = new SumaWag(magazyn);
	  
	  ExecutorService executor = Executors.newFixedThreadPool(2);
	  executor.submit(czytnik);
	  executor.submit(suma);
	  
	  
//	  Thread czytnikThread = new Thread(czytnik);
//	  Thread sumaThread = new Thread(suma);
//	  
//	  czytnikThread.start();
//	  sumaThread.start();
//	  
//	  try {
//		czytnikThread.join();
//		sumaThread.join();
//	} catch (InterruptedException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
  }
}
