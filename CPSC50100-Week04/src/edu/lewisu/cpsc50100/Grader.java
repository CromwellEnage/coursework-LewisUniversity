package edu.lewisu.cpsc50100;

import java.util.ArrayList;

/*
* @author Cromwell D. Enage
*/
public class Grader {
	private ArrayList<Double> _samples;
	private Averager _averager;
	private Variator _variator;

	/*
	* @brief Default constructor.
	*/
	public Grader()
	{
		this._samples = new ArrayList<Double>();
		this._averager = new Averager();
		this._variator = new Variator();
	}

	/*
	* @brief Adds the specified sample to the running total.
	*/
	public void add(double sample)
	{
		this._samples.add(new Double(sample));
		this._averager.add(sample);
		this._variator.add(sample);
	}

	/*
	* @brief Converts all samples to letter grades.
	*/
	public ArrayList<String> compute()
	{
		double average = this._averager.compute();
		double standard_deviation = Math.sqrt(this._variator.compute());
		double[] bounds = new double[4];

		bounds[0] = average - 2.0 * standard_deviation;
		bounds[1] = average - 1.0 * standard_deviation;
		bounds[2] = average + 1.0 * standard_deviation;
		bounds[3] = average + 2.0 * standard_deviation;

		// Build a tally to organize students by letter grade.
		// A lower tally index means a higher letter grade.
		ArrayList<ArrayList<Integer>> index_histogram = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < 5; ++i)
		{
			index_histogram.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < this._samples.size(); ++i)
		{
			double actual_grade = this._samples.get(i).doubleValue();

			if (actual_grade < bounds[3])
			{
				if (actual_grade < bounds[1])
				{
					if (actual_grade < bounds[0])
					{
						// F
						index_histogram.get(4).add(new Integer(i));
					}
					else
					{
						// D
						index_histogram.get(3).add(new Integer(i));
					}
				}
				else
				{
					if (actual_grade < bounds[2])
					{
						// C
						index_histogram.get(2).add(new Integer(i));
					}
					else
					{
						// B
						index_histogram.get(1).add(new Integer(i));
					}
				}
			}
			else
			{
				// A
				index_histogram.get(0).add(new Integer(i));
			}
		}

		// Eliminate each grade gap by advancing the students at the lower end.
		java.util.Iterator<ArrayList<Integer>> itr = index_histogram.iterator();

		while (itr.hasNext())
		{
			if (itr.next().isEmpty())
			{
				itr.remove();
			}
		}

		// Use the tally to build the resulting list.
		String[] letter_grades = {"A", "B", "C", "D", "F"};
		ArrayList<String> result = new ArrayList<String>();

		for (int i = 0; i < this._samples.size(); ++i)
		{
			result.add("F");
		}

		for (int i = 0; i < index_histogram.size(); ++i)
		{
			for (int j = 0; j < index_histogram.get(i).size(); ++j)
			{
				result.set(index_histogram.get(i).get(j).intValue(), letter_grades[i]);
			}
		}

		return result;
	}
}
