package edu.lewisu.cpsc50100;

import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

/*
* @author Cromwell D. Enage
* 
* I also may have over-engineered this one.
* All I needed to store were each of the computed results.
*/
public class MasterAverager {

	private List<Averager> _averagers;

	public MasterAverager()
	{
		this._averagers = new LinkedList<Averager>();
	}

	public void add(Averager averager)
	{
		this._averagers.add(averager);
	}

	public double compute()
	{
		double result = 0.0;
		Iterator<Averager> itr = this._averagers.iterator();

		while (itr.hasNext())
		{
			result += itr.next().compute();
		}

		return result / this._averagers.size();
	}
}
