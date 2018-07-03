package edu.lewisu.cpsc50100;

import java.util.ArrayList;

/*
* @brief Simple generic type of tree node whose children will be stored in an array.
* @author Cromwell D. Enage
*/
public class ArrayTreeNode<T> {
	private ArrayList<ArrayTreeNode<T>> _children;
	private ArrayTreeNode<T> _parent;
	private T _data;

	/*
	* @brief Constructs a child-less tree node that will store the specified data.
	* @param data the specified data.
	*/
	public ArrayTreeNode(T data)
	{
		this._children = new ArrayList<ArrayTreeNode<T>>();
		this._parent = null;
		this._data = data;
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
	public ArrayTreeNode<T> getParent()
	{
		return this._parent;
	}

	/*
	* @brief Returns the number of children that this tree node possesses.
	*/
	public int getChildCount()
	{
		return this._children.size();
	}

	/*
	* @brief Returns the child stored at the specified index in this tree node.
	* @param index the specified index.
	* @return the child of this tree node at index.
	*/
	public ArrayTreeNode<T> getChild(int index)
	{
		return this._children.get(index);
	}

	/*
	* @brief Adds the specified child to this tree node.
	* @param child the specified child.
	*/
	public void addChild(ArrayTreeNode<T> child)
	{
		child._parent = this;
		this._children.add(child);
	}
}
