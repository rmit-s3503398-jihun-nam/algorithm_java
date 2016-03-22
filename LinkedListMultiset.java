package algorithm;

import java.io.PrintStream;
import java.util.*;

public class LinkedListMultiset<T> extends Multiset<T>
{
	/*
	 *  Head and Tail are always null (Dummy nodes) 
	 *  It makes easier to implement loop operation
	 */

	private Node head;
	private Node tail;
	private int size;
	
	public LinkedListMultiset() {
	
		this.head = new Node(null);
		this.tail = new Node(null);
		head.next = tail;
		tail.next = tail;
		this.size = 0;
		
	} // end of LinkedListMultiset()
	
	
	public void add(T item) {
 	
    	   	Node prev = this.head;
    	   	Node current = this.head.next;
    	   	boolean nodeFound = false;
    	   	
    	   	while(current!=this.tail)
    	   	{
                 if(current.data.equals(item))
                 {
                	 nodeFound = true;
                	 break;
                 }
    	   		
    	   		 prev = current;
    	   		 current = prev.next;
    	   	}
    	   	
    	   	/*
    	   	 *  If node is found , increase count
    	   	 *  otherwise, add it to the last.  
    	     *  prev node is the last node so put it to the next.
    	   	 */
    	   	
    	   	if(nodeFound)
    	   	{
    	   		current.counter++;
    	   	}
    	   	else
    	   	{
    	   		
    	   		Node newNode = new Node(item);
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
						prev.next = current.next; // If counter is 0, unlink the node
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
 
			Node current = this.head.next;
			Node prev = this.head;
			
			while(current!=this.tail)
			{
				if(current.data.equals(item))
				{
					prev.next = current.next;
					this.size--;
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
		 private int counter;
		 private Node next;
		 
		 Node(Object data)
		 {
			 this.data = data;
			 this.next = null;
			 this.counter = 1;
		 }
		 
	 }
	
	
} // end of class LinkedListMultiset

