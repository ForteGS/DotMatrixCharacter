///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Dot Matrix Character 
// Files:            DisplayEditor.java
//					 DblListnode.java
//					 DotMatrix.java
//					 EmptyLoopException.java
//					 LoopADT.java
//					 MessageLoop.java
//					 MessageLoopIterator.java
//					 UnrecognizedCharacterException.java

// Semester:         CS367 Spring 2014
//
// Author:           Minh Bui
// Email:            mbui2@wisc.edu
// CS Login:         minh
// Lecturer's Name:  Jim Skrentny
// Lab Section:      null
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If allowed, learn what PAIR-PROGRAMMING IS, 
//                   choose a partner wisely, and complete this section.
//
// Pair Partner:     null
// Email:            null
// CS Login:         null
// Lecturer's Name:  null
// Lab Section:      null
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          null
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * This class is the main class which prompts the users' input.
 * 
 * @author Minh Bui
 */

public class DisplayEditor {
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		/**
		 * When exit is true, the program will terminate.
		 */
		boolean exit = false;

		/**
		 * The file name of the alphabet.
		 */
		String alphabet = "alphabets.txt";

		/**
		 * Create an empty Loop of characters.
		 */
		LoopADT<List<String>> msgLoop = new MessageLoop<List<String>>();

		// String s = "h";

		DotMatrix dm = new DotMatrix();
		dm.loadAlphabets(alphabet);

		// printList(dm.getDotMatrix(s));

		while (!exit) {
			System.out.print("enter command (? for help)> ");
			String cmd = in.nextLine().trim();
			String parameter = cmd.substring(1);

			switch (cmd.charAt(0)) {
			/**
			 * This option prints out the list of available commands for users.
			 */
			case '?': {
				System.out.println("s (save)    l (load)       d (display)");
				System.out.println("n (next)    p (previous)   j (jump)");
				System.out.println("x (delete)  a (add after)  "
						+ "i (insert before)");
				System.out.println("c (context) r (replace)    q (quit)");
				break;
			}

			/**
			 * This option saves the message to the file with specified name. If
			 * the file name already exist, display an overwriting warning
			 * message before saving the message. If the filename cannot be
			 * written to, display the message.
			 */
			case 's': {
				// TODO: Implement this option.
				if (msgLoop.size() == 0)
					System.out.println("no messages to save");
				else {

				}
				break;
			}

			/**
			 * This option loads the message. If the specified file name does
			 * not exist or cannot be read from, display "unable to load".
			 * Otherwise, load the message from the file until the entire file
			 * is read. Set the current character to be the first character read
			 * from the file. Assume that the file content is already in the
			 * correct format.
			 */
			case 'l': {
				// TODO: Implement this option.
				break;
			}

			/**
			 * This option displays the message. If the message loop is empty,
			 * display "no messages". Otherwise, display the message in the
			 * loop, starting with the current character in a 7 x 5 dot-matrix
			 * display (going forward through the entire loop). Each character
			 * must be separated from the other characters by a blank line.
			 */
			case 'd': {
				// TODO: Implement this option.
				break;
			}

			/**
			 * This option goes forward to the next character in the loop and
			 * displays the current context. If the message loop is empty,
			 * display "no messages".
			 */
			case 'n': {
				msgLoop.forward();
				displayCurrContext(msgLoop);
				break;
			}

			/**
			 * This option goes back to the previous character in the loop and
			 * display the current context. If the message loop is empty,
			 * displays "no messages".
			 */
			case 'p': {
				msgLoop.back();
				displayCurrContext(msgLoop);
				break;
			}

			/**
			 * This option "jumps" N characters in the loop (forward if N > 0,
			 * backward if N < 0) and displays the current context. If the
			 * message loop is empty, displays "no messages".
			 */
			case 'j': {
				// TODO: Implement this option.
				
				break;
			}

			/**
			 * Deletes the current character in the message. If the message loop
			 * becomes empty as a result of the removal, display "no messages".
			 * Otherwise, make the character after the removed character the
			 * current character and displays the current context.
			 */
			case 'x': {
				if (msgLoop.size() == 0)
					System.out.println("no messages");
				else	{
					msgLoop.removeCurrent();
					if (msgLoop.size() == 0)
						System.out.println("no messages");
				}
				break;
			}

			/**
			 * If the message loop is empty, add all the characters in the
			 * message to the message loop, one character per node converted to
			 * its dot matrix representation, and added one after another.
			 * Otherwise, add these characters in the same manner immediately
			 * after the current character. In either case, the current
			 * character will become the last character that was added and then
			 * display the current context.
			 * 
			 * If any of the characters in the message is an invalid character,
			 * it won't be able to be converted to the dot matrix representation
			 * so add nothing; instead display
			 * "An unrecognized character has been entered." and then return to
			 * the menu prompt. An invalid character is not defined in the dot
			 * mapping file alphabets.txt
			 */
			case 'a': {
				// TODO: Implement this option.
				if (msgLoop.size() == 0) {
					try {
						for (char c : parameter.toCharArray()) {
							if (!dm.isValidCharacter(c + "")) {
								throw new UnrecognizedCharacterException();
							} else {
								msgLoop.addAfter(dm.getDotMatrix(c + ""));
							}
						}
					} catch (UnrecognizedCharacterException e) {
						System.out.println("An unrecognized character "
								+ "has been entered.");
						break;
					}
				}
				break;
			}

			/**
			 * If the message loop is empty, insert all the characters in the
			 * message to the message loop, one character per node converted to
			 * its dot matrix representation, and inserted one before another.
			 * Otherwise, insert these characters in the same manner immediately
			 * before the current character. In either case, the current
			 * character will become the last character that was inserted and
			 * then display the current context.
			 * 
			 * If any of the characters in the message is an invalid character,
			 * it won't be able to be converted to the dot matrix representation
			 * so insert nothing; instead display
			 * "An unrecognized character has been entered." and then return to
			 * the menu prompt. An invalid character is not defined in the dot
			 * mapping file alphabets.txt
			 */
			case 'i': {
				// TODO: Implement this option.
				break;
			}

			/**
			 * Display the current context.
			 */
			case 'c': {
				// TODO: Implement this option.
				break;
			}

			/**
			 * If the message loop is empty, display "no messages". Otherwise,
			 * replace the current character with the new character and display
			 * the current context (see note below). If the character entered is
			 * invalid display "An unrecognized character has been entered." and
			 * don't replace the message in the message loop. The program should
			 * continue with it's normal operation.
			 */
			case 'r': {
				// TODO: Implement this option.
				break;
			}

			/**
			 * Display "quit" and quit the program.
			 */
			case 'q': {
				exit = true;
				in.close();
				System.out.print("quit");
			}
			default:
				System.out.println("invalid command");
				break;
			}
		}
		in.close();
	}

	/**
	 * This function prints out the elements in a list of strings.
	 * 
	 * @param l
	 */
	private static void printList(List<String> l) {
		Iterator<String> iterL = l.iterator();
		while (iterL.hasNext()) {
			System.out.println(iterL.next());
		}
	}

	/**
	 * This function receives a message loop object and prints out the current
	 * context of that message loop object.
	 * 
	 * @param msgLoop
	 */
	private static void displayCurrContext(LoopADT<List<String>> msgLoop) {
		if (msgLoop.size() == 0)	
			System.out.println("no messages");
		else if (msgLoop.size() == 1)	{
			printSpecialString(10, "*");
			printList(msgLoop.getCurrent());
			printSpecialString(10, "*");
		}
		else if (msgLoop.size() == 2){
			printSpecialString(10, "*");
			printList(msgLoop.getCurrent());
			printSpecialString(10, "*");
			msgLoop.forward();
			printList(msgLoop.getCurrent());
			msgLoop.back();
		} else	{
			msgLoop.back();
			printList(msgLoop.getCurrent());
			msgLoop.forward();
			printSpecialString(10, "*");
			printList(msgLoop.getCurrent());
			printSpecialString(10, "*");
			msgLoop.forward();
			printList(msgLoop.getCurrent());
			msgLoop.back();
		}
	}
	
	/**
	 * This function receives a string s as an object to print and an integer n
	 * indicate how many times should it be printed.
	 * 
	 * @param n the number of time s should be printed
	 * @param s the string needs to be printed
	 */
	private static void printSpecialString(int n, String s){
		for (int i = 0; i < n; i++){
			System.out.print(s);
		}
		System.out.println();
	}
}
