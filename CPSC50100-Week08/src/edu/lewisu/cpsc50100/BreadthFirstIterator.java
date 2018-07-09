package edu.lewisu.cpsc50100;

import java.util.Iterator;
import java.util.Queue;

/*
* @brief Iterator over the elements of a tree node and its descendants, breadth first.
* @author Cromwell D. Enage
*/
public class BreadthFirstIterator<T> implements Iterator<T> {
	private Queue<BinaryTreeNode<T>> _buffer;

	/*
	* @brief Constructs an iterator that will start at the specified node.
	* @param node the specified node.
	*/
	public BreadthFirstIterator(BinaryTreeNode<T> node)
	{
		this._buffer = new java.util.LinkedList<BinaryTreeNode<T>>();

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
		BinaryTreeNode<T> node = this._buffer.remove();
		BinaryTreeNode<T> child = node.getLeftChild();

		if (child != null)
		{
			this._buffer.add(child);
		}

		child = node.getRightChild();

		if (child != null)
		{
			this._buffer.add(child);
		}

		return node.getData();
	}
}
