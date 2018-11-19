import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DoublyLinkedList<T> implements List
{
	private class Node
	{
		public T data;
		public Node next;
		public Node previous;
		
		public Node(T data) 
		{
			this.data = data;
			next 	  = null;
			previous  = null;
		}
		
		public String toString() 
		{
			return " "+this.data;
		}		
	}
	
	private Node head, tail;
	private int size;
	
	public DoublyLinkedList()
	{
		head = null;
		tail = null;
		size = 0;
	}

	
	private void addToBeginning(Object data) 
	{
		Node newNode = new Node((T)data);
		
		if(isEmpty()) 
		{
			tail = newNode;
		}else 
		{
			head.previous = newNode;
		}
		newNode.next = head;
		head 		 = newNode;
		size++;
		
	}
	
	
	private void addToEnd(Object data) 
	{
		Node newNode = new Node((T) data);
		
		if(isEmpty()) 
		{
			head = newNode;
		}else 
		{
			tail.next = newNode;
		}
		
		newNode.previous = tail;
		tail 			 = newNode;
	}		
	
	@Override
	public boolean add(Object obj) {		
		addToEnd(obj);	
		size++;
		return true;
	}
	
	@Override
	public void add(int index, Object obj) 
	{
		if(index < 0 || index > size) throw new IndexOutOfBoundsException();
		
		Node newNode 	 = new Node((T)obj);
		Node currentHead = head, currentTail = tail;		
		
		
		if(size() == 0 || index == 0) 
		{
			addToBeginning(obj);
		}else if(index == size)
		{
			add(obj);
		}else 
		{
			if(index < size()/2) 
			{
				for(; index > 0; index--) 
				{
					currentHead = currentHead.next;
				}
				newNode.next		  = currentHead;
				
				newNode.previous      = currentHead.previous;
				currentHead.previous  = newNode.next;
				newNode.previous.next = newNode;
			}else 
			{				
				for(; index < size(); index++) 
				{
					currentTail = currentTail.previous;
				}
				
				newNode.next     	  = currentTail.next;
				newNode.previous 	  = currentTail;
				currentTail.next 	  = newNode;
				newNode.next.previous = newNode;				
			}
			size++;
		}
				
	}
	
	@Override
	public void clear() {

		while(head != null) 
		{
			Node first = head.next; 
			head.next =  null;
			head.previous = null;
			head = first;
		}
		tail = head = null;
		size = 0;
	}

	@Override
	public boolean contains(Object obj) {
		
		while(head != null) 
		{
			if(head.data.equals((T)obj)) {
				return true;
			}
			head = head.next;
		}						
		return false;
	}

	
	@Override
	public Object get(int index) {
		Node current = head;
		int count = 0;
		
		if(index < 0 || index >= size()) throw new IndexOutOfBoundsException();
		
		if(current == null) throw new NullPointerException();
		
		while(current != null) 
		{
			if(count == index) 
			{
				break;
			}			
			count++;
			current = current.next;
		}
		return current;	
	}

	@Override
	public int indexOf(Object obj) {
		int index = -1;
		while(head != null) 
		{
			if(head.data.equals((T)obj)) {
				break;
			}
			index++;
		}
		return index;
	}

	@Override
	public boolean isEmpty() {
		return head==null || tail == null;
	}

	
	@Override
	public int lastIndexOf(Object obj) {
		Node currentTail;
		int i;		
		
		for(currentTail = tail, i = size()-1; currentTail != null; i--, currentTail = currentTail.previous)
			if(currentTail.data == obj)
				return i;
		
		return -1;
	}
	 

	private Node findNode(Object data) 
	{
		Node current = head;
		while(current != null) 
		{
			if(current.data.equals((T)data)) {
				break;
			}
			System.out.print("\t"+current.data);
			current = current.next;
		}
		return current;
	}
	
	private Node findNode(int index) 
	{
		Node current = head;
		int count = 0;
		while(current != null) 
		{
			if(count == index) {
				break;
			}
			current = current.next;
			count++;
		}
		
		return current;
	}
	
	
	@Override
	public boolean remove(Object obj) 
	{
		Node toDelete = findNode((Object) 233);
		System.out.println("Node: "+toDelete.toString());
		if(toDelete == null) return false;
		
		// if its at the head it doesn't exist previous
		if(toDelete.previous == null) 
		{
			head 		  = toDelete.next;
			head.previous = null;
			size--;
		}
		
		// if its at the tail it doesn't exist next
		if(toDelete.next == null) 
		{
			tail 	  = toDelete.previous;
			tail.next = null;
			size--;
		}
		
		if(toDelete.previous != null || toDelete.next != null) 
		{
			//establish new references		
			Node aux 	 = toDelete.previous;
			aux.next 	 = toDelete.next;			
			aux		     = toDelete.next;
			aux.previous = toDelete.previous;	
			size--;
		}
		
		return true;
	}

	@Override
	public Object remove(int index) 
	{
		if(index < 0 || index >= size()) throw new IndexOutOfBoundsException();
		
		Node current = findNode(index);
		
		// head
		if(index == 0) 
		{
			head = current.next;
			head.previous = null;
			size--;
		}else 	
		if(index == size()-1) 
		{
			tail 	  = current.previous;
			tail.next = null;
			size--;
		}else 
		{
			current.previous.next = current.next;
			current.next.previous = current.previous;
			size--;
		}		
		
		return current;
	}
	
	public void print() 
	{
		Node current = head;
		while(current != null) 
		{
			System.out.print(current.data.toString()+" ");
			 current = current.next;
		}
	}

	@Override
	public boolean removeAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object set(int arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {		
		return this.size;
	}

	@Override
	public List subList(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray(Object[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
