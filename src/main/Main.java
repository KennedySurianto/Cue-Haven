package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.view.MainView;
import main.view.TransactionView;

public class Main extends Application {
	private Stage primaryStage;
	private static StackPane root;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		root = new StackPane();

		primaryStage.setTitle("Cue Haven");

		Image icon = new Image("/resources/8ball.png");
		primaryStage.getIcons().add(icon);

		showMainMenu();
	}

	public void showMainMenu() {
		resetRoot();

		MainView mainView = new MainView();
		root.getChildren().add(mainView.getView());

		primaryStage.setScene(createScene());
		primaryStage.show();
	}

	public void showTransactionMenu() {
		resetRoot();
		
		TransactionView transactionView = new TransactionView();
		root.getChildren().add(transactionView.getView());

		primaryStage.setScene(createScene());
		primaryStage.show();
	}
	
	private Scene createScene() {
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(createMenuBar());
		borderPane.setCenter(root);
		
		return new Scene(borderPane, 1400, 700);
	}
	
	private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu mainMenu = new Menu("Main");
        mainMenu.setStyle("-fx-font-size: 16px;");
        MenuItem mainItem = new MenuItem("Go to Main Menu");
        mainItem.setOnAction(event -> showMainMenu());
        mainMenu.getItems().add(mainItem);

        Menu transactionMenu = new Menu("Transaction");
        transactionMenu.setStyle("-fx-font-size: 16px;");
        MenuItem transactionItem = new MenuItem("Go to Transaction Menu");
        transactionItem.setOnAction(event -> showTransactionMenu());
        transactionMenu.getItems().add(transactionItem);

        menuBar.getMenus().addAll(mainMenu, transactionMenu);

        return menuBar;
    }

	private void resetRoot() {
		root.getChildren().clear();
	}

	public static StackPane getRoot() {
		return root;
	}
}
