package edu.lewisu.cpsc50100;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/*
* @brief Answer to SU18-CPSC-50100 Week 05 DB Practical Question 1.
* 
* Variant of "Writing Pixels to Images" example featured in
* <http://docs.oracle.com/javase/8/javafx/graphics-tutorial/image_ops.htm>,
* modified to restrict pixels by reducing the opacity of those that are excessively blue.
* 
* @author Cromwell D. Enage
*/
public class PracticalAnswer extends javafx.application.Application {

	private static boolean _pixelPredicate(Color color)
	{
		return 0.5 < color.getBlue();
	}

	private static Color _transform(Color color)
	{
		return new Color(color.getRed(), color.getGreen(), color.getBlue(), 0.125);
	}

	@Override
	public void start(javafx.stage.Stage primary_stage) throws Exception
	{
		Image in_image = new Image(this.getParameters().getRaw().iterator().next());
		ImageView image_view = new ImageView();

		image_view.setImage(in_image);

		PixelReader pixel_reader = in_image.getPixelReader();
		WritableImage out_image = new WritableImage(
			(int) in_image.getWidth(),
			(int) in_image.getHeight()
		);
		PixelWriter pixel_writer = out_image.getPixelWriter();

		for (int y = 0; y < in_image.getHeight(); ++y)
		{
			for (int x = 0; x < in_image.getWidth(); ++x)
			{
				Color color = pixel_reader.getColor(x, y);

				if (PracticalAnswer._pixelPredicate(color))
				{
					color = PracticalAnswer._transform(color);
				}

				pixel_writer.setColor(x, y, color);
			}
		}

		image_view.setImage(out_image);

		StackPane stack_pane = new StackPane();

		stack_pane.getChildren().add(image_view);

		Scene scene = new Scene(stack_pane, 800, 600);

		primary_stage.setTitle("Pixel Restriction Test");
		primary_stage.setScene(scene);
		primary_stage.show();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
