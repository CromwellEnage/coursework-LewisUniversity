package edu.lewisu.cpsc50100;

/*
* @brief Function to generate a random version-10 QR code.
* 
* Since Bitset2D does not provide bitshifting operations, the position block, timing pattern,
* and alignment block setting routines must overwrite the corresponding randomly generated
* bits.  Therefore, the randomness is nonuniform.
* 
* More information on QR codes can be found at <http://en.wikipedia.org/wiki/QR_code>.
* 
* @author Cromwell D. Enage
*/
public class QuickResponseCodeGenerator_V10
implements java.util.function.Function<java.util.Random, Bitset2D> {

	@Override
	public Bitset2D apply(java.util.Random random)
	{
		Bitset2D qr_code = new Bitset2D(57, 57);

		qr_code.set(random);
		qr_code = QuickResponseCoder.setPositionBlocks(qr_code);
		qr_code = QuickResponseCoder.setTimingPatterns(qr_code);
		return QuickResponseCoder.setAlignmentBlocks(qr_code, 22, 22);
	}
}
