package edu.lewisu.cpsc50100;

/*
* @brief Collection of routines to write the bits underlying the required patterns.
* 
* These routines are simple but slightly suboptimal.  For version-10 and above QR codes,
* the timing pattern and alignment block setting routines have overlap.
* 
* @author Cromwell D. Enage
*/
public class QuickResponseCoder {
	/*
	* @brief Writes the bits underlying the position blocks.
	* @param qr_code the target bitset.
	* @return qr_code
	* @throw IndexOutOfBoundsException if qr_code isn't large enough.
	*/
	public static Bitset2D setPositionBlocks(Bitset2D qr_code)
	{
		int size1 = qr_code.size1();
		int size2 = qr_code.size2();

		for (int i = 0; i < 7; ++i)
		{
			qr_code.clearBit(i, 7);
			qr_code.clearBit(7, i);
			qr_code.clearBit(size1 - 1 - i, 7);
			qr_code.clearBit(size1 - 8, i);
			qr_code.clearBit(i, size2 - 8);
			qr_code.clearBit(7, size2 - 1 - i);
		}

		qr_code.clearBit(7, 7);
		qr_code.clearBit(7, size2 - 8);
		qr_code.clearBit(size1 - 8, 7);

		for (int i = 0; i < 6; ++i)
		{
			qr_code.setBit(i, 0);
			qr_code.setBit(6, i);
			qr_code.setBit(6 - i, 6);
			qr_code.setBit(0, 6 - i);
			qr_code.setBit(size1 - 1 - i, 0);
			qr_code.setBit(size1 - 7, i);
			qr_code.setBit(size1 - 7 + i, 6);
			qr_code.setBit(size1 - 1, 6 - i);
			qr_code.setBit(i, size2 - 1);
			qr_code.setBit(6, size2 - 1 - i);
			qr_code.setBit(6 - i, size2 - 7);
			qr_code.setBit(0, size2 - 7 + i);
		}

		for (int i = 1; i < 5; ++i)
		{
			qr_code.clearBit(i, 1);
			qr_code.clearBit(5, i);
			qr_code.clearBit(6 - i, 5);
			qr_code.clearBit(1, 6 - i);
			qr_code.clearBit(size1 - 1 - i, 1);
			qr_code.clearBit(size1 - 6, i);
			qr_code.clearBit(size1 - 7 + i, 5);
			qr_code.clearBit(size1 - 2, 6 - i);
			qr_code.clearBit(i, size2 - 2);
			qr_code.clearBit(5, size2 - 1 - i);
			qr_code.clearBit(6 - i, size2 - 6);
			qr_code.clearBit(1, size2 - 7 + i);
		}

		for (int i = 2; i < 5; ++i)
		{
			for (int j = 2; j < 5; ++j)
			{
				qr_code.setBit(i, j);
				qr_code.setBit(size1 - 1 - i, j);
				qr_code.setBit(i, size2 - 1 - j);
			}
		}

		return qr_code;
	}

	/*
	* @brief Writes the bits underlying the timing patterns.
	* @param qr_code the target bitset.
	* @return qr_code
	* @throw IndexOutOfBoundsException if qr_code isn't large enough.
	*/
	public static Bitset2D setTimingPatterns(Bitset2D qr_code)
	{
		for (int i = 8; i < qr_code.size1() - 7; i += 2)
		{
			qr_code.setBit(i, 6);
		}

		for (int j = 8; j < qr_code.size2() - 7; j += 2)
		{
			qr_code.setBit(6, j);
		}

		return qr_code;
	}

	private static Bitset2D setAlignmentBlock(Bitset2D qr_code, int index1, int index2)
	{
		qr_code.clearBit(index1 - 1, index2 - 1);
		qr_code.clearBit(index1 - 1, index2);
		qr_code.clearBit(index1 - 1, index2 + 1);
		qr_code.clearBit(index1, index2 - 1);
		qr_code.setBit(index1, index2);
		qr_code.clearBit(index1, index2 + 1);
		qr_code.clearBit(index1 + 1, index2 - 1);
		qr_code.clearBit(index1 + 1, index2);
		qr_code.clearBit(index1 + 1, index2 + 1);

		int i0 = index1 - 2;
		int i1 = index1 + 2;
		int j0 = index2 - 2;
		int j1 = index2 + 2;

		for (int k = i0; k < i1; ++k)
		{
			qr_code.setBit(k, j0);
		}

		for (int k = j0; k < j1; ++k)
		{
			qr_code.setBit(i1, k);
		}

		for (int k = i1; i0 < k; --k)
		{
			qr_code.setBit(k, j1);
		}

		for (int k = j1; j0 < k; --k)
		{
			qr_code.setBit(i0, k);
		}

		return qr_code;
	}

	/*
	* @brief Writes the bits underlying the alignment block at the bottom right corner.
	* @param qr_code the target bitset.
	* @return qr_code
	* @throw IndexOutOfBoundsException if qr_code isn't large enough.
	*/
	public static Bitset2D setCornerAlignmentBlock(Bitset2D qr_code)
	{
		return setAlignmentBlock(qr_code, qr_code.size1() - 7, qr_code.size2() - 7);
	}

	/*
	* @brief Writes the bits underlying the alignment blocks.
	* @param qr_code the target bitset.
	* @param offset1 the distance between each block in the 1st dimension.
	* @param offset2 the distance between each block in the 2nd dimension.
	* @return qr_code
	* @throw IndexOutOfBoundsException if qr_code isn't large enough.
	*/
	public static Bitset2D setAlignmentBlocks(Bitset2D qr_code, int offset1, int offset2)
	{
		for (int i = 6 + offset1; i < qr_code.size1() - 7; i += offset1)
		{
			qr_code = setAlignmentBlock(qr_code, i, 6);
		}

		for (int j = 6 + offset2; j < qr_code.size2() - 7; j += offset2)
		{
			qr_code = setAlignmentBlock(qr_code, 6, j);
		}

		for (int i = 6 + offset1; i < qr_code.size1(); i += offset1)
		{
			for (int j = 6 + offset2; j < qr_code.size2(); j += offset2)
			{
				qr_code = setAlignmentBlock(qr_code, i, j);
			}
		}

		return qr_code;
	}
}
