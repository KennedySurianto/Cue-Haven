package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.view.MainView;

public class Main extends Application {
    private static StackPane root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Cue Haven");

        // Initialize the root and main view
        root = new StackPane();
        MainView mainView = new MainView();
        root.getChildren().add(mainView.getView());

        Scene scene = new Scene(root, 1400, 700);
        stage.setScene(scene);

        // Set application icon
        Image icon = new Image("/resources/8ball.png");
        stage.getIcons().add(icon);

        stage.show();
    }

    public static StackPane getRoot() {
        return root;
    }
}
