/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dices;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author dell-xps
 */
public class ScreensFramework extends Application {

    public static String screen1ID = "main";
    public static String screen1File = "StartScreen.fxml";
    public static String screen2ID = "screen2";
    public static String screen2File = "PlayersScreen.fxml";
    public static String screen3ID = "screen3";
    public static String screen3File = "GamePlayScreen.fxml";
    public static String screen4ID = "screen4";
    public static String screen4File = "StatisticScreen.fxml";

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
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
