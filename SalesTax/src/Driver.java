import java.io.FileNotFoundException;

public class Driver {

	public static void main(String[] args) {
		try {
			// We can pass in as many command line arguments as we'd like
			for (int x = 0; x < args.length; x++) {				
				Cart cart = ReadFile.parseFile(args[x]);
				System.out.println(cart.getReceipt() + "\n");
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

