/**
 *
 *  @author Olesiński Wojciech S22368
 *
 */

package zad1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CustomersPurchaseSortFind {
	private List<Purchase> purchases;

	public CustomersPurchaseSortFind() {
		purchases = new ArrayList<Purchase>();
	}
	
	public void readFile(String fname) {
		try (
			FileReader fr = new FileReader(new File(fname));
			BufferedReader br = new BufferedReader(fr)
		) {
			
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(";");
				purchases.add(new Purchase(
					data[0], data[1], data[2], Double.parseDouble(data[3]), Double.parseDouble(data[4])
				));
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void showSortedBy(String what) {
		
		System.out.println(what);
		switch (what) {
			case "Nazwiska":
				purchases.stream()
							.sorted((p1, p2) -> {
								return p1.getClientName().equals(p2.getClientName()) ?
										p1.getClientId().compareTo(p2.getClientId()) :
										p1.getClientName().compareTo(p2.getClientName());
							})
							.forEach(System.out::println);
				break;
			case "Koszty":
				purchases.stream()
				.sorted((p1, p2) -> {
					return p1.getTotalCost() == p2.getTotalCost() ? 
							p1.getClientName().compareTo(p2.getClientName()) :
							(int)(p2.getTotalCost() - p1.getTotalCost());
				})
				.forEach(Purchase::printWithCost);
				break;
			default:
				break;
		}
		System.out.println();
		
	}
	
	public void showPurchaseFor(String id) {
		System.out.println("Klient " + id);
		purchases.stream()
			.filter(p -> p.getClientId().equals(id))
			.forEach(System.out::println);
		
		System.out.println();
	}
	
	
}
