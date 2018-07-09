package edu.lewisu.cpsc50100;

import java.util.Iterator;

/*
* @brief Iterator over the elements of a tree node and its descendants, infix order.
* @author Cromwell D. Enage
*/
public class InOrderIterator<T> implements Iterator<T> {
	private BinaryTreeNode<T> _current_node;
	private BinaryTreeNode<T> _root_sentinel;

	/*
	* @brief Constructs an iterator that will start at the leftmost leaf node in the tree.
	* @param root_sentinel the sentinel node whose left child refers to the root of the tree.
	*/
	public InOrderIterator(BinaryTreeNode<T> root_sentinel)
	{
		this._root_sentinel = root_sentinel;
		this._current_node = root_sentinel.getLeftChild();

		if (this._current_node != null)
		{
			while (this._current_node.getLeftChild() != null)
			{
				this._current_node = this._current_node.getLeftChild();
			}
		}
	}

	@Override
	public boolean hasNext()
	{
		return this._current_node != null;
	}

	@Override
	public T next()
	{
		BinaryTreeNode<T> this_node = this._current_node;
		T result = this_node.getData();
		BinaryTreeNode<T> next_node = this_node.getRightChild();

		if (next_node != null)
		{
			while (next_node.getLeftChild() != null)
			{
				next_node = next_node.getLeftChild();
			}

			this._current_node = next_node;
			return result;
		}

		for (
			next_node = this_node.getParent();
			next_node != this._root_sentinel;
			next_node = next_node.getParent()
		)
		{
			if (this_node == next_node.getLeftChild())
			{
				this._current_node = next_node;
				return result;
			}

			this_node = next_node;
		}

		this._current_node = null;
		return result;
	}
}
