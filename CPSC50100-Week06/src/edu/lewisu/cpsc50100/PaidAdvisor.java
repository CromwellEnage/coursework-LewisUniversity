package edu.lewisu.cpsc50100;

/*
* @brief Subclass of Person that earns money over time spent giving advice.
* @author Cromwell D. Enage
*/
public class PaidAdvisor extends Person {
	private double regularPayRate;
	private double specialPayRate;
	private double overtimePayRate;
	private int hoursWorked;
	private int hoursSpecial;

	/*
	* @brief Setting constructor.
	* @param first_name this advisor's first name.
	* @param last_name this advisor's last name.
	* @param regular_pay_rate this advisor's regular pay rate.
	* @param special_pay_rate this advisor's special pay rate.
	* @param overtime_pay_rate this advisor's overtime pay rate.
	* @param total_hours the total number of hours this advisor worked, including in special
	* session.
	* @param special_hours the number of hours this advisor worked in special session.
	* @throw IllegalArgumentException if any of the pay rates are less than 1, if special_hours
	* is negative, or if total_hours is less than special_hours.
	*/
	public PaidAdvisor(
		String first_name, String last_name,
		double regular_pay_rate, double special_pay_rate, double overtime_pay_rate,
		int total_hours, int special_hours
	)
	{
		super(first_name, last_name);
		this.setRateHours(
			regular_pay_rate, special_pay_rate, overtime_pay_rate,
			total_hours, special_hours
		);
	}

	private void setRateHours(
		double regular_pay_rate, double special_pay_rate, double overtime_pay_rate,
		int total_hours, int special_hours
	)
	{
		if (regular_pay_rate < 1.0)
		{
			throw new IllegalArgumentException("Regular pay rate is less than 1.");
		}

		if (special_pay_rate < 1.0)
		{
			throw new IllegalArgumentException("Special pay rate is less than 1.");
		}

		if (overtime_pay_rate < 1.0)
		{
			throw new IllegalArgumentException("Overtime pay rate is less than 1.");
		}

		if (special_hours < 0)
		{
			throw new IllegalArgumentException(
				"Hours spent in special session cannot be negative."
			);
		}

		if (total_hours < special_hours)
		{
			throw new IllegalArgumentException(
				"Total hours cannot be less than hours spent in special session."
			);
		}

		this.regularPayRate = regular_pay_rate;
		this.specialPayRate = special_pay_rate;
		this.overtimePayRate = overtime_pay_rate;
		this.hoursWorked = total_hours;
		this.hoursSpecial = special_hours;
	}

	/*
	* @brief Setting method.
	* @param first_name this advisor's first name.
	* @param last_name this advisor's last name.
	* @param regular_pay_rate this advisor's regular pay rate.
	* @param special_pay_rate this advisor's special pay rate.
	* @param overtime_pay_rate this advisor's overtime pay rate.
	* @param total_hours the total number of hours this advisor worked, including in special
	* session.
	* @param special_hours the number of hours this advisor worked in special session.
	* @throw IllegalArgumentException if any of the pay rates are less than 1, if special_hours
	* is negative, or if total_hours is less than special_hours.
	*/
	public void setNameRateHours(
		String first_name, String last_name,
		double regular_pay_rate, double special_pay_rate, double overtime_pay_rate,
		int total_hours, int special_hours
	)
	{
		this.setName(first_name, last_name);
		this.setRateHours(
			regular_pay_rate, special_pay_rate, overtime_pay_rate,
			total_hours, special_hours
		);
	}

	/*
	* @brief Returns the overtime or the regular pay rate, depending on the specified flag.
	* @param is_overtime the specified flag.
	* @return the overtime pay rate if is_overtime is true, the regular pay rate otherwise.
	*/
	public double getPayRate(boolean is_overtime)
	{
		return is_overtime ? this.overtimePayRate : this.regularPayRate;
	}

	/*
	* @brief Special pay rate accessor method.
	* @return this advisor's special pay rate.
	*/
	public double getSpecialPayRate()
	{
		return this.specialPayRate;
	}

	/*
	* @brief Returns the special or the regular hours worked, depending on the specified flag.
	* @param is_special the specified flag.
	* @return the number of hours this advisor worked in special session if is_special is true,
	* the number of regular hours out of special session this advisor worked otherwise.
	*/
	public int getHoursWorked(boolean is_special)
	{
		return is_special ? this.hoursSpecial : (this.hoursWorked - this.hoursSpecial);
	}

	/*
	* @brief Computes the total pay.
	* @return this advisor's total pay.
	*/
	public double calculatePay()
	{
		int regular_hours_worked = this.getHoursWorked(false);
		int overtime_hours_worked = regular_hours_worked - 30;

		if (overtime_hours_worked < 0)
		{
			overtime_hours_worked = 0;
		}
		else
		{
			regular_hours_worked = 30;
		}

		return (
			regular_hours_worked * this.getPayRate(false) +
			this.getHoursWorked(true) * this.getSpecialPayRate() +
			overtime_hours_worked * this.getPayRate(true)
		);
	}

	/*
	* @brief Overriden from Person.
	* @return a sentence conveying the name and total earnings of this advisor.
	*/
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		java.util.Formatter formatter = new java.util.Formatter(sb, java.util.Locale.US);
	
		formatter.format("%s has earned $%.2f total.", super.toString(), this.calculatePay());
		formatter.close();
		return new String(sb);
	}
}
