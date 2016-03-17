/*
 * Name:	Andrew Hwang
 * UID: 	ahwang96
 * UID #: 	113610271
 * Section:	0103
 * I pledge on my honor that I have not received any unauthorized
 * assistance on this assignment.
 * 
 * This class creates a linked list and contains methods that can add elements
 * into the list while also sorting them into order, returning the size,
 * returning the element at a certain index, removing an element at a certain
 * index, checking to see if the list contains a certain element, and creating
 * a new sublist from one index to another.
 */

package list;

import java.lang.Iterable;
import java.util.Iterator;
import java.util.Comparator;
import java.lang.IndexOutOfBoundsException;

public class List<T extends Comparable<T>> implements Iterable<T> {

	private static class Node<D extends Comparable<D>> {
		private D data;
		private Node<D> next;
		// constructor for a Node
		public Node(D data) {
			this.data = data;
			next = null;
		}
	}
	// initializing the head, tail and size
	private Node<T> head;
	private Node<T> tail;
	private int size;
	// constructor for a List
	public List() {
		this.head = null;
		this.size = 0;
	}
	// creating a deep copy of a List
	public List(List<T> otherList) {
		this.size = otherList.size;
		Node<T> list = otherList.head;
		this.head = new Node<T>(head.data);
		if (list.next != null) {
			this.head.next = new Node<T>(list.next.data);
			list = list.next;
			this.head.next.next = new Node<T>(list.next.data);
		}
	}
	// sorts the order of each element while simultaneously adding it to the list 
	public void sortedOrderInsert(T newElt) {
		Node<T> newCurr = new Node<T>(newElt), afterCurr = null;
		if(head == null) {
			head = newCurr;
			return;
		}
		if (newCurr.data.compareTo(head.data) < 0) {
			afterCurr = head;
			afterCurr.next = head.next;
			head = newCurr;
			head.next = afterCurr;
			return;
		}
		afterCurr = head;
		while (afterCurr != null) {
			if (newCurr.data.compareTo(afterCurr.data) >= 0) {
				if (afterCurr.next == null) {
					afterCurr.next = newCurr;
					return;
				}
			}
			afterCurr = afterCurr.next;
		}
		afterCurr = head;
		while (afterCurr != null) {
			if	(newCurr.data.compareTo(afterCurr.next.data) <= 0) {
				newCurr.next = afterCurr.next;
				afterCurr.next = newCurr;
				return;
			}
			afterCurr = afterCurr.next;
		}
	}
	// returns the size of the linked list
	public int size() {
		Node<T> current = head;
		while (current != null) {
			current = current.next;
			this.size++;
		}
		return size;
	}
	// returns the element at a certain index
	public T getElementAtIndex(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		} else {
			Node<T> current = head;
			int pos = 0;
			while (current != null && pos < index && size() > index) {
				current = current.next;
				pos++;
			}
			return current.data;
		}
	}
	// returns the node at a certain index
	public Node<T> getNodeAtIndex(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		} else {
			Node<T> current = head;
			int pos = 0;
			while (current != null && pos < index && size() > index) {
				current = current.next;
				pos++;
			}
			return current;
		}
	}
	// checks to see if an element exists in the linked list then returns the 
	// element in the list
	public T contains(T element) {
		Node<T> current = this.head;
		while (current != null) {
			if (current.data.compareTo(element) == 0) {
				return current.data;
			} else {
				current = current.next;
			}
		}
		return null;
	}
	// creates a toString of the linked list
	public String toString() {
		Node<T> current = head;
		
		if (head == null)
			return "";

		String total = current.data + "";
		while (current.next != null) {
			total += " " + current.next.data;
			current = current.next;
		}
		return total;
	}

	// returns the first index of a certain element within the linked list
	public int indexOf(T element) {
		Node<T> current = this.head;
		int idx = 0;
		while (current != null) {
			if (current.data.compareTo(element) == 0) {
				return idx;
			} else {
				idx++;
				current = current.next;
			}
		}
		return -1;
	}
	// returns the last index of a certain element within the linked list
	public int lastIndexOf(T element) {
		Node<T> current = this.head;
		int idx = 0;
		while (current != null) {
			if (current.data.compareTo(element) == 0) {
				while (current.next != null && current.next.data.compareTo(element)==0) {
					current = current.next;
					idx++;
				}
				return idx;
			} else {
				idx++;
				current = current.next;
			}
		}
		return -1;
	}

	// removes an element if it matches the parameter and then returns true if
	// the element was removed
	public boolean removeElt(T element) {
		Node<T> current = this.head, prev = null;
		while (current != null) {
			if (current.data.compareTo(element) == 0) {
				prev.next = current.next;
				return true;
			}
			prev = current;
			current = current.next;
		}
		return false;
	}
	// removes the element at a certain index
	public void removeElementWithIndex(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		} else {
			Node<T> current = head, prev = null;
			int idx = 0;
			while (current != null && idx != index) {
				prev = current;
				current = current.next;
				idx++;
			}
			prev.next = current.next;
		}
	}
	// clears the entire linked list
	public void clear() {
		head = null;
		
	}
	// creates a new list with elements from between the two parameter indexes
	public List<T> subList(int fromIndex, int toIndex) throws IndexOutOfBoundsException {
		Node<T> current = getNodeAtIndex(fromIndex);
	    
		int limit = (toIndex - fromIndex) + 1;
	    
		List<T> newList = new List<T>();
	    
		newList.head = new Node<T>(current.data);
	    tail = newList.head;
	    
	    int idx = 1;
	    current = current.next;
	    
	    while(current != null && idx < limit) {
	        tail.next = new Node<T>(current.data);
	        tail = tail.next;
	        current = current.next;
	        idx++;
	    }
	    return newList;
	}

	public Iterator<T> iterator() {
		throw new UnsupportedOperationException("You must write this method.");
	}

	public Comparator<List<T>> lengthComparator() {
		throw new UnsupportedOperationException("You must write this method.");
	}

	public Comparator<List<T>> orderComparator() {
		throw new UnsupportedOperationException("You must write this method.");
	}

}
