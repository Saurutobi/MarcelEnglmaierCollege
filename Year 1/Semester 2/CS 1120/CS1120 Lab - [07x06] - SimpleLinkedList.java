package runClass;

import java.util.AbstractList;

/**
 *
 * @author Marcel Englmaier/Thomas Ekema/Bobby Wojo
 */
public class SimpleLinkedList<T extends Comparable> extends AbstractList<T>
{
    private class Node
	{
        T data;
        Node next;
        
        Node(T data, Node next)
		{
            this.data = data;
            this.next = next;
        }
        
        Node(T data)
		{
            this(data, null);
        }
    }
    Node first;
    Node last;
    
    public SimpleLinkedList()
	{
        first = null;
        last = null;
    }

    @Override
    public T get(int index)
	{
        Node current = first;
        for(int i = 0; i < index; i++)
		{
            current = current.next;
        }
        return current.data;
    }
    
    @Override
    public int size()
	{
        int count = 0;
        Node p = first;
        while(p != null)
		{
            count++;
            p = p.next;
        }
        return count;
    }

    @Override
    public boolean add(T item)
	{
        Node newNode = new Node((T)item);
        
        if(isEmpty())
		{
            first = newNode;
            last = first;
            return true;
        }
        // create new node to mark current location through the list
        Node current = first;
        int count = 1;
        
        // for 1 node
        if(first == last)
		{
            if(item.compareTo(current.data) < 0)
			{
                first = newNode;
                newNode.next = current;
                last = current;
            }
            else
			{
                newNode.next = current.next;
                current.next = newNode;
            }
            return true;
        }
        
        // run through the entire list to find location to place restaurant
        while(current.next != null)
        {
            if(item.compareTo(current.next.data) <= 0)
			{
                // create new node to find the previous node
                Node previoustoCurrent = first;
                // for each item that has been stepped through by current,
                // move up the previous node
                for(int j = 1; j < count; j++)
				{
                    previoustoCurrent = previoustoCurrent.next;
                }
                // now place newNode into list by setting the previous node's .next to the
                // newNode being added. Then set the newNode's .next to the current
                current = current.next;
                previoustoCurrent.next = newNode;
                newNode.next = current;
                return true;
            }
            //compare restaurant data with .next
            if(item.compareTo(current.next.data) > 0)
			{
                //place into list at this location by setting new item's .next to
                //current.next and then replacing current.next to the new item.
                // move to next item
                current = current.next;
                count++;
				//newNode.next = current.next;
				//current.next = newNode;
				//return true;
            }
            
            //Final item in list
            if(current.next == null)
			{
                current.next = newNode;
                last = newNode;
                return true;
            }
        }
        return true;
    }
}
