import java.util.Iterator;

public class MessageLoopIterator<E> implements Iterator<E> {

	/**
	 * An actual pointer to traverse through the doubleListnode.
	 */
	DblListnode<E> tmp;

	/**
	 * A reference object which points to the first node of the double nodes.
	 */
	DblListnode<E> first;

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
		tmp = tmp.getNext();
		return tmp.getData();
	}

	/**
	 * Try to see if the next node is available or not. Since this is a circular
	 * doubly list nodes, if the next node is equal to the first one in the list
	 * Return false.
	 * 
	 * @return True if there is available next node and false otherwise.
	 */
	public boolean hasNext() {
		return !tmp.getNext().equals(first);
	}

	/**
	 * Implemented the remove operation of Iterator<E> interface, but it is not
	 * supported in this program.
	 */
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
