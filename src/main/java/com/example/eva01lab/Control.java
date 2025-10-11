package com.example.eva01lab;

import com.example.eva01lab.Components.Eva01;
import com.example.eva01lab.GroupExtended;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Camera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Control {

    private final Scene scene;
    private final Stage stage;
    private final Camera camera;
    private final GroupExtended group;
    private final Eva01 model;

    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);

    private int renderMode = 0;

    public Control(Scene scene, Stage stage, Camera camera, GroupExtended group, Eva01 model) {
        this.scene = scene;
        this.stage = stage;
        this.camera = camera;
        this.group = group;
        this.model = model;
    }

    public void eventHandler() {
        mouseEventHandler();
        keyboardEventHandler();
        scrollZoomHandler();
    }

    private void mouseEventHandler() {
        Rotate xRotate = new Rotate(0, Rotate.X_AXIS);
        Rotate yRotate = new Rotate(0, Rotate.Y_AXIS);
        group.getTransforms().addAll(xRotate, yRotate);

        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = angleX.get();
            anchorAngleY = angleY.get();
        });

        scene.setOnMouseDragged(event -> {
            angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
            angleY.set(anchorAngleY + (anchorX - event.getSceneX()));
        });
    }

    private void scrollZoomHandler() {
        stage.addEventHandler(ScrollEvent.SCROLL, event -> {
            double delta = event.getDeltaY();
            camera.translateZProperty().set(camera.getTranslateZ() + delta * 5);
        });
    }

    private void keyboardEventHandler() {
        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case W: camera.translateYProperty().set(camera.getTranslateY() - 20); break;
                case S: camera.translateYProperty().set(camera.getTranslateY() + 20); break;
                case A: camera.translateXProperty().set(camera.getTranslateX() - 20); break;
                case D: camera.translateXProperty().set(camera.getTranslateX() + 20); break;
                case UP: group.rotateByX(-10); break;
                case DOWN: group.rotateByX(10); break;
                case LEFT: group.rotateByY(-10); break;
                case RIGHT: group.rotateByY(10); break;
                case M:
                    renderMode = (renderMode + 1) % 3;
                    model.redraw(renderMode);
                    break;
            }
        });
    }
}