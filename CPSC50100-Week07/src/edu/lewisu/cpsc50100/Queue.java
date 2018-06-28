package edu.lewisu.cpsc50100;

/*
* @brief Generic interface for a queue that can hold a set maximum number of elements.
* @author Cromwell D. Enage
*/
public interface Queue<T> {

	/*
	* @brief The set maximum number of elements that this queue can hold.
	*/
	public static final int MAXIMUM_ELEMENT_COUNT = 10;

	/*
	* @brief Returns the number of elements that this queue currently holds.
	*/
	public int getElementCount();

	/*
	* @brief Returns the element currently at the front of this queue.
	*/
	public T getFront();

	/*
	* @brief Removes the element currently at the front from this queue.
	* @throw UnsupportedOperationException if this queue is empty.
	*/
	public void dequeue();

	/*
	* @brief Adds the specified element to the back of this queue.
	* @param t the specified element.
	* @throw UnsupportedOperationException if this queue is full.
	*/
	public void enqueue(T t);
}
