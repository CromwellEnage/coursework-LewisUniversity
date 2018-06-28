package edu.lewisu.cpsc50100;

/*
* @brief Type of runtime exception thrown on bad return values of PositiveMath functions.
* @author Cromwell D. Enage
*/
public class RangeException extends RuntimeException {

	/*
	* @brief Required by Serializable interface.
	*/
	private static final long serialVersionUID = 1L;

	/*
	* @brief Default constructor.
	*/
	public RangeException()
	{
		super();
	}

	/*
	* @brief Constructor with specified message.
	*/
	public RangeException(String s)
	{
		super(s);
	}
}
