package edu.lewisu.cpsc50100;

import java.util.Random;
import java.util.function.Function;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
* @author Cromwell D. Enage
*/
public class Assignment05 extends javafx.application.Application {
	private Group _group;
	private Random _rng;

	/*
	* @brief Default constructor
	*/
	public Assignment05()
	{
		super();
		this._group = null;
		this._rng = new Random();
	}

	private void _drawQRCode(Function<Random,Bitset2D> f, int x, int y, int cell_length)
	{
		this._group.getChildren().clear();

		Bitset2D qr_code = f.apply(this._rng);

		for (int i = 0; i < qr_code.size1(); ++i)
		{
			for (int j = 0; j < qr_code.size2(); ++j)
			{
				Rectangle r = new Rectangle(
					i * cell_length + x,
					j * cell_length + y,
					cell_length,
					cell_length
				);

				if (qr_code.testBit(i, j))
				{
					r.setFill(Color.BLACK);
				}
				else
				{
					r.setFill(Color.WHITE);
				}

				this._group.getChildren().add(r);
			}
		}
    }

	private class MenuListener implements EventHandler<ActionEvent> {
		private Assignment05 _a;
		private Function<Random,Bitset2D> _f;
		private int _cell_length;

		public MenuListener(Assignment05 a, Function<Random,Bitset2D> f, int cell_length)
		{
			this._a = a;
			this._f = f;
			this._cell_length = cell_length;
		}

		@Override
		public void handle(ActionEvent e)
		{
			this._a._drawQRCode(this._f, 12, 36, this._cell_length);
		}
	}

	@Override
	public void start(javafx.stage.Stage primaryStage) throws Exception
	{
		this._group = new Group();

		Group root = new Group();
		Scene scene = new Scene(root, 600, 624, Color.BLUE);
		MenuBar menu_bar = new MenuBar();
		Menu menu = new Menu("Generate...");
		MenuItem menu_item = new MenuItem("...QR Code version 1");
		MenuListener listener = new MenuListener(this, new QuickResponseCodeGenerator_V01(), 5);

		menu_item.setOnAction(listener);
		menu.getItems().add(menu_item);
		menu_item = new MenuItem("...QR Code version 2");
		listener = new MenuListener(this, new QuickResponseCodeGenerator_V02(), 5);
		menu_item.setOnAction(listener);
		menu.getItems().add(menu_item);
		menu_item = new MenuItem("...QR Code version 3");
		listener = new MenuListener(this, new QuickResponseCodeGenerator_V03(), 5);
		menu_item.setOnAction(listener);
		menu.getItems().add(menu_item);
		menu_item = new MenuItem("...QR Code version 4");
		listener = new MenuListener(this, new QuickResponseCodeGenerator_V04(), 5);
		menu_item.setOnAction(listener);
		menu.getItems().add(menu_item);
		menu_item = new MenuItem("...QR Code version 10");
		listener = new MenuListener(this, new QuickResponseCodeGenerator_V10(), 3);
		menu_item.setOnAction(listener);
		menu.getItems().add(menu_item);
		menu_item = new MenuItem("...QR Code version 25");
		listener = new MenuListener(this, new QuickResponseCodeGenerator_V25(), 3);
		menu_item.setOnAction(listener);
		menu.getItems().add(menu_item);
		menu_bar.getMenus().add(menu);
		root.getChildren().add(menu_bar);
		root.getChildren().add(this._group);
		this._drawQRCode(new QuickResponseCodeGenerator_V01(), 12, 36, 5);
		primaryStage.setTitle("Assignment 05 - Random QR Code Generator");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
