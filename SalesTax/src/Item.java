public class Item {
	private String name;
	private double price;
	private boolean isImported;
	private int quantity;
	private boolean isExempt;
	private double salesTax;
	private double importTax;

	// Sales tax and import tax constants
	private final double salesTaxRate = 0.10;
	private final double importTaxRate = 0.05;

	// This is the 'blueprint' of how each item will be constructed
	public Item( String name, double price, boolean isImported, int quantity, boolean isExempt) {
		this.setName(name);
		this.setPrice(price);
		this.setImported(isImported);
		this.setQuantity(quantity);
		this.setExempt(isExempt);

		// When our items are 'initialized', we'll know if it is exempt or imported
		if (isExempt) {
			setSalesTax(0);
		} else {
			setSalesTax(salesTaxRate * price);
		}

		if (isImported) {
			setImportTax(importTaxRate * price);
		} else {
			setImportTax(0);
		}
	}

	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isImported() {
		return isImported;
	}

	public void setImported(boolean isImported) {
		this.isImported = isImported;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isExempt() {
		return isExempt;
	}

	public void setExempt(boolean isExempt) {
		this.isExempt = isExempt;
	}
	
	// We use subtraction and the floor to get the value of 
	// the second digit to the right of the decimal with all other digits being zero
	private double roundToNearestFiveCents(double x) {
		double temp = ((x * 10) - Math.floor(x * 10)) / 10;

		// We round to the nearest 5cents by adding the difference
		// It will round to 0.00, 0.05 or 0.10
		// If the value of 'temp' is between 0.05 and 0.10, the difference will be 0.10-value
		// If the vaule of 'temp' is between 0.00 and 005, the difference will be 0.05-value
		// If 'temp' is exactly 0.00 the difference is 0.00
		if (temp > 0.05) {
			temp = 0.10 - temp;
		} else if (temp > 0) {
			temp = 0.05 - temp;
		} else {
			temp = 0;
		}
		return x + temp;
	}

	public double getSalesTax() {
		return salesTax;
	}

	
	// We multiply 'salesTax' by 100 to take advantage of the floor. 
	// floor will remove everything after the decimal, so we need to move the decimal to the right (multiply)
	// and then we need to move the decimal back to the left (divide) so we can preserve any values after the decimal
	public void setSalesTax(double salesTax) {
		this.salesTax = roundToNearestFiveCents(salesTax);
		this.salesTax = (Math.floor(this.salesTax * 100)) / 100;
	}

	public double getImportTax() {
		return importTax;
	}

	public void setImportTax(double importTax) {
		this.importTax = roundToNearestFiveCents(importTax);
	}

	public double getFinalPrice() {
		return importTax + salesTax + price;
	}
}
