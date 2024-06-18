package main;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	StackPane root;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Cue Haven");

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
		Label headingLabel = new Label("Cue Haven");
		headingLabel.setStyle("-fx-font-size: 32px; -fx-font-weight: bolder;");

		Region marginRegion = new Region();
		marginRegion.setPrefHeight(100);

		StackPane headingPane = new StackPane();
		headingPane.getChildren().addAll(marginRegion, headingLabel);

		Label txLabel = new Label("Transactions");
		txLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(headingPane);
		BorderPane.setAlignment(headingPane, Pos.CENTER);
		borderPane.setCenter(gridPane);
		borderPane.setBottom(txLabel);
		BorderPane.setAlignment(txLabel, Pos.CENTER);

//		BORDERS FOR TESTING:
//		headingPane.setStyle("-fx-border-color: black; -fx-border-width: 0 0 1 0;"); // Bottom border only
//		gridPane.setStyle("-fx-border-color: black; -fx-border-width: 0 1 1 0;"); // Right and bottom borders
//		txLabel.setStyle("-fx-border-color: black; -fx-border-width: 1 0 0 0;"); // Top border only
//		borderPane.setStyle("-fx-border-color: black; -fx-border-width: 1;"); // Full border around the entire BorderPane
		Image icon = new Image("8ball.png");
		stage.getIcons().add(icon);

		System.out.println(getClass());
		root = new StackPane();
		root.getChildren().add(borderPane);

		Scene scene = new Scene(root, 1000, 500);
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

		stackPane.setOnMouseEntered(event -> {
			stackPane.setStyle("-fx-cursor: hand;");
		});

		stackPane.setOnMouseExited(event -> {
			stackPane.setStyle("-fx-cursor: default;");
		});

		stackPane.setOnMouseClicked(event -> {
			if (rectangle.getFill() == Color.GREEN) {
				rectangle.setFill(Color.RED);
				showNotification("Table " + labelText + " opened!");
			} else {
				rectangle.setFill(Color.GREEN);
				showNotification("Table " + labelText + " closed!");
			}
		});

		return stackPane;
	}

	private void showNotification(String message) {
		Label notificationLabel = new Label(message);
		notificationLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");

		Rectangle overlay = new Rectangle(root.getWidth(), root.getHeight());
		overlay.setFill(Color.rgb(0, 0, 0, 0.5));
		StackPane.setMargin(overlay, new Insets(0, 0, 0, 0));

		Rectangle notificationBox = new Rectangle(200, 50);
		notificationBox.setFill(Color.GREY);
		notificationBox.setArcHeight(20);
		notificationBox.setArcWidth(20);

		StackPane notificationPane = new StackPane();
		notificationPane.getChildren().addAll(overlay, notificationBox, notificationLabel);

		root.getChildren().add(notificationPane);

		StackPane.setAlignment(notificationPane, Pos.CENTER);

		// Auto-hide after 3 seconds
		PauseTransition pause = new PauseTransition(Duration.seconds(2));
		pause.setOnFinished(event -> {
			root.getChildren().remove(notificationPane);
		});
		pause.play();
	}

}
