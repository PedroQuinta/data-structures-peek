import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.omg.CORBA.Current;

public class LinkedList<T> 
{

	private class Node
	{
		public T data;
		public Node next;
		public Node(T data) 
		{
			this.data = data;
			next = null;
		}
		
		public String toString() 
		{
			return "Value : "+this.data;
		}
	}
	
	private Node head;
	
	public LinkedList() 
	{
		head = null;
	}
	
	/**
	 * Adds a generic element e into the list. Returns true if succeded.
	 * @param e
	 * @return boolean
	 */
	public boolean add(T e) 
	{
		Node current = head, previous = null, newNode = new Node(e);
		
		
		if(current == null) 
		{
			newNode.next = head;			
			head 		 = newNode;
			return true;					
		}
		
		if(e == null)
			throw new NullPointerException();	
		
		
		while(current != null) 
		{
			previous = current;
			current  = current.next;
		}
		
		newNode.next  = current;
		previous.next = newNode;
		
		return true;

	}
	
	/**
	 * Adds an element at the specified index in the list, at the beginning.
	 * @param index
	 * @param element
	 */
	public void add(int index, T element) 
	{
		Node current = head, previous = null, newNode = new Node(element);
		int count    = 0;
		
		if(current == null) 
		{
			newNode.next = head;
			head = newNode;
		}
	
		if(index < 0 || index >= 99) throw new IndexOutOfBoundsException();
		
		for(;index > 0; index--) 
		{
			previous = current;
			current  = current.next;
		}
		
		newNode.next  = current;
		previous.next = newNode;		
	}
	
	
	public boolean addAll(Collection<T> c) 
	{
		boolean notified = false;
		
		for(T e : c) 
		{
			if(add(e)) 
			{
				notified = true;
				break;
			}
		}
		
		return notified;
	}
	
	
	public boolean addAll(int index, Collection<T> c) 
	{			
		for(T e : c) 
		{
			add(index, e);				
		}
		
		return true;	
	}
	
	public void clear() 
	{
		head = null;
	}
	
	public boolean contains(T o) 
	{
		Node current = head;
		
		while(current != null) 
		{
			if(o.equals(current.data)) {
				return true;
			}			
			current  = current.next;
		}
		
		return false;
	}
	
	public boolean containsAll(Collection<T> c) 
	{
		for(T e : c) 
		{
			if(!contains(e)) 
			{
				return false;
			}
		}
		
		return true;
	}
	
	public boolean equals(Object o) 
	{
		Node current = head, previous = null;
		boolean flag = false;
		
		if(o instanceof LinkedList) {
			LinkedList list = (LinkedList) o;			
			
			if(list.size() == size()) {
				Node cl = list.head;
				
				if(list.get(0) == current.data)
					return true;
				
				while(current != null && cl != null) 
				{
					if(!cl.equals(current))
						return false;
					
					cl 		= cl.next;
					current = current.next;
				}
			}
		}
		return true;
	}
	
	
	public T get(int index) 
	{
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
		return current.data;		
	}
	
	public int hashCode() 
	{
		Node current = head;
		int hashcode = 1;
		
		while(current != null) 
		{
			hashcode = 31 * hashcode + ((current.next == null) ? 0 : current.next.hashCode());
			current  = current.next; 
		}
		
		return hashcode;
	}
	
	public int indexOf(T o) 
	{
		Node current = head;
		int count = 0;
		
		if(current == null) throw new NullPointerException();
		
		while(current != null) 
		{
			if(current.data.equals(o)) {
				return count;
			}
			
			count++;
			current = current.next;
		}
		
		return -1;
	}
	
	public boolean isEmpty() 
	{
		return head ==null;
	}
	
	public Iterator<T> iterator()
	{
		return new Iterator<T>() {

			Node current = head;
			
			@Override
			public boolean hasNext() {				
				return current != null;
			}

			@Override
			public T next() {
				if(hasNext()) 
				{
					T data  = current.data;
					current = current.next;
					return data;
				}
				return null;
			}
			
		};
	}
	
	public int lastIndexOf(T o) 
	{
		Node current = head;
		int count = 0, result = -1;		
		
		if(current == null) throw new NullPointerException();
		
		while(current != null) 
		{
			if(current.data.equals(o)) {
				result = count;
			}
			
			count++;
			current = current.next;
		}
		
		return result;				
	}
	
	public T remove(int index) 
	{
		Node current = head, previous = null;
		int count = 0;
		
		if(index < 0 || index >= size()) throw new IndexOutOfBoundsException();	
		
		
		while(current != null) 
		{
			if(index == count) 
			{
				current = head;
				head    = current.next;
				break;
			}
			
			previous = current;
			current  = current.next;
			count++;
		}
		
		
		
		previous.next = current.next;
		
		return current.data;
	}
	
	
	public boolean remove(T o) 
	{
		Node current = head, previous = null;
		int count = 0;
		
		if(!contains(o))
			return false;
		
		while(current != null) 
		{
			if(o.equals(get(count))) 
			{
				current = head;
				head	= current.next;
				return true;
			}
			count++;
			current = current.next;
		}
		return false;
	}
		
	public T set(int index, T element) 
	{
		Node current = (Node)get(index);		
		Node aux = new Node(current.data);		
		current.data = element;
		
		return aux.data;
	}
	
	
	public int size() 
	{
		Node current = head;
		int count 	 = 0;
		
		while(current != null) 
		{
			count++;
			current = current.next;
		}
		
		return count;
	}
	
	public T[] toArray() 
	{
		Node current   = head;
		Class <T> tipo = (Class<T>) get(0);
		T[] lista = (T[])Array.newInstance(tipo, size());//new T[size()];
		int count = 0;
		
		while(current != null) 
		{
			lista[count] = current.data;
			count++;
			current = current.next;
		}
		
		return lista;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
