package edu.lewisu.cpsc50100;

import java.math.BigInteger;

/*
* @brief Represents a rectangular set of bits in a two-dimensional space.
* 
* The interface is basic in that only getter and setter methods are provided.
* A more advanced interface would also provide methods to shift bits along each dimension.
* 
* @author Cromwell D. Enage
*/
public class Bitset2D {
	private BigInteger _bits;
	private int _size1;
	private int _size2;

	/*
	* @brief Constructor.
	* @param size1 the bounds at the first dimension.
	* @param size2 the bounds at the second dimension.
	*/
	public Bitset2D(int size1, int size2)
	{
		this._bits = BigInteger.ZERO;
		this._size1 = size1;
		this._size2 = size2;
	}

	private int _getIndex(int index1, int index2)
	{
		if (index1 < 0)
		{
			throw new IndexOutOfBoundsException("index1: " + index1 + " < 0");
		}

		if (index1 >= this._size1)
		{
			throw new IndexOutOfBoundsException("index1: " + index1 + " >= " + this._size1);
		}

		if (index2 < 0)
		{
			throw new IndexOutOfBoundsException("index2: " + index2 + " < 0");
		}

		if (index2 >= this._size2)
		{
			throw new IndexOutOfBoundsException("index2: " + index2 + " >= " + this._size2);
		}

		return index1 * this._size2 + index2;
	}

	/*
	* @brief Returns the bounds at the first dimension.
	*/
	public int size1()
	{
		return this._size1;
	}

	/*
	* @brief Returns the bounds at the second dimension.
	*/
	public int size2()
	{
		return this._size2;
	}

	/*
	* @brief Queries the value of the bit at the specified pair of indices.
	* @param index1 the first index of the pair.
	* @param index2 the second index of the pair.
	* @return true if the bit is set, false if the bit is cleared.
	* @throw IndexOutOfBoundsException if either of the indices are out of bounds.
	*/
	public boolean testBit(int index1, int index2)
	{
		return this._bits.testBit(this._getIndex(index1, index2));
	}

	/*
	* @brief Sets the bit at the specified pair of indices.
	* @param index1 the first index of the pair.
	* @param index2 the second index of the pair.
	* @throw IndexOutOfBoundsException if either of the indices are out of bounds.
	*/
	public void setBit(int index1, int index2)
	{
		this._bits = this._bits.setBit(this._getIndex(index1, index2));
	}

	/*
	* @brief Clears the bit at the specified pair of indices.
	* @param index1 the first index of the pair.
	* @param index2 the second index of the pair.
	* @throw IndexOutOfBoundsException if either of the indices are out of bounds.
	*/
	public void clearBit(int index1, int index2)
	{
		this._bits = this._bits.clearBit(this._getIndex(index1, index2));
	}

	/*
	* @brief Sets and clears bits at random within bounds.
	* @param random the randomizer.
	*/
	public void set(java.util.Random random)
	{
		this._bits = new BigInteger(this._size1 * this._size2, random);
	}
}
