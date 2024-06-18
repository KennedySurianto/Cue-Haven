package main.controller;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import main.Main;

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
                showNotification(labelText + " opened!");
            } else {
                rectangle.setFill(Color.GREEN);
                showNotification(labelText + " closed!");
            }
        });

        return stackPane;
    }

    private static void showNotification(String message) {
        Label notificationLabel = new Label(message);
        notificationLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");

        Rectangle overlay = new Rectangle(Main.getRoot().getWidth(), Main.getRoot().getHeight());
        overlay.setFill(Color.rgb(0, 0, 0, 0.5));
        StackPane.setMargin(overlay, new Insets(0, 0, 0, 0));

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
