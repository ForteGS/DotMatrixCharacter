/**
 * This class implements the doubly linked list data structure.
 * @author Minh Bui
 * @param <E>
 */

public class DblListnode<E> {

	/**
	 * The previous node.
	 */
	private DblListnode<E> prev;

	/**
	 * The next node.
	 */
	private DblListnode<E> next;

	/**
	 * The data object of this node.
	 */
	private E data;

	/**
	 * Constructor with full parameters.
	 * 
	 * @param prev
	 *            The previous node
	 * @param next
	 *            The next node
	 * @param data
	 *            The data object in this node
	 */
	public DblListnode(DblListnode<E> prev, DblListnode<E> next, E data) {
		this.prev = prev;
		this.next = next;
		this.data = data;
	}

	/**
	 * Constructor with no parameters.
	 */
	public DblListnode() {
		this(null, null, null);
	}

	/**
	 * Constructor with only data provided.
	 * 
	 * @param data
	 */
	public DblListnode(E data) {
		this(null, null, data);
	}

	/**
	 * Access the data in this node.
	 * 
	 * @return data
	 */
	public E getData() {
		return this.data;
	}

	/**
	 * Get the next node of this node.
	 * 
	 * @return the next node.
	 */
	public DblListnode<E> getNext() {
		return next;
	}

	/**
	 * Get the previous node of this node.
	 * 
	 * @return
	 */
	public DblListnode<E> getPrev() {
		return prev;
	}

	/**
	 * Change the data in this node.
	 * 
	 * @param data
	 */
	public void setData(E data) {
		this.data = data;
	}

	/**
	 * Change the address of the next node of this node.
	 * 
	 * @param next
	 */
	public void setNext(DblListnode<E> next) {
		this.next = next;
	}

	/**
	 * Change the address of the previous node of this node.
	 * 
	 * @param prev
	 */
	public void setPrev(DblListnode<E> prev) {
		this.prev = prev;
	}
}
