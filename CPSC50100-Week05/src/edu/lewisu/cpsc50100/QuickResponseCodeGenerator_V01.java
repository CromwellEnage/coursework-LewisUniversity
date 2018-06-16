package edu.lewisu.cpsc50100;

/*
* @brief Function to generate a random version-1 QR code.
* 
* Since Bitset2D does not provide bitshifting operations, the position block and timing pattern
* setting routines must overwrite the corresponding randomly generated bits.  Therefore,
* the randomness is nonuniform.
* 
* More information on QR codes can be found at <http://en.wikipedia.org/wiki/QR_code>.
* 
* @author Cromwell D. Enage
*/
public class QuickResponseCodeGenerator_V01
implements java.util.function.Function<java.util.Random, Bitset2D> {

	@Override
	public Bitset2D apply(java.util.Random random)
	{
		Bitset2D qr_code = new Bitset2D(21, 21);

		qr_code.set(random);
		qr_code = QuickResponseCoder.setPositionBlocks(qr_code);
		return QuickResponseCoder.setTimingPatterns(qr_code);
	}
}
