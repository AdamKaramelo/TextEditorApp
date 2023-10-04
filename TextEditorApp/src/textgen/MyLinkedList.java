package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list.unneccessary
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		head = null;
		tail = null;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) //should be correct
	{
		LLNode<E> newEl = new LLNode<E>(element);
		if(element == null) {
			throw new NullPointerException("Element to append cannot be null");
		}
		if(head == null) {
			head = newEl;
			tail = newEl;
		}else {
			newEl.prev = tail;
			tail.next = newEl;
			tail = newEl;
		}
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		LLNode<E> headTemp = head;
		int c = index;
		E el = null;
		size = this.size();
		if(index >= size() || index < 0) {
			throw new IndexOutOfBoundsException("Index is not acceptable");
		}
		else if(index == 0) {
			el = headTemp.data;
		}else {
			while(c > 0) {
				headTemp = headTemp.next;
				c--;
			}
			el = headTemp.data;
		}
		return el;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) //should be ok
	{
		LLNode<E> headTemp = head;
		LLNode<E> newEl = new LLNode<E>(element);
		if(index > size() || index <0) {
			throw new IndexOutOfBoundsException("Index is not correct.");
		}else if(element == null) {
			throw new NullPointerException("Element to append cannot be null");
		}
		else if (head == null) {		// list is empty, index must be 0
			head = newEl;
			tail = newEl;
		}
		else if (index == 0) {			// insert before head
			newEl.next = head;
			head.prev = newEl;
			head = newEl;
		}
		else if (index == size) { 	// insert after tail
			newEl.prev = tail;
			tail.next = newEl;
			tail = newEl;
		}
		else if(index < size()) {
			int c = index - 1;
			while(c > 0) {
				headTemp = headTemp.next;
				c--;
			}
			newEl.next=headTemp.next;
			newEl.prev = newEl.next.prev;
			newEl.next.prev = newEl;
			headTemp.next=newEl;
		}
	}


	/** Return the size of the list */
	public int size() //should be correct
	{
		LLNode<E> headTemp = head;
		if(head == null) {
			size = 0;
		}else {
			size = 1;
			while(headTemp.next != null) {
				headTemp = headTemp.next;
				size++;
			}
		}
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		LLNode<E> headTemp = head;
		E data = null;
		if(index >= size() || index < 0) {
			throw new IndexOutOfBoundsException("Index is larger than the size of the List");
		}else if(index == 0) {
			data = head.data;
			head = head.next;
		}else {
			for(int i=0; i < index ; i++) {
				headTemp = headTemp.next;
			}
			headTemp.prev.next = headTemp.next;
			headTemp.next.prev = headTemp.prev;
			headTemp= null;
		}
		return data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		LLNode<E> headTemp = head;
		E data = null;
		if(index >= size()|| index < 0) {
			throw new IndexOutOfBoundsException("Index is out of bouds.");
		}else if(element == null){
			throw new NullPointerException("Cannot set an element to null");
		}else if(index == 0) {
			head.data = element;
		}
		else {
			for(int i=0; i < index ; i++) {
				headTemp = headTemp.next;
			}
			data = headTemp.data;
			headTemp.data = element;
		}
		return data;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode(E e)
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
