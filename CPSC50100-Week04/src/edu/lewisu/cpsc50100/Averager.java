package edu.lewisu.cpsc50100;

/*
* @author Cromwell D. Enage
*/
public class Averager {
	private double _total;
	private int _count;

	/*
	* @brief Default constructor.
	*/
	public Averager()
	{
		this._total = 0;
		this._count = 0;
	}

	/*
	* @brief Adds the specified sample to the running total.
	*/
	public void add(double sample)
	{
		this._total += sample;
		++this._count;
	}

	/*
	* @brief Computes the average.
	*/
	public double compute()
	{
		if (this._count == 0)
		{
			return 0.0;
		}

		return this._total / this._count;
	}
}
