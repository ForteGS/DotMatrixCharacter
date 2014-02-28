import java.util.Iterator;

public class TestMessageLoop {
	public static void main(String[] args) {
		// Create an instance
		MessageLoop<String> msgLoop = new MessageLoop<String>();

		// Testing adding method
		System.out.println("***TESTING add METHOD***");
		msgLoop.add("Minh");
		System.out.println("Expected result: Minh -->");
		System.out.println("Result:");
		printMessageLoop(msgLoop);
		System.out.println();

		// Testing addBefore method
		System.out.println("***TESTING addBefore method***");
		msgLoop.addBefore("Bui");
		System.out.println("Expected result: Bui --> Minh -->");
		System.out.println("Result:");
		printMessageLoop(msgLoop);
		System.out.println("The current item will become " + "\"Bui\"");
		System.out.println("Result: " + msgLoop.getCurrent());
		System.out.println();
		System.out.println("***TESTING addBefore method at the beginning***");
		System.out.println("The nodes currently: Bui --> Minh -->");
		msgLoop.addBefore("Heh");
		System.out.println("Expected: Heh --> Bui --> Minh");
		System.out.println("Got: ");
		printMessageLoop(msgLoop);
		System.out.println("Current node expected: Heh");
		System.out.println("Got: " + msgLoop.getCurrent());
		System.out.println();

		// Testing addAfter method
		System.out.println("***TESTING addAfter method***");
		msgLoop.addAfter("Duc");
		System.out.println("Expected result: Heh --> Duc --> Bui --> Minh -->");
		System.out.println("Result:");
		printMessageLoop(msgLoop);
		System.out.println("The current item will become " + "\"Duc\"");
		System.out.println("Result: " + msgLoop.getCurrent());
		System.out.println();

		// Testing getCurrent method
		System.out.println("***TESTING getCurrent method***");
		System.out.println("Expected result: Duc");
		System.out.println("Result:");
		System.out.println(msgLoop.getCurrent());
		System.out.println();

		// Testing removeCurrent method
		System.out.println("***TESTING removeCurrent method***");
		System.out.println("Expected result: Duc");
		System.out.println("Result: ");
		System.out.println(msgLoop.removeCurrent());
		System.out.println("Expected current item after removal: Bui");
		System.out.println("Result: " + msgLoop.getCurrent());
		System.out.println();

		// Testing forward method
		System.out.println("***TESTING forward method***");
		System.out.println("Current: " + msgLoop.getCurrent());
		msgLoop.forward();
		System.out.println("Current item after forward: "
				+ msgLoop.getCurrent());
		System.out.println();

		// Testing back method
		System.out.println("***TESTING back method***");
		System.out.println("Current: " + msgLoop.getCurrent());
		msgLoop.back();
		System.out.println("Current item after forward: "
				+ msgLoop.getCurrent());
		System.out.println();

		// Testing size() method
		System.out.println("***TESTING size method***");
		System.out.println("Expected result: 3");
		System.out.println("Got: " + msgLoop.size());
		System.out.println();

	}

	/**
	 * Print out the contents of the nodes in message loop object. This testing
	 * case also test the iterating object generated from this class.
	 * 
	 * @param msgLoop
	 */
	private static <E> void printMessageLoop(MessageLoop<E> msgLoop) {
		Iterator<E> iterMsg = msgLoop.iterator();

		while (iterMsg.hasNext()) {
			E data = iterMsg.next();
			System.out.print(data + "--> ");
		}
		System.out.println();
	}
}
