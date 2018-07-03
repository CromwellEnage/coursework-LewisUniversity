package edu.lewisu.cpsc50100;

import java.util.Iterator;
import java.util.Queue;

/*
* @brief Iterator over the elements of a tree node and its descendants, breadth first.
* @author Cromwell D. Enage
*/
public class BreadthFirstIterator<T> implements Iterator<T> {
	private Queue<ArrayTreeNode<T>> _buffer;

	/*
	* @brief Constructs an iterator that will start at the specified node.
	* @param node the specified node.
	*/
	public BreadthFirstIterator(ArrayTreeNode<T> node)
	{
		this._buffer = new java.util.LinkedList<ArrayTreeNode<T>>();

		if (node != null)
		{
			this._buffer.add(node);
		}
	}

	@Override
	public boolean hasNext()
	{
		return !this._buffer.isEmpty();
	}

	@Override
	public T next()
	{
		ArrayTreeNode<T> node = this._buffer.remove();

		for (int i = 0; i < node.getChildCount(); ++i)
		{
			this._buffer.add(node.getChild(i));
		}

		return node.getData();
	}
}
