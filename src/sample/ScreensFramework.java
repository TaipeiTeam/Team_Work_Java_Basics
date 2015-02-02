package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
<<<<<<< HEAD
import javafx.stage.StageStyle;
=======
>>>>>>> origin/master

/**
 * Created by radko on 30.1.2015 г..
 */
public class ScreensFramework extends Application {

    public static String screen1ID = "main";
    public static String screen1File = "Screen1.fxml";
    public static String screen2ID = "screen2";
    public static String screen2File = "Screen2.fxml";
    public static String screen3ID = "screen3";
<<<<<<< HEAD
    public static String screen3File = "Screen3Statistic.fxml";
=======
    public static String screen3File = "Screen3.fxml";
>>>>>>> origin/master
    public static String screen4ID = "throwDices";
    public static String screen4File = "ThrowDices.fxml";

    @Override
    public void start(Stage primaryStage) {

        ScreensController mainController = new ScreensController();
        mainController.loadScreen(ScreensFramework.screen1ID, ScreensFramework.screen1File);
        mainController.loadScreen(ScreensFramework.screen2ID, ScreensFramework.screen2File);
        mainController.loadScreen(ScreensFramework.screen3ID, ScreensFramework.screen3File);
        mainController.loadScreen(ScreensFramework.screen4ID, ScreensFramework.screen4File);

        mainController.setScreen(ScreensFramework.screen1ID);

        Group root = new Group();
        root.getChildren().addAll(mainController);
<<<<<<< HEAD
        Scene scene = new Scene(root,1000,600);
        scene.setFill(null);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
=======
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
>>>>>>> origin/master
        primaryStage.show();
    }
}
