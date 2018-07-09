package edu.lewisu.cpsc50100;

/*
* @brief Simple generic type of tree node with zero to two children.
* @author Cromwell D. Enage
*/
public class BinaryTreeNode<T> {
	private T _data;
	private BinaryTreeNode<T> _parent;
	private BinaryTreeNode<T> _left_child;
	private BinaryTreeNode<T> _right_child;

	/*
	* @brief Constructs a child-less tree node that will store the specified data.
	* @param data the specified data.
	*/
	public BinaryTreeNode(T data)
	{
		this._data = data;
		this._parent = this._left_child = this._right_child = null;
	}

	/*
	* @brief Returns the data stored in this tree node.
	*/
	public T getData()
	{
		return this._data;
	}

	/*
	* @brief Returns the parent of this tree node.
	*/
	public BinaryTreeNode<T> getParent()
	{
		return this._parent;
	}

	/*
	* @brief Returns the left child of this tree node.
	*/
	public BinaryTreeNode<T> getLeftChild()
	{
		return this._left_child;
	}

	/*
	* @brief Returns the right child of this tree node.
	*/
	public BinaryTreeNode<T> getRightChild()
	{
		return this._right_child;
	}

	/*
	* @brief Makes the specified node the left child of this tree node.
	* @param node the specified node.  May be null.
	*/
	public void setLeftChild(BinaryTreeNode<T> node)
	{
		if (this._left_child != null)
		{
			this._left_child._parent = null;
		}

		this._left_child = node;

		if (node != null)
		{
			node._parent = this;
		}
	}

	/*
	* @brief Makes the specified node the right child of this tree node.
	* @param node the specified node.  May be null.
	*/
	public void setRightChild(BinaryTreeNode<T> node)
	{
		if (this._right_child != null)
		{
			this._right_child._parent = null;
		}

		this._right_child = node;

		if (node != null)
		{
			node._parent = this;
		}
	}

	/*
	* @brief Makes the right child node take the place of this one in the tree.
	* 
	* This node will become the left child of the one taking its place in the tree.
	* 
	* @return the tree node taking this one's place in the tree.
	* @throw UnsupportedOperationException if this node has no right children.
	*/
	public BinaryTreeNode<T> rotateLeft()
	{
		BinaryTreeNode<T> pivot = this._right_child;

		if (pivot == null)
		{
			throw new UnsupportedOperationException("This node has no right children.");
		}

		pivot._parent = this._parent;
		this._right_child = pivot._left_child;

		if (this._right_child != null)
		{
			this._right_child._parent = this;
		}

		pivot._left_child = this;

		if (this._parent != null)
		{
			if (this._parent._left_child == this)
			{
				this._parent._left_child = pivot;
			}
			else
			{
				this._parent._right_child = pivot;
			}
		}

		this._parent = pivot;
		return pivot;
	}

	/*
	* @brief Makes the left child node take the place of this one in the tree.
	* 
	* This node will become the right child of the one taking its place in the tree.
	* 
	* @return the tree node taking this one's place in the tree.
	* @throw UnsupportedOperationException if this node has no left children.
	*/
	public BinaryTreeNode<T> rotateRight()
	{
		BinaryTreeNode<T> pivot = this._left_child;

		if (pivot == null)
		{
			throw new UnsupportedOperationException("This node has no left children.");
		}

		pivot._parent = this._parent;
		this._left_child = pivot._right_child;

		if (this._left_child != null)
		{
			this._left_child._parent = this;
		}

		pivot._right_child = this;

		if (this._parent != null)
		{
			if (this._parent._right_child == this)
			{
				this._parent._right_child = pivot;
			}
			else
			{
				this._parent._left_child = pivot;
			}
		}

		this._parent = pivot;
		return pivot;
	}

	/*
	* @brief Swaps this node's data with that of the other one.
	* @param other the other node.
	*/
	public void swapData(BinaryTreeNode<T> other)
	{
		T t = this._data;

		this._data = other._data;
		other._data = t;
	}
}
