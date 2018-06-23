package edu.lewisu.cpsc50100;

/*
* @brief Required base data structure.
* @author Michael J. Novak
*/
public class Person implements Comparable<Person> {
	private String firstName;
	private String lastName;

	/*
	* @brief Name-setting constructor.
	* @param first_name this person's first name.
	* @param last_name this person's last name.
	*/
	public Person(String first_name, String last_name)
	{
		this.setName(first_name, last_name);
	}

	/*
	* @brief Default constructor.
	*/
	public Person()
	{
		this("", "");
	}

	/*
	* @brief Name mutator method.
	* @param first_name this person's first name.
	* @param last_name this person's last name.
	*/
	public void setName(String first_name, String last_name)
	{
		this.firstName = first_name;
		this.lastName = last_name;
	}

	/*
	* @brief First-name accessor method.
	* @return this person's first name.
	*/
	public String getFirstName()
	{
		return this.firstName;
	}

	/*
	* @brief Last-name accessor method.
	* @return this person's last name.
	*/
	public String getLastName()
	{
		return this.lastName;
	}

	/*
	* @brief Overriden from java.lang.Object.
	* @return this person's first and last name, separated by a space.
	*/
	public String toString()
	{
		return this.firstName + " " + this.lastName;
	}

	@Override
	public int compareTo(Person other)
	{
		if (this.lastName.equals(other.lastName))
		{
			return this.firstName.compareTo(other.firstName);
		}
		else
		{
			return this.lastName.compareTo(other.lastName);
		}
	}
}
