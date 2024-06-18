import java.util.Iterator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Billiard Software");

		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setPadding(new Insets(10));
		gridPane.setHgap(20);
		gridPane.setVgap(20);

		for (int row = 0; row < 2; row++) {
			for (int col = 0; col < 4; col++) {
				StackPane box = createBox("Table  " + (row * 4 + col + 1));
				GridPane.setConstraints(box, col, row);
				gridPane.getChildren().add(box);
			}
		}

		Scene scene = new Scene(gridPane, 1000, 500);
		stage.setScene(scene);

		stage.show();
	}

	private StackPane createBox(String labelText) {
		Rectangle rectangle = new Rectangle(200, 100);
		rectangle.setFill(Color.GREEN);
		rectangle.setArcWidth(20);
		rectangle.setArcHeight(20);

		Label label = new Label(labelText);
		label.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");

		StackPane stackPane = new StackPane();
		stackPane.getChildren().addAll(rectangle, label);

		StackPane.setAlignment(label, Pos.CENTER);

		stackPane.setOnMouseClicked(event -> {
			if (rectangle.getFill() == Color.GREEN) {
				rectangle.setFill(Color.RED);				
			} else {
				rectangle.setFill(Color.GREEN);
			}
		});

		return stackPane;
	}

}
