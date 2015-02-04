package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by radko on 30.1.2015 г..
 */
public class ScreensFramework extends Application {

    public static String screen1ID = "main";
    public static String screen1File = "Screen1.fxml";
    public static String screen2ID = "screen2";
    public static String screen2File = "Screen2.fxml";
    public static String screen3ID = "screen3";
    public static String screen3File = "Screen3.fxml";

    @Override
    public void start(Stage primaryStage) {

        ScreensController mainController = new ScreensController();
        mainController.loadScreen(ScreensFramework.screen1ID, ScreensFramework.screen1File);
        mainController.loadScreen(ScreensFramework.screen2ID, ScreensFramework.screen2File);
        mainController.loadScreen(ScreensFramework.screen3ID, ScreensFramework.screen3File);

        mainController.setScreen(ScreensFramework.screen1ID);

        Group root = new Group();
        root.getChildren().addAll(mainController);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
