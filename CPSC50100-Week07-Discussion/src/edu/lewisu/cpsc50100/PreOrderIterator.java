package edu.lewisu.cpsc50100;

import java.util.Iterator;
import java.util.Stack;

/*
* @brief Iterator over the elements of a tree node and its descendants, prefix order.
* @author Cromwell D. Enage
*/
public class PreOrderIterator<T> implements Iterator<T> {
	private Stack<ArrayTreeNode<T>> _node_stack;
	private Stack<Integer> _int_stack;
	private ArrayTreeNode<T> _current_node;
	private int _child_index;

	/*
	* @brief Constructs an iterator that will start at the specified node.
	* @param node the specified node.
	*/
	public PreOrderIterator(ArrayTreeNode<T> node)
	{
		this._node_stack = new Stack<ArrayTreeNode<T>>();
		this._int_stack = new Stack<Integer>();
		this._current_node = node;
		this._child_index = 0;

		if (node != null)
		{
			this._int_stack.push(Integer.valueOf(0));
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
		T result = this._current_node.getData();
		int index = this._int_stack.peek().intValue();

		if (index == this._current_node.getChildCount())
		{
			boolean is_post_order = true;

			while (is_post_order)
			{
				this._int_stack.pop();

				if (this._node_stack.empty())
				{
					this._current_node = null;
					this._int_stack.clear();
					is_post_order = false;
				}
				else
				{
					this._current_node = this._node_stack.pop();

					if (++this._child_index == this._current_node.getChildCount())
					{
						index = this._int_stack.pop().intValue();

						if (!this._int_stack.empty())
						{
							this._child_index = this._int_stack.peek().intValue();
						}

						this._int_stack.push(Integer.valueOf(index));
					}
					else
					{
						this._int_stack.pop();
						this._node_stack.push(this._current_node);
						this._int_stack.push(Integer.valueOf(this._child_index));
						this._current_node = this._current_node.getChild(this._child_index);
						this._int_stack.push(Integer.valueOf(0));
						is_post_order = false;
					}
				}
			}
		}
		else
		{
			this._node_stack.push(this._current_node);
			this._child_index = index;
			this._current_node = this._current_node.getChild(this._child_index);
			this._int_stack.push(Integer.valueOf(0));
		}

		return result;
	}
}
