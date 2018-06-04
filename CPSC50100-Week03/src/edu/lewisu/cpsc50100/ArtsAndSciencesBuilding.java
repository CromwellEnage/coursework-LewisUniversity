package edu.lewisu.cpsc50100;

/*
* @author Cromwell D. Enage
*/
public class ArtsAndSciencesBuilding {
	private int[] _elevator_user_count;
	private Elevator _otis;

	/*
	* Constructor
	*/
	public ArtsAndSciencesBuilding(int popAtB, int popAt1, int popAt2)
	{
		this._elevator_user_count = new int[3];
		this._elevator_user_count[0] = popAtB;
		this._elevator_user_count[1] = popAt1;
		this._elevator_user_count[2] = popAt2;
		this._otis = new Elevator();
	}

	/*
	* Accessors
	*/
	public int getPopulationAtFloor(int floor)
	{
		return this._elevator_user_count[floor];
	}

	public int getPopulationInElevator()
	{
		return this._otis.getPassengerCount();
	}

	public int getFloorOfElevator()
	{
		return this._otis.getFloor();
	}

	/*
	* @brief Sends one passenger from the elevator's current floor to the elevator.
	* @return true if the operation succeeds, false otherwise.
	*/
	public boolean enterOnePassengerToElevator()
	{
		if (this.getPopulationAtFloor(this._otis.getFloor()) > 0)
		{
			this._otis.enterOnePassenger();
			--this._elevator_user_count[this._otis.getFloor()];
			return true;
		}
		else
		{
			return false;
		}
	}

	/*
	* @brief Sends one passenger from the elevator to its current floor.
	* @return true if the operation succeeds, false otherwise.
	*/
	public boolean exitOnePassengerFromElevator()
	{
		if (this._otis.exitOnePassenger())
		{
			++this._elevator_user_count[this._otis.getFloor()];
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
	public boolean moveElevatorFromInside(boolean is_up_not_down)
	{
		return this._otis.pushInnerButton(is_up_not_down);
	}

	/*
	* @brief Moves the elevator toward the specified floor.
	* @param floor the specified floor
	* @return true if the operation succeeds, false otherwise.
	* @throw IndexOutOfBoundsException if the specified floor doesn't exist.
	* @throw IllegalArgumentException if there are no passengers on this floor.
	*/
	public boolean requestElevatorAtFloor(int floor)
	{
		if (this.getPopulationAtFloor(floor) > 0)
		{
			return this._otis.pushOuterButtonFrom(floor);
		}
		else
		{
			throw new IllegalArgumentException("There are no passengers on this floor.");
		}
	}
}
