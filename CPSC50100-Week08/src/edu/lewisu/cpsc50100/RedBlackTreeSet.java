package edu.lewisu.cpsc50100;

import java.util.Iterator;

/*
* @brief Simplified generic red-black tree implementation of a non-repeating set.
* @author Cromwell D. Enage
*/
public class RedBlackTreeSet<T extends Comparable<T>> {
	private BinaryTreeNode<RedBlack<T>> _root_sentinel;
	private int _size;

	/*
	* @brief Default constructor.
	*/
	public RedBlackTreeSet()
	{
		this._root_sentinel = new BinaryTreeNode<RedBlack<T>>(
			new RedBlack<T>(null, false)
		);
		this._size = 0;
	}

	/*
	* @brief Returns the number of elements that this tree contains.
	*/
	public int size()
	{
		return this._size;
	}

	/*
	* @brief Returns true if this tree contains no elements, false otherwise.
	*/
	public boolean isEmpty()
	{
		return this._root_sentinel.getLeftChild() == null;
	}

	/*
	* @brief Returns true if this tree contains the specified element, false otherwise.
	* @param t the specified element.
	* @return true if 
	*/
	public boolean contains(T t)
	{
		BinaryTreeNode<RedBlack<T>> node = this._root_sentinel.getLeftChild();

		while (node != null)
		{
			switch (node.getData().getData().compareTo(t))
			{
				case -1:
					node = node.getLeftChild();
					break;
				case 1:
					node = node.getRightChild();
					break;
				default:
					return true;
			}
		}

		return false;
	}

	public Iterator<RedBlack<T>> iterator()
	{
		return new InOrderIterator<RedBlack<T>>(this._root_sentinel);
	}

	public boolean add(T t)
	{
		if (this.isEmpty())
		{
			this._root_sentinel.setLeftChild(
				new BinaryTreeNode<RedBlack<T>>(new RedBlack<T>(t, false))
			);
			return true;
		}

		BinaryTreeNode<RedBlack<T>> p = this._root_sentinel.getLeftChild();
		boolean is_not_duplicate = true;

		while (is_not_duplicate)
		{
			switch (p.getData().getData().compareTo(t))
			{
				case -1:
					if (p.getLeftChild() != null)
					{
						p = p.getLeftChild();
					}
					else
					{
						p.setLeftChild(
							new BinaryTreeNode<RedBlack<T>>(new RedBlack<T>(t, false))
						);
						/*p = p.getLeftChild();

						BinaryTreeNode<RedBlack<T>> n = RedBlackBalancer.postAdd(p);

						if (n.getParent() == null)
						{
							this._root_sentinel.setLeftChild(n);
						}*/

						return true;
					}
					break;
				case 1:
					if (p.getRightChild() != null)
					{
						p = p.getRightChild();
					}
					else
					{
						p.setRightChild(
							new BinaryTreeNode<RedBlack<T>>(new RedBlack<T>(t, false))
						);
						/*p = p.getRightChild();

						BinaryTreeNode<RedBlack<T>> n = RedBlackBalancer.postAdd(p);

						if (n.getParent() == null)
						{
							this._root_sentinel.setLeftChild(n);
						}*/

						return true;
					}
					break;
				default:
					is_not_duplicate = false;
			}
		}

		return false;
	}

	public boolean remove(T t)
	{
		BinaryTreeNode<RedBlack<T>> p = this._root_sentinel.getLeftChild();
		BinaryTreeNode<RedBlack<T>> q = null;
		BinaryTreeNode<RedBlack<T>> s = null;

		while (p != null)
		{
			switch (p.getData().getData().compareTo(t))
			{
				case -1:
					p = p.getLeftChild();
					break;
				case 1:
					p = p.getRightChild();
					break;
				default:
					while (true)
					{
						s = p.getLeftChild();

						if (
							(s != null) && (
								(
									p.getRightChild() == null
								) || RedBlackBalancer.choosePredecessor(p)
							)
						)
						{
							while (s.getRightChild() != null)
							{
								s = s.getRightChild();
							}

							if (s.getParent() == p)
							{
								if (p.getRightChild() == null)
								{
									q = p;
									p.swapData(s);

									if (this._root_sentinel.getLeftChild() == p)
									{
										this._root_sentinel.setLeftChild(s);
									}

									p = s;
									s = q;

									boolean must_rebalance = RedBlackBalancer.preRemove(s);

									p.setLeftChild(null);

									if (must_rebalance)
									{
										p = RedBlackBalancer.postRemoveLeft(p);

										if (p.getParent() == null)
										{
											this._root_sentinel.setLeftChild(p);
										}
									}

									break;
								}
							}
						}
					}
					return true;
			}
		}

		return false;
	}

	/*
	* @brief Removes all elements from this tree.
	*/
	public void clear()
	{
		this._root_sentinel.setLeftChild(null);
		this._size = 0;
	}
}
