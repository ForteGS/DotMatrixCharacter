///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Dot Matrix Character 
// File:             MessageLoop.java
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
 * This class is responsible to create a data structure called MessageLoop that
 * used DblListnode as the main implementation.
 * 
 * @author root
 * 
 * @param <E>
 */
public class MessageLoop<E> implements LoopADT<E> {
	/**
	 * The actual looping object which contains the text.
	 */
	private DblListnode<E> msgLoop;

	/**
	 * Number of items in this message loop.
	 */
	private int numItems;

	/**
	 * The current node.
	 */
	private DblListnode<E> curr;

	/**
	 * Constructor: initialize msgLoop to a new DblListnode and number of items
	 * to zero.
	 */
	public MessageLoop() {
		msgLoop = new DblListnode<E>();
		numItems = 0;
	}

	/**
	 * Adding item to the list of empty nodes, increments the number of items to
	 * 1 and set the current item to the newly created node.
	 * 
	 * @param item
	 */
	private void add(E item) {
		msgLoop.setData(item);
		msgLoop.setNext(msgLoop);
		msgLoop.setPrev(msgLoop);
		numItems++;
		curr = msgLoop;
		return;
	}

	/**
	 * Adding item before the current item. increments the number of items, and
	 * set the current item to the newly created node.
	 * 
	 * @param item
	 */
	public void addBefore(E item) {
		if (numItems == 0)
			add(item);
		else if (numItems == 1) {
			DblListnode<E> newItem = new DblListnode<E>(curr.getPrev(), curr,
					item);
			curr.getPrev().setNext(newItem);
			curr.setPrev(newItem);
			curr = newItem;
			msgLoop = curr;
		} else {
			DblListnode<E> newItem = new DblListnode<E>(curr.getPrev(), curr,
					item);
			curr.getPrev().setNext(newItem);
			curr.setPrev(newItem);
			curr = newItem;
		}
		numItems++;
	}

	/**
	 * Adding item after the current item. increments the number of items, and
	 * set the current item to the newly created node.
	 * 
	 * @param item
	 */
	public void addAfter(E item) {
		if (numItems == 0)
			add(item);
		else {
			DblListnode<E> newItem = new DblListnode<E>(curr, curr.getNext(),
					item);
			curr.getNext().setPrev(newItem);
			curr.setNext(newItem);
			curr = newItem;
			numItems++;
		}
	}

	/**
	 * Return the data of the current item.
	 * 
	 * @return the data of the current item.
	 */
	public E getCurrent() {
		return curr.getData();
	}

	/**
	 * Removes and returns the current item. The item immediately after the
	 * removed item then becomes the current item. If the Loop is empty
	 * initially, an EmptyLoopException is thrown.
	 * 
	 * @return the removed item
	 * @throws EmptyLoopException
	 *             if the Loop is empty
	 */
	public E removeCurrent() {
		E tmp = curr.getData();
		if (tmp == null || numItems == 0)
			throw new EmptyLoopException();
		if (numItems == 1) {
			msgLoop = new DblListnode<E>();
			numItems--;
			return tmp;
		}

		curr.getPrev().setNext(curr.getNext());
		curr.getNext().setPrev(curr.getPrev());
		curr = curr.getNext();
		numItems--;
		return tmp;
	}

	/**
	 * Advances the current item forward one item resulting in the item that is
	 * immediately after the current item becoming the current item.
	 */
	public void forward() {
		if (numItems == 0)
			return;
		curr = curr.getNext();
	}

	/**
	 * Moves the current item backwards one item resulting in the item that is
	 * immediately before the current item becoming the current item.
	 */
	public void back() {
		if (numItems == 0)
			return;
		curr = curr.getPrev();
	}

	/**
	 * Returns the number of items in this message loop.
	 * 
	 * @return the number of items in this message loop
	 */
	public int size() {
		return numItems;
	}

	/**
	 * Returns an iterator for this Loop. Rather that using the Iterable
	 * interface we'll combine it with the ADT.
	 * 
	 * @return an iterator for this Loop
	 */
	public Iterator<E> iterator() {
		return new MessageLoopIterator(msgLoop);
	}
}
