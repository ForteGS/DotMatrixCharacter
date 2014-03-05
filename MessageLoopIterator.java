///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Dot Matrix Character 
// File:             MessageLoopIterator.java
// Semester:         CS367 Spring 2014
//
// Author:           Minh Bui
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
// CS Login:         null
// Lecturer's Name:  null
// Lab Section:      null
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          null
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.Iterator;

/**
 * An Iterator for the MessageLoop data structure.
 * 
 * @author Minh Bui
 * 
 * @param <E>
 */
public class MessageLoopIterator<E> implements Iterator<E> {

	/**
	 * An actual pointer to traverse through the doubleListnode.
	 */
	private DblListnode<E> tmp;

	/**
	 * A reference object which points to the first node of the double nodes.
	 */
	private DblListnode<E> first;

	private int counter = 0;

	/**
	 * Constructor: receive a Double List node as parameter.
	 * 
	 * @param dbl
	 */
	public MessageLoopIterator(DblListnode<E> dbl) {
		tmp = dbl;
		first = dbl;
	}

	/**
	 * Advance to the next node and return the data of this node.
	 * 
	 * @return an object which is the data of the node.
	 */
	public E next() {
		E data;
		if (counter == 0) {
			data = tmp.getData();
		} else {
			tmp = tmp.getNext();
			data = tmp.getData();
		}
		counter++;
		return data;
	}

	/**
	 * Try to see if the next node is available or not. Since this is a circular
	 * doubly list nodes, if the next node is equal to the first one in the list
	 * Return false.
	 * 
	 * @return True if there is available next node and false otherwise.
	 */
	public boolean hasNext() {
		return (!tmp.getNext().equals(first) || counter == 0);
	}

	/**
	 * Implemented the remove operation of Iterator<E> interface, but it is not
	 * supported in this program.
	 */
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
