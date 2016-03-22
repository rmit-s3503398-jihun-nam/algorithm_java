package algorithm;
import java.io.PrintStream;
import java.util.*;
 
public class SortedLinkedListMultiset<T> extends Multiset<T>
{
	
	/*
	 *  Head and Tail are always null (Dummy nodes) 
	 *  It makes easier to implement loop operation
	 */
 
	private Node head;
	private Node tail;
	private int size;
	
	public SortedLinkedListMultiset() {
		
		this.head = new Node(null);
		this.tail = new Node(null);
		head.next = tail;
		tail.next = tail;
		this.size = 0;
		
	} // end of SortedLinkedListMultiset()
	
	
	public void add(T item) {
		
		Node newNode = new Node(item);
		Node current;
		Node prev;
		boolean nodeFound = false;
		
		prev = this.head;  
		current = prev.next;
		
		/*
		 *  Loop through until it reaches tail node or bigger value in list than item
		 * 
		 */
		
		while(current!=this.tail && current.data.toString().compareTo((String) item)<0)
		{
			prev = current;
			current = prev.next;
		}
		
		/*
		 *  current node is not a tail node, Nodes found to increase its count value if the data and item are the same.
		 */
		
		if(current.data!=null) 
		{
			if(current.data.toString().compareTo((String) item)==0)
			{
				current.counter++;
				nodeFound = true;
			}
		}
		
		/*
		 *  current node can be a tail node or current node value is bigger than item. so insert between prev and current node
		 */
		
		if(!nodeFound) 
		{
			newNode.next = prev.next;
			prev.next = newNode;
	
			this.size++;
		}
		
		
	} // end of add()
	
	
	public int search(T item) {
		
		Node head = this.head.next;
		int counter = 0;
		
		while(head!=this.tail)
		{
			if(head.data.equals(item))
			{
				counter = head.counter;
			}
			
			head = head.next;
		}
 
		// default return, please override when you implement this method
		return counter;
	} // end of add()
	
	
	public void removeOne(T item) {
		
		Node prev = this.head;
		Node current = this.head.next;
		
		while(current!=this.tail)
		{
			if(current.data.equals(item))
			{
				if(current.counter>0)
				{
					current.counter--;
					
					if(current.counter==0)
					{
						prev.next = current.next;
						this.size--;
					}
					
					break;
				}
			}
			
			prev = current;
			current = prev.next;
		}
		
		
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		
		  Node prev = this.head;
		  Node current = prev.next;
		  
		  while(current!=this.tail)
		  {
			  if(current.data.equals(item))
			  {
				  prev.next = current.next;
				  break;
			  }
			  
			  prev = current;
			  current = prev.next;
		  }
		
	} // end of removeAll()
	
	
	public void print(PrintStream out) {
		
		   if(this.size>0)
		   {
			   Node tmp = this.head.next;
			   
			   while(tmp!=this.tail)
			   {
				   if(tmp.counter>0 && tmp.data!=null)
				   {
					   System.out.print(tmp.data + " | ");
					   System.out.println(tmp.counter);
				   }
				   tmp = tmp.next;
			   }
		   }
		
	} // end of print()
	
	
	   private class Node{
		   
			 private Object data;
			 private Node next;
			 private int counter;
			 
			 Node(Object data)
			 {
				 this.data = data;
				 this.next = null;
				 this.counter = 1;
			 }
			 
		 }
	
	
} // end of class SortedLinkedListMultiset