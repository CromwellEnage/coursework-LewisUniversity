package edu.lewisu.cpsc50100;

/*
* @author Cromwell D. Enage
*/
public class Assignment01 {

	/*
	* This "algorithm" parses the command line arguments for its input values.
	* The first argument determines how many test grades there are.
	* All other grades are assignment grades.
	* Each following pair of arguments consists of a grade and its weight.
	* The output is printed to the console rather than returned by the function.
	*/
	public static void main(String[] args) {
		Averager test_averager = new Averager();
		Averager assignment_averager = new Averager();
		int test_count = Integer.parseInt(args[0]) * 2;
		int i = 1;

		while (i < test_count)
		{
			test_averager.add(Integer.parseInt(args[i]) * Double.parseDouble(args[i + 1]));
			i += 2;
		}

		while (i + 1 < args.length)
		{
			assignment_averager.add(Integer.parseInt(args[i]) * Double.parseDouble(args[i + 1]));
			i += 2;
		}

		MasterAverager master_averager = new MasterAverager();

		System.out.println("Test average = " + test_averager.compute());
		master_averager.add(test_averager);
		System.out.println("Assignment average = " + assignment_averager.compute());
		master_averager.add(assignment_averager);
		System.out.println("Master average = " + master_averager.compute());
	}
}
