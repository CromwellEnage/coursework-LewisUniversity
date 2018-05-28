package edu.lewisu.cpsc50100;

import java.util.ArrayList;

/*
* @author Cromwell D. Enage
*/
public class Order {
	private class Item {
		String _name;
		int _quantity;
		double _unit_price;

		public Item(String name, int quantity, double unit_price)
		{
			this._name = name;
			this._quantity = quantity;
			this._unit_price = unit_price;
		}

		public String getName()
		{
			return this._name;
		}

		public int getQuantity()
		{
			return this._quantity;
		}

		public double getUnitPrice()
		{
			return this._unit_price;
		}
	}

	boolean _is_delivery;
	boolean _is_delivery_with_extra_cost;
	int _zip_code;
	double _tax_rate;
	ArrayList<Item> _items;

	public Order(double tax_rate, int destination_zip_code, int store_zip_code)
	{
		this._is_delivery = true;
		this._is_delivery_with_extra_cost = (
				(destination_zip_code > store_zip_code - 5) &&
				(destination_zip_code < store_zip_code + 5)
				);
		this._zip_code = destination_zip_code;
		this._tax_rate = tax_rate;
		this._items = new ArrayList<Item>();
	}

	public Order(double tax_rate)
	{
		this._is_delivery = this._is_delivery_with_extra_cost = false;
		this._zip_code = 0;
		this._tax_rate = tax_rate;
		this._items = new ArrayList<Item>();
	}

	public void addItem(String name, int quantity, double unit_price)
	{
		this._items.add(new Item(name, quantity, unit_price));
	}

	public int getItemCount()
	{
		return this._items.size();
	}

	public String getItemName(int i)
	{
		return this._items.get(i).getName();
	}

	public int getQuantity(int i)
	{
		return this._items.get(i).getQuantity();
	}

	public double getUnitPrice(int i)
	{
		return this._items.get(i).getUnitPrice();
	}

	public boolean isDeliveryWithExtraCost()
	{
		return this._is_delivery_with_extra_cost;
	}

	public double getTaxPercentage()
	{
		return this._tax_rate * 100;
	}

	public double computeTotalPrice()
	{
		double price = 0.0;

		for (int i = 0; i < this.getItemCount(); ++i)
		{
			price += this.getUnitPrice(i) * this.getQuantity(i);
		}

		price += price * this._tax_rate;

		if (this._is_delivery)
		{
			if (this.isDeliveryWithExtraCost())
			{
				price += 5.0;
			}
			else
			{
				price += 7.0;
			}
		}

		return price;
	}
}
