package edu.lewisu.cpsc50100;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/*
* @author Cromwell D. Enage
*/
public class Assignment07 extends javafx.application.Application {
	private Queue<Character> _alphabet_q;
	private int _first_number;
	private int _second_number;
	private Label _count_label;
	private Label _front_label;
	private Label _gcd_label;
	private Text _exception_text;
	private TextField _queue_text_field;
	private TextField _first_text_field;
	private TextField _second_text_field;

	/*
	* @brief Default constructor.
	*/
	public Assignment07()
	{
		this._alphabet_q = new LLQueue<Character>();
		this._first_number = this._second_number = 0;
		this._count_label = this._front_label = this._gcd_label = null;
		this._exception_text = null;
		this._queue_text_field = this._first_text_field = this._second_text_field = null;
	}

	private class QueueTextListener implements EventHandler<ActionEvent> {
		private Assignment07 _a;

		public QueueTextListener(Assignment07 a)
		{
			this._a = a;
		}

		@Override
		public void handle(ActionEvent event)
		{
			if (this._a._queue_text_field.getText().length() == 1)
			{
				char charbinx = this._a._queue_text_field.getText().charAt(0);

				if (charbinx == '-')
				{
					try
					{
						this._a._alphabet_q.dequeue();
						this._a._count_label.setText(
							Integer.toString(this._a._alphabet_q.getElementCount())
						);

						if (0 == this._a._alphabet_q.getElementCount())
						{
							this._a._front_label.setText("(empty)");
						}
						else
						{
							this._a._front_label.setText(
								this._a._alphabet_q.getFront().toString()
							);
						}

						this._a._exception_text.setText("");
					}
					catch (UnsupportedOperationException exception)
					{
						this._a._exception_text.setText(exception.getMessage());
					}
				}
				else if (
					(('a' <= charbinx) && (charbinx <= 'z')) ||
					(('A' <= charbinx) && (charbinx <= 'Z'))
				)
				{
					try
					{
						this._a._alphabet_q.enqueue(
							Character.valueOf(Character.toLowerCase(charbinx))
						);
						this._a._count_label.setText(
							Integer.toString(this._a._alphabet_q.getElementCount())
						);
						this._a._front_label.setText(
							this._a._alphabet_q.getFront().toString()
						);
						this._a._exception_text.setText("");
					}
					catch (UnsupportedOperationException exception)
					{
						this._a._exception_text.setText(exception.getMessage());
					}
				}
			}

			this._a._queue_text_field.setText("");
		}
	}

	private class GCDTextListener implements EventHandler<ActionEvent> {
		private Assignment07 _a;

		public GCDTextListener(Assignment07 a)
		{
			this._a = a;
		}

		@Override
		public void handle(ActionEvent event)
		{
			boolean has_integer_inputs = false;

			if (!this._a._first_text_field.getText().isEmpty())
			{
				try
				{
					int a = Integer.parseInt(this._a._first_text_field.getText());

					this._a._first_number = a;
					has_integer_inputs = true;
				}
				catch (NumberFormatException exception)
				{
					this._a._first_text_field.setText(
						Integer.toString(this._a._first_number)
					);
				}
			}

			if (!this._a._second_text_field.getText().isEmpty())
			{
				try
				{
					int b = Integer.parseInt(this._a._second_text_field.getText());

					this._a._second_number = b;
					has_integer_inputs = true;
				}
				catch (NumberFormatException exception)
				{
					this._a._second_text_field.setText(
						Integer.toString(this._a._second_number)
					);
				}
			}

			if (has_integer_inputs)
			{
				try
				{
					this._a._gcd_label.setText(
						Integer.toString(
							PositiveMath.computeGreatestCommonDivisor(
								this._a._first_number,
								this._a._second_number
							)
						)
					);
					this._a._exception_text.setText("");
				}
				catch (IllegalArgumentException exception)
				{
					this._a._exception_text.setText(exception.getMessage());
				}
				catch (RangeException exception)
				{
					this._a._gcd_label.setText(exception.getMessage());
					this._a._exception_text.setText("");
				}
			}
		}
	}

	@Override
	public void start(javafx.stage.Stage primary_stage) throws Exception
	{
		GridPane grid_pane = new GridPane();

		grid_pane.setHgap(10);
		grid_pane.setVgap(10);
		grid_pane.setPadding(new javafx.geometry.Insets(25, 25, 25, 25));
		grid_pane.add(new Label("First number:"), 0, 0);
		this._first_text_field = new TextField("0");
		this._first_text_field.setOnAction(new GCDTextListener(this));
		grid_pane.add(this._first_text_field, 1, 0);
		grid_pane.add(new Label("Second number:"), 0, 1);
		this._second_text_field = new TextField("0");
		this._second_text_field.setOnAction(new GCDTextListener(this));
		grid_pane.add(this._second_text_field, 1, 1);
		grid_pane.add(new Label("Greatest common divisor:"), 0, 2);
		this._gcd_label = new Label("0");
		grid_pane.add(this._gcd_label, 1, 2);
		grid_pane.add(new Label("Letter to enqueue ('-' to dequeue):"), 0, 3);
		this._queue_text_field = new TextField("");
		this._queue_text_field.setOnAction(new QueueTextListener(this));
		grid_pane.add(this._queue_text_field, 1, 3);
		grid_pane.add(new Label("No. of enqueued chars:"), 0, 4);
		this._count_label = new Label("0");
		grid_pane.add(this._count_label, 1, 4);
		grid_pane.add(new Label("Front of queue:"), 0, 5);
		this._front_label = new Label("(empty)");
		grid_pane.add(this._front_label, 1, 5);
		this._exception_text = new Text();
		grid_pane.add(this._exception_text, 0, 6, 1, 2);
		grid_pane.add(
			new Label("Press ENTER after typing an input\nto change the associated output."),
			1, 6, 1, 2
		);

		Scene scene = new Scene(grid_pane, 550, 325);

		primary_stage.setTitle("Assignment 07 - GCD with Alphabet Queue");
		primary_stage.setScene(scene);
		primary_stage.show();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
