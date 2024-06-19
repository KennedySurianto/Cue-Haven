package main.view;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import main.controller.TransactionController;
import main.model.Transaction;

public class TransactionView {
	private BorderPane view;
	private Label transactionLabel;
	private StackPane headingPane;
	private TableView<Transaction> transactionTable;

	@SuppressWarnings("unchecked")
	public TransactionView() {
		view = new BorderPane();

		// Setup Transaction Label
		transactionLabel = new Label("Transactions");
		transactionLabel.setStyle("-fx-font-size: 64px; -fx-font-weight: bolder;");
		Region marginRegion = new Region();
		marginRegion.setPrefHeight(150);
		headingPane = new StackPane();
		headingPane.getChildren().addAll(marginRegion, transactionLabel);
		StackPane.setAlignment(transactionLabel, Pos.BOTTOM_CENTER);

		// Setup TableView
		transactionTable = new TableView<Transaction>();

		TableColumn<Transaction, String> transactionIdColumn = createColumn("ID", "transactionId");
		TableColumn<Transaction, String> transactionDateColumn = createColumn("Date", "transactionDate");
		TableColumn<Transaction, String> customerNameColumn = createColumn("Customer", "customerName");
		TableColumn<Transaction, String> tableNumberColumn = createColumn("Table", "tableNumber");
		TableColumn<Transaction, String> startTimeColumn = createColumn("Start", "startTime");
		TableColumn<Transaction, String> endTimeColumn = createColumn("End", "endTime");
		
		// Set specific column widths
		transactionIdColumn.setPrefWidth(80);
		transactionDateColumn.setPrefWidth(150);
		customerNameColumn.setPrefWidth(200);
		tableNumberColumn.setPrefWidth(100);
		startTimeColumn.setPrefWidth(150);
		endTimeColumn.setPrefWidth(150);

		transactionTable.getColumns().addAll(transactionIdColumn, transactionDateColumn, customerNameColumn,
				tableNumberColumn, startTimeColumn, endTimeColumn);

		// Insert data into Transaction Table
		ArrayList<Transaction> transactions = TransactionController.getTransactions();
		for (Transaction t : transactions) {
			transactionTable.getItems().add(new Transaction(t.getTransactionId(), t.getTransactionDate(),
					t.getCustomerName(), t.getTableNumber(), t.getStartTime(), t.getEndTime()));
		}
		
		// Styling
		transactionTable.setStyle("-fx-background-color: #f0f0f0; -fx-font-size: 16px;");
		transactionIdColumn.setStyle("-fx-alignment: CENTER;");
		customerNameColumn.setStyle("-fx-text-fill: #336699;");

		// Setup view Positioning
		view.setTop(headingPane);
		view.setCenter(transactionTable);
		BorderPane.setAlignment(transactionLabel, Pos.CENTER);
	}

	private TableColumn<Transaction, String> createColumn(String columnName, String propertyName) {
		TableColumn<Transaction, String> column = new TableColumn<Transaction, String>(columnName);
		column.setCellValueFactory(new PropertyValueFactory<Transaction, String>(propertyName));
		column.setMinWidth(view.getWidth() / 6);
		
		return column;
	}

	public BorderPane getView() {
		return view;
	}

}
