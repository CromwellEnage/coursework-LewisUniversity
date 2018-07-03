package edu.lewisu.cpsc50100;

import java.math.BigDecimal;
import java.math.BigInteger;

/*
* @brief Equivalent functions to compute the nth integer in the Fibonacci sequence.
* 
* The formulas behind the algorithms can be found at
* <http://en.wikipedia.org/wiki/Fibonacci_number>.
* 
* @author Cromwell D. Enage
*/
public class FibonacciMath {

	/*
	* @brief Computes the nth integer in the Fibonacci sequence using recursion.
	* @param n the target position in the Fibonacci sequence.
	* @return the integer at the nth position in the Fibonacci sequence.
	*/
	public static BigInteger computeRecursively(int n)
	{
		if (n < 1)
		{
			return BigInteger.ZERO;
		}
		else if (n == 1)
		{
			return BigInteger.ONE;
		}
		else
		{
			return computeRecursively(n - 1).add(computeRecursively(n - 2));
		}
	}

	/*
	* @brief Computes the nth integer in the Fibonacci sequence using the closed form.
	* @param n the target position in the Fibonacci sequence.
	* @return the integer at the nth position in the Fibonacci sequence.
	*/
	public static BigInteger computeClosedForm(int n)
	{
		BigDecimal sqrt_5 = BigDecimal.valueOf(Math.sqrt(5.0));
		return sqrt_5.add(BigDecimal.ONE).multiply(BigDecimal.valueOf(0.5)).pow(
			n
		).multiply(BigDecimal.valueOf(2.0)).add(sqrt_5).multiply(
			BigDecimal.valueOf(0.5)
		).divide(sqrt_5, java.math.RoundingMode.FLOOR).toBigInteger();
	}
}
