package edu.lewisu.cpsc50100;

import java.io.*;

/*
* @author Cromwell D. Enage
*/
public class Assignment03 {
	public static void main(String[] args) throws IOException {
		Console cs = System.console();

		if (cs == null)
		{
			System.err.println("No console.");
			System.exit(1);
		}

		System.out.println("Otis Elevators proudly presents:");
		System.out.println("The Otis Retro-Elevator, a Lewis University exclusive!");
		ArtsAndSciencesBuilding ans_building = new ArtsAndSciencesBuilding(3, 4, 5);
		String cs_input = null;

		while (true)
		{
			switch (ans_building.getFloorOfElevator())
			{
				case 0:
				{
					System.out.println("The elevator is in the basement.");
					break;
				}

				case 1:
				{
					System.out.println("The elevator is in the 1st floor.");
					break;
				}

				case 2:
				{
					System.out.println("The elevator is in the 2nd floor.");
					break;
				}
			}

			System.out.print(
				(ans_building.getPopulationInElevator() == 1) ? "There is" : "There are"
			);
			System.out.print(" " + ans_building.getPopulationInElevator());
			System.out.print(
				(ans_building.getPopulationInElevator() == 1) ? " person" : " people"
			);
			System.out.println(" in the elevator.");
			System.out.print(
				(ans_building.getPopulationAtFloor(0) == 1) ? "There is" : "There are"
			);
			System.out.print(" " + ans_building.getPopulationAtFloor(0));
			System.out.print(
				(ans_building.getPopulationAtFloor(0) == 1) ? " person" : " people"
			);
			System.out.println(" in the basement.");
			System.out.print(
				(ans_building.getPopulationAtFloor(1) == 1) ? "There is" : "There are"
			);
			System.out.print(" " + ans_building.getPopulationAtFloor(1));
			System.out.print(
				(ans_building.getPopulationAtFloor(1) == 1) ? " person" : " people"
			);
			System.out.println(" in the 1st floor.");
			System.out.print(
				(ans_building.getPopulationAtFloor(2) == 1) ? "There is" : "There are"
			);
			System.out.print(" " + ans_building.getPopulationAtFloor(2));
			System.out.print(
				(ans_building.getPopulationAtFloor(2) == 1) ? " person" : " people"
			);
			System.out.println(" in the 2nd floor.");
			System.out.println("What would you like to do?");
			System.out.println("(I)  Send a passenger from the current floor to the elevator.");
			System.out.println("(O)  Send a passenger from the elevator to the current floor.");
			System.out.println("(U)  Move the elevator up one floor.");
			System.out.println("(2U) Move the elevator up two floors.");
			System.out.println("(D)  Move the elevator down one floor.");
			System.out.println("(2D) Move the elevator down two floors.");
			System.out.println("(0F) Move the elevator one floor toward the basement.");
			System.out.println("(1F) Move the elevator one floor toward the 1st floor.");
			System.out.println("(2F) Move the elevator one floor toward the 2nd floor.");
			System.out.println("(X)  Finish the grand opening.");
			cs_input = cs.readLine();

			if ((cs_input.compareTo("X") == 0) || (cs_input.compareTo("x") == 0))
			{
				System.out.println("Otis Elevators: good to the last drop!");
				return;
			}
			else if ((cs_input.compareTo("I") == 0) || (cs_input.compareTo("i") == 0))
			{
				if (ans_building.enterOnePassengerToElevator())
				{
					System.out.println("The elevator door opened.");
					System.out.println("One passenger entered the elevator.");
					System.out.println("The elevator door closed.");
				}
				else
				{
					System.out.println("No one is waiting for the elevator on this floor.");
				}
			}
			else if ((cs_input.compareTo("O") == 0) || (cs_input.compareTo("o") == 0))
			{
				if (ans_building.exitOnePassengerFromElevator())
				{
					System.out.println("The elevator door opened.");
					System.out.println("One passenger exited the elevator.");
					System.out.println("The elevator door closed.");
				}
				else
				{
					System.out.println("The elevator is empty.");
				}
			}
			else if ((cs_input.compareTo("U") == 0) || (cs_input.compareTo("u") == 0))
			{
				if (ans_building.getPopulationInElevator() == 0)
				{
					System.out.println("No one is in the elevator.");
				}
				else if (ans_building.moveElevatorFromInside(true))
				{
					System.out.println("The elevator has moved one floor up.");
				}
				else
				{
					System.out.println("The elevator has not moved.");
				}
			}
			else if ((cs_input.compareTo("2U") == 0) || (cs_input.compareTo("2u") == 0))
			{
				if (ans_building.getPopulationInElevator() == 0)
				{
					System.out.println("No one is in the elevator.");
				}
				else if (ans_building.moveElevatorFromInside(true))
				{
					if (ans_building.moveElevatorFromInside(true))
					{
						System.out.println("The elevator has moved two floors up.");
					}
					else
					{
						System.out.println("The elevator has moved one floor up.");
					}
				}
				else
				{
					System.out.println("The elevator has not moved.");
				}
			}
			else if ((cs_input.compareTo("D") == 0) || (cs_input.compareTo("d") == 0))
			{
				if (ans_building.getPopulationInElevator() == 0)
				{
					System.out.println("No one is in the elevator.");
				}
				else if (ans_building.moveElevatorFromInside(false))
				{
					System.out.println("The elevator has moved one floor down.");
				}
				else
				{
					System.out.println("The elevator has not moved.");
				}
			}
			else if ((cs_input.compareTo("2D") == 0) || (cs_input.compareTo("2d") == 0))
			{
				if (ans_building.getPopulationInElevator() == 0)
				{
					System.out.println("No one is in the elevator.");
				}
				else if (ans_building.moveElevatorFromInside(false))
				{
					if (ans_building.moveElevatorFromInside(false))
					{
						System.out.println("The elevator has moved two floors down.");
					}
					else
					{
						System.out.println("The elevator has moved one floor down.");
					}
				}
				else
				{
					System.out.println("The elevator has not moved.");
				}
			}
			else if ((cs_input.compareTo("0F") == 0) || (cs_input.compareTo("0f") == 0))
			{
				if (ans_building.getPopulationAtFloor(0) == 0)
				{
					System.out.println("No one is in the basement.");
				}
				else if (ans_building.requestElevatorAtFloor(0))
				{
					System.out.println("The elevator has moved one floor toward the basement.");
				}
				else
				{
					System.out.println("The elevator has not moved.");
				}
			}
			else if ((cs_input.compareTo("1F") == 0) || (cs_input.compareTo("1f") == 0))
			{
				if (ans_building.getPopulationAtFloor(1) == 0)
				{
					System.out.println("No one is in the basement.");
				}
				else if (ans_building.requestElevatorAtFloor(1))
				{
					System.out.println("The elevator has moved toward the 1st floor.");
				}
				else
				{
					System.out.println("The elevator has not moved.");
				}
			}
			else if ((cs_input.compareTo("2F") == 0) || (cs_input.compareTo("2f") == 0))
			{
				if (ans_building.getPopulationAtFloor(2) == 0)
				{
					System.out.println("No one is in the basement.");
				}
				else if (ans_building.requestElevatorAtFloor(2))
				{
					System.out.println("The elevator has moved toward the 2nd floor.");
				}
				else
				{
					System.out.println("The elevator has not moved.");
				}
			}
		}
	}
}
