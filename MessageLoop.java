import java.util.Iterator;


public class MessageLoop<E> implements LoopADT<E>, Iterator<E> {
	private DblListnode<E> msgLoop;
	private int numItems;
	private DblListnode<E> curr;
	
	public MessageLoop()	{
		msgLoop = new DblListnode<E>();
		numItems = 0;
	}
	
	public void add(E item)	{
		msgLoop.setData(item);
		msgLoop.setNext(msgLoop);
		msgLoop.setPrev(msgLoop);
		numItems++;
		curr = msgLoop;
	}
	
	public void addBefore(E item)	{
		if (numItems == 0)	
			add(item);
		
		DblListnode<E> newItem = new DblListnode<E>(curr.getPrev(), curr, item);
		curr.getPrev().setNext(newItem);
		curr.setPrev(newItem);
		curr = newItem;
	}
	
	public void addAfter(E item)	{
		if (numItems == 0)	
			add(item);
		
		DblListnode<E> newItem = new DblListnode<E>(curr, curr.getNext(), item);
		curr.getNext().setPrev(newItem);
		curr.setNext(newItem);
		curr = newItem;
	}
	
	public E getCurrent()	{
		return curr.getData();
	}
	
	public E removeCurrent()	{
		if (curr == null || numItems == 0)
			throw new EmptyLoopException();
		if (numItems == 1)
			msgLoop = new DblListnode<E>();
		
		E tmp = curr.getData();
		curr.getPrev().setNext(curr.getNext());
		curr.getNext().setPrev(curr.getPrev());
		curr = curr.getNext();
		return tmp;
	}
	
	public void forward()	{
		curr = curr.getNext();
	}
	
	public void back()	{
		curr = curr.getPrev();
	}
	
	public int size()	{
		return numItems;
	}
	
	public Iterator<E> iterator()	{
		return msgLoop.iterator();
	}
}
