/**
 *
 *  @author Olesiński Wojciech S22368
 *
 */

package zad1;

import java.nio.file.Paths;

public class Main {
	
	public static long exec() {
		Magazyn magazyn = new Magazyn();
		Czytnik czytnik = new Czytnik(magazyn, Paths.get("../Towary.txt"));
		SumaWag suma = new SumaWag(magazyn);
		  
		long startTime = System.currentTimeMillis();
		Thread t1 = new Thread(czytnik);
		Thread t2 = new Thread(suma);
		  
		t1.start();
		t2.start();
		  
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		  

		long endTime = System.currentTimeMillis();
//		System.err.println("Execution time: " + (endTime - startTime)+"ms");
		return endTime - startTime;
		  
	}
	
  public static void main(String[] args) {
	  exec();
		/*
		 * List<Long> executionTimes = new ArrayList<>(); for (int i = 0; i < 1000; i++)
		 * { executionTimes.add(exec()); } System.out.println("\n\n"); long sum = 0; for
		 * (long el: executionTimes) sum += el;
		 * System.out.println("Mean execution time: " +
		 * ((double)sum/executionTimes.size()));
		 */
	  
  }
}
