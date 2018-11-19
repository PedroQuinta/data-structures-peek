package fcup;

public class DoublyLinkedList 
{
	private class Node
	{
		public Node next, previous;
		public int value;
		
		Node(int val)
		{
			value 	 = val;
			next 	 = null;
			previous = null;
		}
	}
	
	public Node head, tail;
	public int size;
	
	public DoublyLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	
	public boolean isEmpty() 
	{		
		return size == 0;
	}
	
	public String toString() 
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Content :\n");
		Node current = head;
		
		while(current != null) 
		{
			sb.append(current.value);
			if(current.next != null)
				sb.append("->");
			current = current.next;
		}
		
		return sb.toString();
	}
	
	public int get(int pos) 
	{
		int result = -1;
		if(pos < 0 || pos >= size) return result;
		else 
		{
			Node current = head;
			int count = 0;
			while(current != null) 
			{
				if(count == pos) 
				{
					result = current.value;
					break;
				}
				current = current.next;
				count++;
			}
		}
		return result;
	}
	
	public void addToBeginning(int value) 
	{
		Node newNode = new Node(value);
		
		if(isEmpty())
			tail = newNode;
		else
			head.previous = newNode;
		
		newNode.next = head;
		head = newNode;			
		size++;
	}
	
	public void addToEnd(int value) 
	{
		Node newNode = new Node(value);
		
		if(isEmpty())
			head = newNode;
		else
			tail.next = newNode;
		newNode.previous = tail;
		tail = newNode;
		size++;
	}
	
	public void addByIndex(int value, int index) 
	{
		if(index < 0 || index >= size) return;
		else 
		{
			if(index == 0) {
				addToBeginning(value);
			}else if(index == size - 1){
				addToEnd(value);
			}else {				
			
				Node current = head, newNode = new Node(value);
				int count = 0;
				while(current != null) 
				{
					if(count == index) 
					{
						newNode.next = current.next;
						current.next = newNode;
						size++;
						break;
					}
					current = current.next;
					count++;
				}
				size++;
			}
		}
	}
	
		
	/*public void addOrderedAsc(int value) 
	{
		Node current = head, previous = null, newNode = new Node(value);
		if(isEmpty())
			head = newNode;
		else 
		{
			while(current != null && value > current.value) 
			{
				previous = current;
				current  = current.next;
			}
			
			if(previous == null) {
				newNode.next = current;
				head 	 = newNode;
			}else 
			{
				previous.next = newNode;
				newNode.next = current;
			}
				
		}
		size++;			
	}
	
	public void addOrderedDesc(int value) 
	{
		Node current = head, previous = null, newNode = new Node(value);
		if(isEmpty())
			head = newNode;
		else 
		{
			while(current != null && value < current.value) 
			{
				previous = current;
				current  = current.next;
			}
			
			if(previous == null) {
				newNode.next = current;
				head 	 = newNode;
			}else 
			{
				previous.next = newNode;
				newNode.next = current;
			}
				
		}
		size++;			
	}*/
	
	private Node find(int index) 
	{
		if(index < 0 || index >= size) return null;
		else {
			Node current = head; int count = 0;		
			while(current != null) {
				if(count == index) {
					break;
				}
				current = current.next;
				count++;
			}
			return current;
		}
	}
	
	
	public void remove(int index) 
	{
		if(index < 0 || index >= size) return;
		else 
		{
			Node current = find(index);
			System.out.print(current.value);
			if(index == 0) 
			{
				head = current.next;
				head.previous = null;
				size--;
			}else if(index == size-1) 
			{
				tail = current.previous;
				tail.next = null;
				size--;
			}else 
			{
				current.previous.next = current.next;
				current.next.previous = current.previous;
				size--;
			}			
		}
	}
	
	private boolean contains(int value) 
	{
		if(isEmpty()) return false;
		else
		{
			Node current = head;
			while(current != null) 
			{
				if(current.value == value)
					return true;
				current = current.next;
			}
			return false;
		}
		
	}
	
	public int count(int val) 
	{
		int result = 0;		
		Node current = head;
		while(current != null) {
			if(current.value == val)
				result++;
			current = current.next;
		}	
		return result;
	}
	
	
	public void removeEntries(int val) 
	{
		Node current = head, last= tail;
		
		while(current != null) 
		{
			if(current.value == val) 
			{
				if(current.previous == null) 
				{
					head = current.next;
					head.previous = null;
					size--;
				}else if(current.next == null) 
				{
					tail = current.previous;
					tail.next = null;
					size--;
				}else 
				{
					current.previous.next = current.next;
					current.next.previous = current.previous;
					size--;
				}
			}
			current = current.next;
		}
	}
	
	public int indexOf(int val) 
	{
		int pos = 0;
		Node current = head;
		while(current != null) 
		{
			if(current.value == val) 
			{
				break;
			}
			current = current.next;
			pos++;
		}
		return pos;
	}
	
	public int lastIndexOf(int val) 
	{
		int pos = size - 1;
		Node last = tail;
		
		while(last != null) 
		{			
			if(last.value == val)
				break;
			last = last.previous;
			pos--;
		}
		return pos;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
