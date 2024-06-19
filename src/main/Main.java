package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
    	root.getChildren().clear();
    	
    	MainView mainView = new MainView();
    	root.getChildren().add(mainView.getView());
    	
    	Scene mainScene = new Scene(root, 1400, 700);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
    
    public void showTransactionMenu() {
    	root.getChildren().clear();
    	
    	TransactionView transactionView = new TransactionView();
    	root.getChildren().add(transactionView.getView());
    	
    	Scene transactionScene = new Scene(root);
    	primaryStage.setScene(transactionScene);
    	primaryStage.show();
    }

    public static StackPane getRoot() {
        return root;
    }
}
