package edu.lewisu.cpsc50100;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;

/*
* @author Cromwell D. Enage
*/
public class Assignment04 {
	public static void main(String[] args) {
		Grader grader = new Grader();

		try
		{
			BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(args[0]))
			);
			String line = reader.readLine();
			double max_grade = Double.parseDouble(line);

			while ((line = reader.readLine()) != null)
			{
				grader.add(Double.parseDouble(line) / max_grade);
			}

			reader.close();
		}
		catch (IOException e)
		{
			System.err.println("Oops: " + e.toString());
		}

		java.util.ArrayList<String> grades = grader.compute();

		for (int i = 0; i < grades.size(); ++i)
		{
			System.out.println("The grade of student " + i + " is " + grades.get(i) + ".");
		}
	}
}
