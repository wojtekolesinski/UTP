/**
 *
 *  @author Olesiński Wojciech S22368
 *
 */

package zad1;


public class Purchase {
	private String clientId;
	private String clientName;
	private String commodityName;
	private double price;
	private double quantity;
	private double totalCost;
	
	public Purchase(String clientId, String clientName, String commodityName, double price, double quantity) {
		this.clientId = clientId;
		this.clientName = clientName;
		this.commodityName = commodityName;
		this.price = price;
		this.quantity = quantity;
		this.totalCost = price * quantity;
	}
	
	@Override
	public String toString() {
		return clientId + ";" + clientName + ";" + commodityName + ";" + price + ";" + quantity;
	}
	
	public void printWithCost() {
		System.out.println(this + " (koszt: " + totalCost + ")");
	}

	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	
	
	
}
