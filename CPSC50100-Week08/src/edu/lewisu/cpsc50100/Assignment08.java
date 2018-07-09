package edu.lewisu.cpsc50100;

import java.io.*;
import java.util.Iterator;

public class Assignment08 {

	public static void main(String[] args)
	{
		RedBlackTreeSet<String> string_tree = new RedBlackTreeSet<String>();

		try
		{
			Console cs = System.console();

			if (cs == null)
			{
				System.err.println("No console.");
				System.exit(1);
			}

			BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(args[0]))
			);
			String reader_line = null;

			while ((reader_line = reader.readLine()) != null)
			{
				try
				{
					string_tree.add(reader_line);
				}
				catch (Exception e)
				{
					System.err.println(e.getMessage());
				}
			}

			reader.close();
		}
		catch (IOException e)
		{
		}

		System.out.println("In sorted order:");

		Iterator<RedBlack<String>> itr = string_tree.iterator();

		while (itr.hasNext())
		{
			System.out.println(itr.next().getData());
		}
	}
}
