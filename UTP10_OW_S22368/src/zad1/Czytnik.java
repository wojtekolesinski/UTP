package zad1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

public class Czytnik implements Runnable {
	
	private Magazyn magazyn;
	private Path sciezka;
	
	public Czytnik(Magazyn magazyn, Path sciezka) {
		this.magazyn = magazyn;
		this.sciezka = sciezka;
	}



	@Override
	public void run() {
		Iterator<String> lines = null;
		try {
			lines = Files.lines(sciezka).iterator();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		for (int i = 1; lines.hasNext(); i++) {
			String[] data = lines.next().split(" ");
			magazyn.zaladuj(new Towar(Integer.parseInt(data[0]), Double.parseDouble(data[1])));
			
			if (i % 200 == 0) {
				System.out.println("utworzono "+i+" obiektów");
			}
		}
		
		magazyn.zakonczDostawe();
		
	}

}
