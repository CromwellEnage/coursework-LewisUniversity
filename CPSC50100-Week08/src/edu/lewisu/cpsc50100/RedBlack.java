package edu.lewisu.cpsc50100;

/*
* @brief Data holder with a red-or-black flag.
* @author Cromwell D. Enage
*/
public class RedBlack<T> {
	private T _data;
	private boolean _is_red_not_black;

	public RedBlack(T data, boolean is_red_not_black)
	{
		this._data = data;
		this._is_red_not_black = is_red_not_black;
	}

	public T getData()
	{
		return this._data;
	}

	public boolean isRed()
	{
		return this._is_red_not_black;
	}

	public boolean isBlack()
	{
		return !this._is_red_not_black;
	}

	public void setData(T data)
	{
		this._data = data;
	}

	public void setRed()
	{
		this._is_red_not_black = true;
	}

	public void setBlack()
	{
		this._is_red_not_black = false;
	}

	public void setToColorOf(RedBlack<T> t)
	{
		this._is_red_not_black = t._is_red_not_black;
	}
}
