package algorithm;

import java.io.PrintStream;
import java.util.*;

public class BstMultiset<T> extends Multiset<T>
{
	/*
	 *  Head and Tail are always null (Dummy nodes) 
	 *  It makes easier to implement loop operation
	 */
 
	private Node head;
	private Node tail;
	private int size = 0;

	public BstMultiset() {
	   
		this.head = new Node(null);
		this.tail = new Node(null);
		
		this.head.l_node = this.tail;  
		this.head.r_node = this.tail;
		
		this.tail.l_node = this.tail;
		this.tail.r_node = this.tail;
		
	} // end of BstMultiset()
	
	
	/*
	 *   Loop through the tree from head node to tail
	 * 	 increase count value if the same value found in a node
	 *   otherwise, find a subtree to insert  
	 */

	public void add(T item) {
		
		if(this.size==0)
		{
			// First insertion
			
			Node newNode = new Node(item);
			this.head.l_node = newNode;
			newNode.l_node = this.tail;
			newNode.r_node = this.tail;
			this.size++;
		}
		else
		{
			
		Node parent = this.head;
		Node child = this.head.l_node;
		boolean sameElement = false;
		
		while(child!=this.tail)
		{
			if(child.data.equals(item))
			{
 
				child.count++;
				sameElement = true;
				break;
			}
			
			parent = child;
			
			if(child.data.toString().compareTo((String) item) > 0)
			{
				child = child.l_node; // If the item is smaller than root value, go to the left subtree
			}
		  else
		   {
			    child = child.r_node; // If the item is bigger than root value, go to the right subtree
		   }

		}
		
		/*
		 *  Node with the same value NOT found if sameElement is false,
		 *  means parent node is the last node at some subtree 
		 *  insert it depends on its value comparison  
		 */
		
		if(!sameElement)
		{
		
			Node newNode = new Node(item);
			
			if(parent.data.toString().compareTo((String) item)>0)
			{
				parent.l_node = newNode;
			}
		  else
			{
			    parent.r_node = newNode;
			}
			
			newNode.l_node = this.tail;
			newNode.r_node = this.tail;
			this.size++;
		
		}
		
		}
 
		
	} // end of add()


	public int search(T item) {
		
		Node tmp = this.head.l_node;
		int count = 0;
		
		while(tmp!=this.tail)
		{
			if(tmp.data.equals(item))
			{
				count = tmp.count;
				break;
			}
			
			if(tmp.data.toString().compareTo((String)item)>0)
			{
				tmp = tmp.l_node;
			}
		  else
			{
			   	tmp = tmp.r_node;
			}
			
		}
		
		// default return, please override when you implement this method
		return count;
	} // end of add()


	@SuppressWarnings("unchecked")
	public void removeOne(T item) {
		
		
		Node child = this.head.l_node;
		
		while(child!=this.tail)
		{
 
			if(child.data.equals(item))
			{
				
				/* if a node has more than one item, count--;
				*  if the node has only one, has to be removed from the tree, call removeAll method with the item value
				*/ 
				 
				if(child.count>1)
				{
					child.count--;
					break;
				}
			  else if(child.count==1)
				{
					removeAll((T) child.data);
					break;
				}
				
				
			}
			
 
			if(child.data.toString().compareTo((String)item)>0)
			{
 
				child = child.l_node;
			}
		  else
			{
 
			   child = child.r_node;
			}

			
		}

		
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		
		Node parent = this.head;
		Node child = this.head.l_node;
		
		while(child!=this.tail)
		{
 
			if(child.data.equals(item))
			{
				/*  Found a node that has the same item.
 				*   If it has a left child or right child,    
 				*   Loop through and copy its data to its parent node
 				*   keep going until the last node 
 				*/
 				
				while(child.l_node != this.tail || child.r_node!=this.tail)
				{
 
					parent = child; // track the current node's parent node 
					
					if(child.l_node==this.tail) // If the current node's left value is null, copy its right value to the current node and move to the right child
					{
						child.data = child.r_node.data;
						child.count = child.r_node.count;
						
						child = child.r_node;
					}
				 else if(child.r_node==this.tail) // If the current node's right value is null, copy its left value to the current node and move to the left child
					{
						child.data = child.l_node.data;
						child.count = child.l_node.count;
						
						child = child.l_node;
					}
					
					// If the current value has both child nodes, go to the right node which is bigger than left node  
					
					if(child.l_node!=this.tail && child.r_node!=this.tail)
					{
						child.data = child.r_node.data;
						child.count = child.r_node.count;

						child = child.r_node;
					}
					
					

				}
 
				 // The last node has the same value as its parent node, it needs to be removed
				
				 // when the node is a parent node. this means, it has been in while loop,
				
				  this.size--;
				
				  if(parent.data!=null && parent.data.equals(parent.l_node.data))
				  {
					    parent.l_node = this.tail;
					    break;
				  }
			    else if(parent.data!=null && parent.data.equals(parent.r_node.data)) 
				  {
				    	parent.r_node = this.tail;
				    	break;
				  }
				  
				  // when the node is the last node. this one was originally at the bottom 
									
				  if(item.equals(parent.l_node.data))
				  {
					    parent.l_node = this.tail;
					    break;
				  }
			    else if(item.equals(parent.r_node.data)) 
				  {
				    	parent.r_node = this.tail;
				    	break;
				  }
				  
	
				
				
			}
			
			parent = child;  
			
			if(child.data.toString().compareTo((String)item)>0)
			{
 
				child = child.l_node;
			}
		  else
			{
 
			   child = child.r_node;
			}
			
			
			
		}
		
		
	} // end of removeAll()


	public void print(PrintStream out) {
    
		if(this.size>0)
		{
			Node tmp = this.head.l_node;
			bst(tmp);
		}
		
		
	} // end of print()
	
	/*
	 *  Loop through the tree recursively 
	 */
	
	public void bst(Node node)
	{
		if(node!=this.tail)
		{
			System.out.print(node.data + " | ");
			System.out.println(node.count);
		
			bst(node.l_node);
			bst(node.r_node);
		}
 
	}
	
	class Node{
		
		private Object data;
		private int count = 1;
		private Node l_node;
		private Node r_node;
		
		public Node(Object data)
		{
		   this.data = data;	
		}
		
	}
	

} // end of class BstMultiset
