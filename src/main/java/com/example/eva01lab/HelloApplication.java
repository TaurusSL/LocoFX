package com.example.eva01lab;

import com.example.eva01lab.Components.Eva01;
import com.example.eva01lab.Control;
import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static final int WIDTH = 1400;
    private static final int HEIGHT = 800;

    @Override
    public void start(Stage stage) throws IOException {
        GroupExtended mainGroup = new GroupExtended();

        Eva01 model = new Eva01(0, 0, 0, mainGroup, 0);
        model.init();

        Camera camera = new PerspectiveCamera(true);
        camera.setNearClip(1);
        camera.setFarClip(10000);
        camera.translateZProperty().set(-2500);

        Scene scene = new Scene(mainGroup, WIDTH, HEIGHT, true);
        scene.setFill(Color.LIGHTPINK);
        scene.setCamera(camera);

        mainGroup.rotateByX(-20);
        mainGroup.rotateByY(-45);

        Control control = new Control(scene, stage, camera, mainGroup, model);
        control.eventHandler();

        stage.setTitle("Потяг v1.0.0");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}