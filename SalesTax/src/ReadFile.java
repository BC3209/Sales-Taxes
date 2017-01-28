import java.io.*;
import java.util.*;

public class ReadFile {
	// Create an ArrayList and initialize it. These are all of our exempt words
	private	static final ArrayList<String> exemptKeywords = new ArrayList<String>() {{
	    add("pills");
	    add("book");
	    add("chocolate");
	    add("chocolates");
	}};

	
 public static Cart parseFile(String file) throws FileNotFoundException {
	  Scanner read;
	  Cart cart;

	  
	  read = new Scanner(new File(file));
	  cart = new Cart();

	  while(read.hasNextLine()) {
		  String readTheNextLine = read.nextLine();
		  String[] temp = readTheNextLine.split("\\s");
		  String name = "";

		  boolean isImported = false;
		  boolean isExempt = false;
		  
		  

		  // Starting from index 1 and going to temp.length - 2 gives us the name of the item
		  // We start at index 1 because we want to skip the quantity 
		  // We stop at length - 2 because that is where the word "at" is located
		  for (int x = 1; x < temp.length - 2; x++) {
			  name += " " + temp[x];
			   
						  
			  if (temp[x].trim().equals("imported") ) {
				  isImported = true;
			  }
			  if (exemptKeywords.contains(temp[x].trim().toLowerCase())) {
				  isExempt = true;
			  }
		  }
		  // Building our item based on the Item constructor 
		  Item item1  = new Item(name.trim(), Double.parseDouble(temp[temp.length-1]), isImported, Integer.parseInt(temp[0]), isExempt);		  
		  cart.addToCart(item1);
	 }
	  
	  // Close file
	  read.close();
	  return cart;
  }
}
