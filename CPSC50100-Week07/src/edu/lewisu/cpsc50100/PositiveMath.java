package edu.lewisu.cpsc50100;

/*
* @brief Math functions that work on positive numbers.
* @author Cromwell D. Enage
*/
public class PositiveMath {

	private static int _computeGCD(int a, int b)
	{
		if (a < 0)
		{
			throw new IllegalArgumentException("negative: a = " + a);
		}

		if (b < 0)
		{
			throw new IllegalArgumentException("negative: b = " + b);
		}

		if (a == 0)
		{
			return b;
		}

		if (b == 0)
		{
			return a;
		}

		while (true)
		{
			if (a == b)
			{
				break;
			}
			else if (a < b)
			{
				b = b - a;
			}
			else
			{
				a = a - b;
			}
		}

		return a;
	}

	/*
	* @brief Computes the GCD of the specified integers.
	* @param a the first integer.
	* @param b the second integer.
	* @return the GCD.
	* @throw IllegalArgumentException if either of the integers is negative.
	* @throw RangeException if the GCD of the specified integers is 1.
	*/
	public static int computeGreatestCommonDivisor(int a, int b)
	{
		int result = _computeGCD(a, b);

		if (result == 1)
		{
			throw new RangeException("GCD(" + a + ", " + b + ") = 1");
		}

		return result;
	}
}
