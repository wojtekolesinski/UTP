package zad1;

public class SumaWag implements Runnable {
	
	private Magazyn magazyn;
	private double suma;
	

	public SumaWag(Magazyn magazyn) {
		this.magazyn = magazyn;
		suma = 0;
	}

	@Override
	public void run() {
		for (int i = 1; magazyn.oczekujeWiecejDostaw() || !magazyn.pusty(); i++) {
			suma += magazyn.rozladuj().getWaga();
			if (i % 100 == 0) {
				System.out.println("policzono wage "+i+" towarów");
			}
		}
		System.out.println(suma);
	}

}
