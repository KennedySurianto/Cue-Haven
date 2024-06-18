package main.controller;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import main.Main;
import main.model.Transaction;

public class MainController {

    public static StackPane createBox(String labelText) {
        Rectangle rectangle = new Rectangle(300, 150);
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

        stackPane.setOnMouseClicked(event -> {
            if (rectangle.getFill() == Color.GREEN) {
                rectangle.setFill(Color.RED);
                
                showConfirmation("Open " + labelText + "?", labelText.replace("Table ", ""));
                showNotification(labelText + " Opened!");
            } else {
                rectangle.setFill(Color.GREEN);
                
                
                showNotification(labelText + " closed!");
            }
        });

        return stackPane;
    }
    
    private static void showConfirmation(String message, String tableNumber) {
        Label confirmationLabel = new Label(message);
        confirmationLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");

        Rectangle overlay = generateOverlay();

        Rectangle confirmationBox = new Rectangle(300, 150);
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

        StackPane boxPane = new StackPane();
        boxPane.getChildren().addAll(confirmationBox, vbox);
        StackPane.setAlignment(boxPane, Pos.CENTER);

        StackPane confirmationPane = new StackPane();
        confirmationPane.getChildren().addAll(overlay, boxPane);
        StackPane.setAlignment(confirmationPane, Pos.CENTER);

        Main.getRoot().getChildren().add(confirmationPane);

        okButton.setOnAction(event -> {
            Transaction transaction = new Transaction();
            transaction.startTransaction("Kennedy", tableNumber);
        	
            Main.getRoot().getChildren().remove(confirmationPane);
        });

        cancelButton.setOnAction(event -> {
            // Perform action for Cancel button (e.g., close dialog)
            System.out.println("Cancel button clicked");
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

        // Auto-hide after 3 seconds
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> Main.getRoot().getChildren().remove(notificationPane));
        pause.play();
    }
    
}
