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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

		if (args.length == 1 || args.length > 2) {
			System.err.println("invalid command-line arguments");
			System.exit(1);
		}

		if (args.length == 0) {
			System.out.print("Enter the dot-matrix alphabets file:");
			alphabet = in.next();
			in.nextLine();
		}
		/**
		 * Code forked from CS367 page. Create a scanner of the file named as
		 * the first given argument. If fail to open file, switch scanner to the
		 * input stream.
		 * 
		 * @author: CS367 TAs
		 */
		boolean useFile = args.length == 2;
		if (useFile) {
			File inFile = new File(args[0]);
			alphabet = args[1];
			if (!inFile.exists() || !inFile.canRead()) {
				System.err.println("Problem with input file!");
				System.exit(1);
			}
			try {
				in = new Scanner(inFile);
			} catch (FileNotFoundException e) {
				System.err.println("Problem with input file!");
				System.exit(1);
			}
		} else
			;

		/**
		 * Load up the dot matrix tree map for each character in the alphabet.
		 */
		DotMatrix dm = new DotMatrix();
		dm.loadAlphabets(alphabet);

		while (!exit) {
			System.out.print("enter command (? for help)> ");

			String cmd = in.nextLine();
			String parameter = null;
			char option = cmd.charAt(0);

			/**
			 * Some options require arguments
			 */
			if (cmd.length() > 1)
				parameter = cmd.substring(2);

			if (args.length == 2) {
				System.out.println(cmd);
			}
			switch (option) {

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
				if (!guard(parameter))
					System.out.println("invalid command");
				else {

					if (msgLoop.size() == 0)
						System.out.println("no messages to save");
					else {
						try {
							File saveFile = new File(parameter);
							String special = printSpecialString(10, "#");

							if (saveFile.exists()) {
								System.out.println("warning: file already "
										+ "exists, will be overwritten");
							}

							PrintWriter out = new PrintWriter(saveFile);

							Iterator<List<String>> msgIter = msgLoop.iterator();
							while (msgIter.hasNext()) {
								List<String> item = msgIter.next();
								out.print(printList(item));
								out.println(special);
							}

							out.close();
						} catch (FileNotFoundException e) {
							System.out.println("unable to save");
						}
					}
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
				if (!guard(parameter)) {
					System.out.println("invalid command");
				} else {
					try {
						File input = new File(parameter);
						Scanner fileIn = new Scanner(input);

						msgLoop = new MessageLoop<List<String>>();
						List<String> character = new ArrayList<String>();

						// For each 7 lines, we add a character, ignoring the
						// "##########" line.
						int count = 0;
						while (fileIn.hasNext()) {
							String c = fileIn.nextLine();
							if (count < 7) {
								character.add(c.substring(0));
								count++;
							} else {
								msgLoop.addAfter(character);
								character = new ArrayList<String>();
								count = 0;
							}
						}

						// Since we are at the last node position, we want the
						// current item to be the first added item.
						msgLoop.forward();

						fileIn.close();
					} catch (FileNotFoundException e) {
						System.out.println("unable to load");
					}
				}
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
				if (msgLoop.size() == 0)
					System.out.println("no messages");
				else {
					Iterator<List<String>> msgIter = msgLoop.iterator();
					System.out.println();
					while (msgIter.hasNext()) {
						List<String> item = msgIter.next();
						System.out.println(printList(item));
					}
				}
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
				if (!guard(parameter))
					System.out.println("invalid command");
				else if (msgLoop.size() == 0)
					System.out.println("no messages");
				else {
					String n = parameter.substring(0);

					try {
						int N = Integer.parseInt(n);
						if (N >= 0) {
							for (int i = 0; i < N; i++) {
								msgLoop.forward();
							}
						} else {
							for (int i = 0; i < Math.abs(N); i++) {
								msgLoop.back();
							}
						}
						displayCurrContext(msgLoop);
						break;
					} catch (Exception e) {
						System.out.println("invalid command");
					}

				}
				break;
			}
			/**
			 * Deletes the current character in the message. If the message loop
			 * becomes empty as a result of the removal, display "no messages".
			 * Otherwise, make the character after the removed character the
			 * current character and displays the current context.
			 */
			case 'x': {
				if (parameter != null) {
					System.out.println("invalid command");
					break;

				} else {
					if (msgLoop.size() != 0)
						msgLoop.removeCurrent();
				}
				displayCurrContext(msgLoop);
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
				if (!guard(parameter)) {
					System.out.println("invalid command");
				} else {

					try {
						for (char c : parameter.toCharArray()) {
							if (!dm.isValidCharacter(c + ""))
								throw new UnrecognizedCharacterException();
						}

						for (char c : parameter.toCharArray()) {
							msgLoop.addAfter(dm.getDotMatrix(c + ""));
						}
						displayCurrContext(msgLoop);
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
				if (!guard(parameter)) {
					System.out.println("invalid command");
				} else {
					try {
						for (char c : parameter.toCharArray()) {
							if (!dm.isValidCharacter(c + ""))
								throw new UnrecognizedCharacterException();
						}

						for (char c : parameter.toCharArray()) {
							msgLoop.addBefore(dm.getDotMatrix(c + ""));
						}
						displayCurrContext(msgLoop);
					} catch (UnrecognizedCharacterException e) {
						System.out.println("An unrecognized character "
								+ "has been entered.");
						break;
					}

				}
				break;
			}

			/**
			 * Display the current context.
			 */
			case 'c': {
				if (msgLoop.size() == 0)
					System.out.println("no messages");
				else
					displayCurrContext(msgLoop);

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
				if (!guard(parameter) || parameter.length() > 1) {
					System.out.println("invalid command");

				} else {
					if (msgLoop.size() == 0)
						System.out.println("no messages");
					else {
						try {
							msgLoop.removeCurrent();
							String c = parameter.substring(0, 1);
							if (!dm.isValidCharacter(c))
								throw new UnrecognizedCharacterException();
							else
								msgLoop.addBefore(dm.getDotMatrix(c));
							displayCurrContext(msgLoop);
						} catch (UnrecognizedCharacterException e) {
							System.out.println("An unrecognized "
									+ "character has been entered.");
						}
					}
				}
				break;
			}

			/**
			 * Display "quit" and quit the program.
			 */
			case 'q': {
				exit = true;
				in.close();
				System.out.println("quit");
				break;
			}
			default:
				System.out.println("invalid command");
				break;
			}
		}
	}

	/**
	 * This function prints out the elements in a list of strings.
	 * 
	 * @param l
	 */
	private static String printList(List<String> l) {
		Iterator<String> iterL = l.iterator();
		String character = "";
		while (iterL.hasNext()) {
			String item = iterL.next() + "\n";
			character += item;
		}
		return character;
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
		else if (msgLoop.size() == 1) {
			System.out.println(printSpecialString(10, "*"));
			System.out.print(printList(msgLoop.getCurrent()));
			System.out.println(printSpecialString(10, "*"));
		} else if (msgLoop.size() == 2) {
			System.out.println(printSpecialString(10, "*"));
			System.out.print(printList(msgLoop.getCurrent()));
			System.out.println(printSpecialString(10, "*"));
			msgLoop.forward();
			System.out.print(printList(msgLoop.getCurrent()));
			msgLoop.back();
		} else {
			msgLoop.back();
			System.out.print(printList(msgLoop.getCurrent()));
			msgLoop.forward();
			System.out.println(printSpecialString(10, "*"));
			System.out.print(printList(msgLoop.getCurrent()));
			System.out.println(printSpecialString(10, "*"));
			msgLoop.forward();
			System.out.print(printList(msgLoop.getCurrent()));
			msgLoop.back();
		}
	}

	/**
	 * This function receives a string s as an object to print and an integer n
	 * indicate how many times should it be printed.
	 * 
	 * @param n
	 *            the number of time s should be printed
	 * @param s
	 *            the string needs to be printed
	 */
	private static String printSpecialString(int n, String s) {
		String special = "";
		for (int i = 0; i < n; i++) {
			special += s;
		}
		return special;
	}

	/**
	 * Return false if parameter is null.
	 * 
	 * @param parameter
	 * @return
	 */
	private static <E> boolean guard(E parameter) {
		return !(parameter == null);
	}
}
