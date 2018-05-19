package edu.lewisu.cpsc50100;

import java.io.*;

/*
* @author Cromwell D. Enage
*/
public class Assignment01 {

	/*
	* This "algorithm" parses the file specified by args[0] for its inputs.
	* The first line determines how many test grades there are.
	* All other grades are assignment grades.
	* Each following pair of lines consists of a grade and its weight.
	* The output is printed to the console rather than returned by the function.
	*/
	public static void main(String[] args) {
		Averager test_averager = new Averager();
		Averager assignment_averager = new Averager();

		try
		{
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(new FileInputStream(args[0]))
					);
			int test_count = Integer.parseInt(reader.readLine()) * 2;

			for (int i = 1; i < test_count; i += 2)
			{
				test_averager.add(
						Integer.parseInt(reader.readLine()) *
						Double.parseDouble(reader.readLine())
						);
			}

			String line = null;

			while ((line = reader.readLine()) != null)
			{
				int grade = Integer.parseInt(line);
				line = reader.readLine();
				if (line == null) break;
				double weight = Double.parseDouble(line);
				assignment_averager.add(grade * weight);
			}

			reader.close();
		}
		catch (IOException e)
		{
		}

		MasterAverager master_averager = new MasterAverager();

		System.out.println("Test average = " + test_averager.compute());
		master_averager.add(test_averager);
		System.out.println("Assignment average = " + assignment_averager.compute());
		master_averager.add(assignment_averager);
		System.out.println("Master average = " + master_averager.compute());
	}
}
