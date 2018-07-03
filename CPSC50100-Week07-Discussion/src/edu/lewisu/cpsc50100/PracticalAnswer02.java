package edu.lewisu.cpsc50100;

import java.math.BigInteger;
import java.util.Iterator;

/*
* @author Cromwell D. Enage
*/
public class PracticalAnswer02 {

	private static void _iterate(Iterator<BigInteger> itr)
	{
		while (itr.hasNext())
		{
			System.out.print(" " + itr.next());
		}

		System.out.println("");
	}

	private static void _createDescendants(ArrayTreeNode<BigInteger> parent, int n)
	{
		for (int i = 2; i < n; ++i)
		{
			ArrayTreeNode<BigInteger> child = new ArrayTreeNode<BigInteger>(
				FibonacciMath.computeClosedForm(i)
			);

			parent.addChild(child);
			_createDescendants(child, i - 1);
		}
	}

	public static void main(String[] args)
	{
		for (int i = 0; i < 44; ++i)
		{
			if (!FibonacciMath.computeClosedForm(i).equals(FibonacciMath.computeRecursively(i)))
			{
				System.err.println("Fibonacci formulae don't match at n = " + i);
			}
		}

		try
		{
			System.out.println(
				"Fibonacci(100000) recursive = " + FibonacciMath.computeRecursively(100000)
			);
		}
		catch (StackOverflowError error)
		{
			System.err.println(error.toString());
		}

		System.out.println(
			"Fibonacci(100000) non-recursive = " + FibonacciMath.computeClosedForm(100000)
		);

		ArrayTreeNode<BigInteger> root = new ArrayTreeNode<BigInteger>(
			FibonacciMath.computeClosedForm(7)
		);

		_createDescendants(root, 7);

		Iterator<BigInteger> itr = new BreadthFirstIterator<BigInteger>(root);

		System.out.print("Breadth-first iteration:");
		_iterate(itr);
		itr = new PreOrderIterator<BigInteger>(root);
		System.out.print("Prefix-order iteration:");
		_iterate(itr);
	}
}
