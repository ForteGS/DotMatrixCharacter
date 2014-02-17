import java.util.Iterator;


public class MessageLoop<E> implements LoopADT<E> {
	private DblListnode<E> msgLoop;
	
	public MessageLoop()	{
		msgLoop = new DblListnode<E>();
	}
	
	public void addBefore(E item)	{
		
	}
	
	public void addAfter(E item)	{
		
	}
	
	public E getCurrent()	{
		
	}
	
	public E removeCurrent()	{
		
	}
	
	public void forward()	{
		
	}
	
	public void back()	{
		
	}
	
	public int size()	{
		return 0;
	}
	
	public Iterator<E> iterator()	{
		return msgLoop.iterator();
	}
}
