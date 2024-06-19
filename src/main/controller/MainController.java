package main.controller;

import java.time.LocalTime;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import main.Main;
import main.model.Transaction;
import main.model.TransactionWrapper;

public class MainController {

	public static StackPane createBox(String labelText) {
		Rectangle rectangle = new Rectangle(180, 300);
		rectangle.setFill(Color.GREEN);
		rectangle.setArcWidth(20);
		rectangle.setArcHeight(20);

		Label label = new Label(labelText);
		label.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");

		StackPane stackPane = new StackPane();
		stackPane.getChildren().addAll(rectangle, label);

		StackPane.setAlignment(label, Pos.CENTER);

		stackPane.setOnMouseEntered(event -> stackPane.setStyle("-fx-cursor: hand;"));
		stackPane.setOnMouseExited(event -> stackPane.setStyle("-fx-cursor: default;"));

		TransactionWrapper transactionWrapper = new TransactionWrapper();
		stackPane.setOnMouseClicked(event -> {
			if (rectangle.getFill() == Color.GREEN) {
				showStartTransactionConfirmation(labelText, rectangle, transactionWrapper);
			} else {
				showEndTransactionConfirmation(labelText, rectangle, transactionWrapper);
			}
		});

		return stackPane;
	}

	private static void showEndTransactionConfirmation(String labelText, Rectangle rectangle,
			TransactionWrapper transactionWrapper) {
		String message = "Close " + labelText + "?";
		Label confirmationLabel = new Label(message);
		confirmationLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");

		Rectangle overlay = generateOverlay();

		Rectangle confirmationBox = new Rectangle(200, 110);
		confirmationBox.setFill(Color.GREY);
		confirmationBox.setArcHeight(20);
		confirmationBox.setArcWidth(20);

		Button okButton = new Button("OK");
		Button cancelButton = new Button("Cancel");
		
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(10));
		hbox.getChildren().addAll(cancelButton, okButton);

		VBox vbox = new VBox(10);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(20));
		vbox.getChildren().addAll(confirmationLabel, hbox);

		StackPane stackPane = new StackPane();
		stackPane.getChildren().addAll(confirmationBox, vbox);
		StackPane.setAlignment(stackPane, Pos.CENTER);

		StackPane mainStackPane = new StackPane();
		mainStackPane.getChildren().addAll(overlay, stackPane);
		StackPane.setAlignment(mainStackPane, Pos.CENTER);

		Main.getRoot().getChildren().add(mainStackPane);
		
		okButton.setOnAction(event -> {
			transactionWrapper.transaction.endTransaction(LocalTime.now());

			TransactionController.addTransaction(transactionWrapper.transaction);
			transactionWrapper.transaction = null;
			
			rectangle.setFill(Color.GREEN);
			Main.getRoot().getChildren().remove(mainStackPane);
			
			showNotification(labelText + " closed!");
		});
		
		cancelButton.setOnAction(event -> {
			Main.getRoot().getChildren().remove(mainStackPane);
		});
	}

	private static void showStartTransactionConfirmation(String labelText, Rectangle rectangle,
			TransactionWrapper transactionWrapper) {
		String message = "Open " + labelText + "?";
		Label confirmationLabel = new Label(message);
		confirmationLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");

		Rectangle overlay = generateOverlay();

		Rectangle confirmationBox = new Rectangle(300, 170);
		confirmationBox.setFill(Color.GREY);
		confirmationBox.setArcHeight(20);
		confirmationBox.setArcWidth(20);

		Button okButton = new Button("OK");
		Button cancelButton = new Button("Cancel");

		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(10));
		hbox.getChildren().addAll(cancelButton, okButton);

		Label nameLabel = new Label("Customer Name: ");
		nameLabel.setStyle("-fx-text-fill: white;");
		TextField nameTF = new TextField();
		nameTF.setMaxWidth(200);

		VBox vbox = new VBox(10);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(20));
		vbox.getChildren().addAll(confirmationLabel, nameLabel, nameTF, hbox);

		StackPane boxPane = new StackPane();
		boxPane.getChildren().addAll(confirmationBox, vbox);
		StackPane.setAlignment(boxPane, Pos.CENTER);

		StackPane confirmationPane = new StackPane();
		confirmationPane.getChildren().addAll(overlay, boxPane);
		StackPane.setAlignment(confirmationPane, Pos.CENTER);

		Main.getRoot().getChildren().add(confirmationPane);

		String tableNumber = labelText.replace("Table ", "");
		okButton.setOnAction(event -> {
			if (nameTF.getText().length() < 3)
				return;

			transactionWrapper.transaction = new Transaction();
			transactionWrapper.transaction.startTransaction(nameTF.getText(), tableNumber);

			Main.getRoot().getChildren().remove(confirmationPane);
			showNotification(labelText + " Opened!");
			rectangle.setFill(Color.RED);
		});

		cancelButton.setOnAction(event -> {
			Main.getRoot().getChildren().remove(confirmationPane);
		});

	}

	private static Rectangle generateOverlay() {
		Rectangle overlay = new Rectangle(Main.getRoot().getWidth(), Main.getRoot().getHeight());
		overlay.setFill(Color.rgb(0, 0, 0, 0.5));
		StackPane.setMargin(overlay, new Insets(0, 0, 0, 0));

		return overlay;
	}

	private static void showNotification(String message) {
		Label notificationLabel = new Label(message);
		notificationLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");

		Rectangle overlay = generateOverlay();

		Rectangle notificationBox = new Rectangle(200, 50);
		notificationBox.setFill(Color.GREY);
		notificationBox.setArcHeight(20);
		notificationBox.setArcWidth(20);

		StackPane notificationPane = new StackPane();
		notificationPane.getChildren().addAll(overlay, notificationBox, notificationLabel);

		Main.getRoot().getChildren().add(notificationPane);

		StackPane.setAlignment(notificationPane, Pos.CENTER);

		// Auto-hide after 2 seconds
		PauseTransition pause = new PauseTransition(Duration.seconds(2));
		pause.setOnFinished(event -> Main.getRoot().getChildren().remove(notificationPane));
		pause.play();
	}

}
