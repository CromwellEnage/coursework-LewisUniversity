package edu.lewisu.cpsc50100;

/*
* @brief Functions to facilitate balancing of a red-black tree.
* @author Cromwell D. Enage
*/
public class RedBlackBalancer {

	public static <T> BinaryTreeNode<RedBlack<T>>
	postFill(BinaryTreeNode<RedBlack<T>> node, int n)
	{
		int counter = 0;

		if (0 < n - ++counter)
		{
			int black_count = counter;

			while (black_count < n)
			{
				black_count <<= counter;
			}

			if (black_count == n + counter)
			{
				BreadthFirstIterator<RedBlack<T>> itr = new BreadthFirstIterator<
					RedBlack<T>
				>(node);

				while (itr.hasNext())
				{
					itr.next().setBlack();
				}
			}
			else
			{
				black_count >>= counter;
				--black_count;

				BreadthFirstIterator<RedBlack<T>> itr = new BreadthFirstIterator<
					RedBlack<T>
				>(node);

				for (--counter; counter < black_count; ++counter)
				{
					itr.next().setBlack();
				}

				while (itr.hasNext())
				{
					itr.next().setRed();
				}
			}
		}

		return node;
	}

	public static <T> BinaryTreeNode<RedBlack<T>>
	postAdd(BinaryTreeNode<RedBlack<T>> node)
	{
		node.getData().setRed();

		BinaryTreeNode<RedBlack<T>> parent = null;
		BinaryTreeNode<RedBlack<T>> grandparent = null;
		BinaryTreeNode<RedBlack<T>> uncle = null;

		while ((parent = node.getParent()) != null)
		{
			if (parent.getData().isBlack())
			{
				return node;
			}

			grandparent = parent.getParent();

			if (parent == grandparent.getLeftChild())
			{
				uncle = grandparent.getRightChild();

				if (uncle != null)
				{
					if (uncle.getData().isRed())
					{
						parent.getData().setBlack();
						uncle.getData().setBlack();
						grandparent.getData().setRed();
						continue;
					}
				}
			}
			else
			{
				uncle = grandparent.getLeftChild();

				if (uncle != null)
				{
					if (uncle.getData().isRed())
					{
						parent.getData().setBlack();
						uncle.getData().setBlack();
						grandparent.getData().setRed();
						continue;
					}
				}
			}

			if ((node == parent.getRightChild()) && (parent == grandparent.getLeftChild()))
			{
				parent = parent.rotateLeft();
				node = node.getLeftChild();
			}
			else if ((node == parent.getLeftChild()) && (parent == grandparent.getRightChild()))
			{
				parent = parent.rotateRight();
				node = node.getRightChild();
			}

			grandparent = parent.getParent();
			parent.getData().setBlack();
			grandparent.getData().setRed();

			if (node == parent.getLeftChild())
			{
				return grandparent.rotateRight();
			}
			else
			{
				return grandparent.rotateLeft();
			}
		}

		node.getData().setBlack();
		return node;
	}

	public static <T> boolean choosePredecessor(BinaryTreeNode<RedBlack<T>> node)
	{
		node = node.getRightChild();

		while (node.getLeftChild() != null)
		{
			node = node.getLeftChild();
		}

		return node.getData().isBlack() && (node.getLeftChild() == null) && (
			node.getRightChild() == null
		);
	}

	public static <T> boolean preRemove(BinaryTreeNode<RedBlack<T>> node)
	{
		return node.getData().isBlack();
	}

	private static <T> BinaryTreeNode<RedBlack<T>>
	_balance(BinaryTreeNode<RedBlack<T>> node)
	{
		BinaryTreeNode<RedBlack<T>> parent = null;
		BinaryTreeNode<RedBlack<T>> sibling = null;
		BinaryTreeNode<RedBlack<T>> nephew = null;

		for (
			boolean is_left = false;
			(parent = node.getParent()) != null;
			node = parent
		)
		{
			is_left = (node == parent.getLeftChild());
			sibling = is_left ? parent.getRightChild() : parent.getLeftChild();

			if (sibling.getData().isRed())
			{
				// Case 5.1
				parent.getData().setRed();
				sibling.getData().setBlack();

				if (is_left)
				{
					parent.rotateLeft();
					sibling = parent.getRightChild();
				}
				else
				{
					parent.rotateRight();
					sibling = parent.getLeftChild();
				}

				sibling.getData().setRed();
				parent.getData().setBlack();
				return parent.getParent();
			}

			nephew = is_left ? sibling.getRightChild() : sibling.getLeftChild();

			if (nephew != null)
			{
				if (nephew.getData().isRed())
				{
					// Case 5.3.2
					nephew.getData().setBlack();
					sibling.getData().setToColorOf(parent.getData());
					parent.getData().setBlack();
					return is_left ? parent.rotateLeft() : parent.rotateRight();
				}
			}

			nephew = is_left ? sibling.getLeftChild() : sibling.getRightChild();

			if (nephew != null)
			{
				if (nephew.getData().isRed())
				{
					// Case 5.3.1
					sibling = is_left ? sibling.rotateRight() : sibling.rotateLeft();
					sibling.getData().setToColorOf(parent.getData());
					parent.getData().setBlack();
					return is_left ? parent.rotateLeft() : parent.rotateRight();
				}
			}

			// Case 5.2
			sibling.getData().setRed();

			if (parent.getData().isRed())
			{
				parent.getData().setBlack();
				return parent;
			}
		}

		return node;
	}

	public static <T> BinaryTreeNode<RedBlack<T>>
	postRemoveLeft(BinaryTreeNode<RedBlack<T>> node)
	{
		BinaryTreeNode<RedBlack<T>> result = null;
		BinaryTreeNode<RedBlack<T>> child = node.getRightChild();

		if (child.getData().isRed())
		{
			// Case 5.1: right sibling
			node.getData().setRed();
			child.getData().setBlack();
			result = node.rotateLeft();
			child = node.getRightChild();
		}

		BinaryTreeNode<RedBlack<T>> grandchild = child.getRightChild();

		if (grandchild != null)
		{
			// Case 5.3.2: right sibling
			grandchild.getData().setBlack();
			child.getData().setToColorOf(node.getData());

			if (result == null)
			{
				result = node.rotateLeft();
			}
			else
			{
				node.rotateLeft();
			}

			return result;
		}

		grandchild = child.getLeftChild();

		if (grandchild != null)
		{
			// Case 5.3.1: right sibling
			child = child.rotateRight();
			child.getData().setToColorOf(node.getData());

			if (result == null)
			{
				result = node.rotateLeft();
			}
			else
			{
				node.rotateLeft();
			}

			return result;
		}

		// Case 5.2: right sibling
		child.getData().setRed();

		if (result != null)
		{
			node.getData().setBlack();
		}
		else if (node.getData().isRed())
		{
			node.getData().setBlack();
			result = node;
		}
		else
		{
			result = _balance(node);
		}

		return result;
	}

	public static <T> BinaryTreeNode<RedBlack<T>>
	postRemoveRight(BinaryTreeNode<RedBlack<T>> node)
	{
		BinaryTreeNode<RedBlack<T>> result = null;
		BinaryTreeNode<RedBlack<T>> child = node.getLeftChild();

		if (child.getData().isRed())
		{
			// Case 5.1: left sibling
			node.getData().setRed();
			child.getData().setBlack();
			result = node.rotateRight();
			child = node.getLeftChild();
		}

		BinaryTreeNode<RedBlack<T>> grandchild = child.getLeftChild();

		if (grandchild != null)
		{
			// Case 5.3.2: left sibling
			grandchild.getData().setBlack();
			child.getData().setToColorOf(node.getData());

			if (result == null)
			{
				result = node.rotateRight();
			}
			else
			{
				node.rotateRight();
			}

			return result;
		}

		grandchild = child.getRightChild();

		if (grandchild != null)
		{
			// Case 5.3.1: left sibling
			child = child.rotateLeft();
			child.getData().setToColorOf(node.getData());

			if (result == null)
			{
				result = node.rotateRight();
			}
			else
			{
				node.rotateRight();
			}

			return result;
		}

		// Case 5.2: left sibling
		child.getData().setRed();

		if (result != null)
		{
			node.getData().setBlack();
		}
		else if (node.getData().isRed())
		{
			node.getData().setBlack();
			result = node;
		}
		else
		{
			result = _balance(node);
		}

		return result;
	}
}
