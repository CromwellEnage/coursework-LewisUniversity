package edu.lewisu.cpsc50100;

/*
* @brief Generic linked-list queue implementation.
* @author Cromwell D. Enage
*/
public class LLQueue<T> implements Queue<T> {
	private class LLNode {
		public T data;
		public LLNode next;

		public LLNode(T t)
		{
			this.data = t;
			this.next = null;
		}
	}

	private int _element_count;
	private LLNode _front_node;
	private LLNode _back_node;

	/*
	* @brief Default constructor.
	*/
	public LLQueue()
	{
		this._element_count = 0;
		this._front_node = this._back_node = null;
	}

	@Override
	public int getElementCount()
	{
		return this._element_count;
	}

	@Override
	public T getFront()
	{
		return this._front_node.data;
	}

	@Override
	public void dequeue()
	{
		if (0 == this._element_count)
		{
			throw new UnsupportedOperationException("This LLQueue is empty.");
		}

		if (0 == --this._element_count)
		{
			this._front_node = this._back_node = null;
		}
		else
		{
			this._front_node = this._front_node.next;
		}
	}

	@Override
	public void enqueue(T t)
	{
		if (MAXIMUM_ELEMENT_COUNT == this._element_count)
		{
			throw new UnsupportedOperationException("This LLQueue is full.");
		}

		if (0 == this._element_count)
		{
			this._front_node = this._back_node = new LLNode(t);
		}
		else
		{
			this._back_node.next = new LLNode(t);
			this._back_node = this._back_node.next;
		}

		++this._element_count;
	}
}
