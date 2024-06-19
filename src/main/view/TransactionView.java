package main.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class TransactionView {
	private BorderPane view;
	private Label transactionLabel;
	private StackPane headingPane;
	
	public TransactionView() {
		view = new BorderPane();
		
		// Setup Transaction Label
		transactionLabel = new Label("Cue Haven");
		transactionLabel.setStyle("-fx-font-size: 64px; -fx-font-weight: bolder;");
        Region marginRegion = new Region();
        marginRegion.setPrefHeight(150);
        headingPane = new StackPane();
        headingPane.getChildren().addAll(marginRegion, transactionLabel);
        StackPane.setAlignment(transactionLabel, Pos.BOTTOM_CENTER);
		
		// Setup view Positioning
		view.setTop(transactionLabel);
		BorderPane.setAlignment(transactionLabel, Pos.CENTER);
	}
	
	public BorderPane getView() {
		return view;
	}

}
