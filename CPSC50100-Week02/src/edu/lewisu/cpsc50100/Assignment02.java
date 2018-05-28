package edu.lewisu.cpsc50100;

import java.io.*;

/*
* @author Cromwell D. Enage
*/
public class Assignment02 {
	public static void main(String[] args) {
		Order order = null;

		try
		{
			String cs_input = null;
			Console cs = System.console();

			if (cs == null)
			{
				System.err.println("No console.");
				System.exit(1);
			}

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(new FileInputStream(args[0]))
					);
			int store_zip_code = Integer.parseInt(reader.readLine());
			double tax_rate = Double.parseDouble(reader.readLine()) * 0.01;

			while (order == null)
			{
				System.out.println("Do you want [p]ickup or [d]elivery?");
				cs_input = cs.readLine();

				if ((cs_input.compareTo("p") == 0) || (cs_input.compareTo("P") == 0))
				{
					order = new Order(tax_rate);
				}
				else if ((cs_input.compareTo("d") == 0) || (cs_input.compareTo("D") == 0))
				{
					System.out.println("Please enter your destination zip code:");

					try
					{
						int your_zip_code = Integer.parseInt(cs.readLine());

						if (
								(store_zip_code - 6 < your_zip_code) &&
								(your_zip_code < store_zip_code + 6)
								)
						{
							order = new Order(tax_rate, your_zip_code, store_zip_code);

							if (order.isDeliveryWithExtraCost())
							{
								System.out.println("Delivery with extra cost.");
							}
							else
							{
								System.out.println("Delivery available.");
							}
						}
						else
						{
							System.out.println("Delivery not available.");
							reader.close();
							return;
						}
					}
					catch (NumberFormatException e)
					{
					}
				}
			}

			String reader_line = null;

			while ((reader_line = reader.readLine()) != null)
			{
				String name = reader_line;
				reader_line = reader.readLine();
				if (reader_line == null) break;
				double unit_price = Double.parseDouble(reader_line);
				cs_input = null;

				do
				{
					System.out.println("How many orders of " + name + " do you want?");
					cs_input = cs.readLine();

					try
					{
						int quantity = Integer.parseInt(cs_input);

						if (quantity < 0)
						{
							cs_input = null;
						}
						else
						{
							order.addItem(name, quantity, unit_price);
						}
					}
					catch (NumberFormatException e)
					{
						cs_input = null;
					}
				}
				while (cs_input == null);
			}

			reader.close();
		}
		catch (IOException e)
		{
		}

		if (order != null)
		{
			System.out.println("Here is your receipt:");

			for (int i = 0; i < order.getItemCount(); ++i)
			{
				System.out.print(order.getItemName(i));
				System.out.print(" (x" + order.getQuantity(i));
				System.out.format("): $%.2f%n", order.getUnitPrice(i) * order.getQuantity(i));
			}

			System.out.println("Tax: " + order.getTaxPercentage() + "%");
			System.out.format("Your total will be $%.2f%n", order.computeTotalPrice());
		}
	}
}
