import java.util.ArrayList;

public class Cart {
	private ArrayList<Item> items = new ArrayList<Item>();

	//	Adding to the items array
	public void addToCart(Item item) {
		items.add(item);
	}

	public String getReceipt() {
		String tempString = "";
		double tempSalesTax = 0;
		double tempTotalPrice = 0;

		// Get all of the items in our 'items' ArrayList
		for (int x = 0; x < items.size(); x++) {
			Item itemx = items.get(x);

			//Start building out our items by appending to our 'temp' variables and calling methods on our 'itemx' variable
			tempString += itemx.getQuantity() + " " + itemx.getName() + " : " + String.format( "%.2f", itemx.getFinalPrice()) + "\n";
			tempSalesTax += itemx.getSalesTax() + itemx.getImportTax();
			tempTotalPrice += itemx.getFinalPrice();
		}
		// String format() gives us a nice looking decimal format with two decimal places
		tempString += "Sales Taxes: " + String.format( "%.2f", tempSalesTax) + "\n";
		tempString += "Total Price: " + String.format( "%.2f", tempTotalPrice);
		
		return tempString;
	}
}

