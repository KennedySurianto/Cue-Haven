package main.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import main.controller.MainController;

public class MainView {
    private BorderPane view;
    private GridPane gridPane;
    private StackPane headingPane;
    private Label headingLabel;

    public MainView() {
        view = new BorderPane();

        // Setup GridPane
        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 4; col++) {
                StackPane box = MainController.createBox("Table " + (row * 4 + col + 1));
                GridPane.setConstraints(box, col, row);
                gridPane.getChildren().add(box);
            }
        }

        // Setup Heading
        headingLabel = new Label("Cue Haven");
        headingLabel.setStyle("-fx-font-size: 64px; -fx-font-weight: bolder;");
        Region marginRegion = new Region();
        marginRegion.setPrefHeight(150);
        headingPane = new StackPane();
        headingPane.getChildren().addAll(marginRegion, headingLabel);
        StackPane.setAlignment(headingLabel, Pos.BOTTOM_CENTER);

        // Setup BorderPane
        view.setTop(headingPane);
        BorderPane.setAlignment(headingPane, Pos.BOTTOM_CENTER);
        view.setCenter(gridPane);
        
    }

    public BorderPane getView() {
        return view;
    }
}
