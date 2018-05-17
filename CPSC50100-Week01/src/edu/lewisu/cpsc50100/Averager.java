package edu.lewisu.cpsc50100;

/*
* @author Cromwell D. Enage
* 
* Although the implementation turned out to be a glorified adding machine,
* the structure of this class should make it more amenable to improvements.
*/
public class Averager {

	private double _total;
//	private int _count;

	public Averager()
	{
		this._total = 0;
//		this._count = 0;
	}

	public void add(double entry)
	{
		this._total += entry;
//		++this._count;
	}

	public double compute()
	{
		return this._total;
		// I forgot that weighted averages work differently from
		// regular averages, so I tried the line below and got
		// a far smaller result than expected.
//		return this._total / this._count;
	}
}
