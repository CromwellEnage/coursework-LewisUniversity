package edu.lewisu.cpsc50100;

/*
* @author Cromwell D. Enage
*/
public class Elevator {
	private int _floor_max;
	private int _floor;
	private int _passenger_count;

	/*
	* Constructors
	*/
	public Elevator(int top_floor)
	{
		this._floor_max = top_floor;
		this._floor = 0;
		this._passenger_count = 0;
	}

	public Elevator()
	{
		this(2);
	}

	/*
	* Accessors
	*/
	public int getFloor()
	{
		return this._floor;
	}

	public int getPassengerCount()
	{
		return this._passenger_count;
	}

	/*
	* @brief Sends one passenger into the elevator.
	* 
	* User code is responsible for removing the passenger from the current floor.
	*/
	public void enterOnePassenger()
	{
		++this._passenger_count;
	}

	/*
	* @brief Removes one passenger from the elevator.
	* 
	* User code is responsible for sending the passenger to the current floor.
	* 
	* @return true if the operation succeeds, false otherwise.
	*/
	public boolean exitOnePassenger()
	{
		if (this.getPassengerCount() > 0)
		{
			--this._passenger_count;
			return true;
		}
		else
		{
			return false;
		}
	}

	/*
	* @brief Moves the elevator up or down from the inside.
	* @param is_up_not_down true to go up, false to go down.
	* @return true if the operation succeeds, false otherwise.
	*/
	public boolean pushInnerButton(boolean is_up_not_down)
	{
		if (this.getPassengerCount() > 0)
		{
			if (is_up_not_down)
			{
				if (this._floor < this._floor_max)
				{
					++this._floor;
					return true;
				}
			}
			else
			{
				if (this._floor > 0)
				{
					--this._floor;
					return true;
				}
			}
		}

		return false;
	}

	/*
	* @brief Moves the elevator toward the specified floor from the outside.
	* @param passenger_floor the specified floor
	* @return true if the operation succeeds, false otherwise.
	* @throw IndexOutOfBoundsException if the specified floor doesn't exist.
	*/
	public boolean pushOuterButtonFrom(int passenger_floor)
	{
		if ((passenger_floor < 0) || (passenger_floor > this._floor_max))
		{
			throw new IndexOutOfBoundsException();
		}

		if (this._floor < passenger_floor)
		{
			++this._floor;
			return true;
		}
		else if (passenger_floor < this._floor)
		{
			--this._floor;
			return true;
		}
		else
		{
			return false;
		}
	}
}
