package edu.lewisu.cpsc50100;

import java.util.ArrayList;

/*
* @author Cromwell D. Enage
*/
public class Variator {
	private ArrayList<Double> _samples;
	private Averager _averager;

	/*
	* @brief Default constructor.
	*/
	public Variator()
	{
		this._samples = new ArrayList<Double>();
		this._averager = new Averager();
	}

	/*
	* @brief Adds the specified sample to the running total.
	*/
	public void add(double sample)
	{
		this._samples.add(new Double(sample));
		this._averager.add(sample);
	}

	/*
	* @brief Computes the variance.
	*/
	public double compute()
	{
		if (_samples.isEmpty())
		{
			return 0.0;
		}

		double average = this._averager.compute();
		double result = 0.0;

		for (int i = 0; i < _samples.size(); ++i)
		{
			double difference = _samples.get(i).doubleValue() - average;
			result += difference * difference;
		}

		return result / _samples.size();
	}
}
