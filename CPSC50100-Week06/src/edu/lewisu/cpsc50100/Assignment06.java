package edu.lewisu.cpsc50100;

import java.io.*;
import java.util.Iterator;
import java.util.TreeSet;

/*
* @author Cromwell D. Enage
*/
public class Assignment06 {
	private static class PayComparator implements java.util.Comparator<PaidAdvisor> {

		@Override
		public int compare(PaidAdvisor lhs, PaidAdvisor rhs)
		{
			if (lhs.calculatePay() < rhs.calculatePay())
			{
				return -1;
			}
			else if (rhs.calculatePay() < lhs.calculatePay())
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Console cs = System.console();

		if (cs == null)
		{
			System.err.println("No console.");
			System.exit(1);
		}

		System.out.println("Lewis University proudly presents: Fantasy Faculty!");
		System.out.println("Because schoolwork makes the cool work!");
		TreeSet<PaidAdvisor> alphabetical_roster = new TreeSet<PaidAdvisor>();
		TreeSet<PaidAdvisor> paid_roster = new TreeSet<PaidAdvisor>(new PayComparator());
		String cs_input = null;
		Person person = null;
		double regular_pay_rate = 0.0;
		double special_pay_rate = 0.0;
		double overtime_pay_rate = 0.0;
		int hours_worked = -1;
		int hours_special = -1;

		while (true)
		{
			System.out.println("Enter the first name of advisor " + (paid_roster.size() + 1));
			System.out.println("(or enter 'Y' to quit):");
			cs_input = cs.readLine();

			if ((cs_input.compareTo("Y") == 0) || (cs_input.compareTo("y") == 0))
			{
				break;
			}

			System.out.println("Enter " + cs_input + "'s last name:");
			person = new Person(cs_input, cs.readLine());

			do
			{
				System.out.println("Enter " + person + "'s regular pay rate");
				System.out.println("(or enter 'D' for default value of 25,");
				System.out.println("otherwise cannot be less than 1):");
				cs_input = cs.readLine();

				if ((cs_input.compareTo("D") == 0) || (cs_input.compareTo("d") == 0))
				{
					regular_pay_rate = 25.0;
				}
				else
				{
					try
					{
						regular_pay_rate = Double.parseDouble(cs_input);
					}
					catch (NumberFormatException e)
					{
						regular_pay_rate = 0.0;
					}
				}
			}
			while (regular_pay_rate < 1.0);

			do
			{
				System.out.println("Enter " + person + "'s special pay rate");
				System.out.println("(or enter 'D' for default value of 50,");
				System.out.println("otherwise cannot be less than 1):");
				cs_input = cs.readLine();

				if ((cs_input.compareTo("D") == 0) || (cs_input.compareTo("d") == 0))
				{
					special_pay_rate = 50.0;
				}
				else
				{
					try
					{
						special_pay_rate = Double.parseDouble(cs_input);
					}
					catch (NumberFormatException e)
					{
						special_pay_rate = 0.0;
					}
				}
			}
			while (special_pay_rate < 1.0);

			do
			{
				System.out.println("Enter " + person + "'s overtime pay rate");
				System.out.println("(or enter 'D' for default value of time-and-a-half,");
				System.out.println("otherwise cannot be less than 1):");
				cs_input = cs.readLine();

				if ((cs_input.compareTo("D") == 0) || (cs_input.compareTo("d") == 0))
				{
					overtime_pay_rate = regular_pay_rate * 1.5;
				}
				else
				{
					try
					{
						overtime_pay_rate = Double.parseDouble(cs_input);
					}
					catch (NumberFormatException e)
					{
						overtime_pay_rate = 0.0;
					}
				}
			}
			while (overtime_pay_rate < 1.0);

			do
			{
				System.out.println("Enter " + person + "'s total hours worked");
				System.out.println("(cannot be negative):");

				try
				{
					hours_worked = Integer.parseInt(cs.readLine());
				}
				catch (NumberFormatException e)
				{
					hours_worked = -1;
				}
			}
			while (hours_worked < 0);

			do
			{
				System.out.println("Enter " + person + "'s total hours worked in special session");
				System.out.println("(cannot be negative or greater than total hours worked):");

				try
				{
					hours_special = Integer.parseInt(cs.readLine());

					if (hours_worked < hours_special)
					{
						hours_special = -1;
					}
				}
				catch (NumberFormatException e)
				{
					hours_special = -1;
				}
			}
			while (hours_special < 0);

			alphabetical_roster.add(
				new PaidAdvisor(
					person.getFirstName(), person.getLastName(),
					regular_pay_rate, special_pay_rate, overtime_pay_rate,
					hours_worked, hours_special
				)
			);
			paid_roster.add(
				new PaidAdvisor(
					person.getFirstName(), person.getLastName(),
					regular_pay_rate, special_pay_rate, overtime_pay_rate,
					hours_worked, hours_special
				)
			);
		}

		System.out.println("The Fantasy Faculty, in alphabetical order!");

		Iterator<PaidAdvisor> itr = alphabetical_roster.iterator();

		while (itr.hasNext())
		{
			System.out.println(itr.next());
		}

		System.out.println("The Fantasy Faculty, in poverty order!");
		itr = paid_roster.iterator();

		while (itr.hasNext())
		{
			System.out.println(itr.next());
		}
	}
}
