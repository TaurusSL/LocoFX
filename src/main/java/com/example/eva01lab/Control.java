package com.example.eva01lab;

import com.example.eva01lab.Components.Eva01;
import com.example.eva01lab.GroupExtended;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.util.Map;

public class Control {

    private final Scene scene;
    private final Stage stage;
    private final Camera camera;
    private final GroupExtended mainGroup;
    private final Eva01 model;
    private final Map<String, StackPane> controlKeys;
    private final Group gizmoGroup;

    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);

    private int renderMode = 0;

    private final Color activeColor = Color.ORANGE;
    private final Color inactiveColor = Color.web("#444");

    public Control(Scene scene, Stage stage, Camera camera, GroupExtended group, Eva01 model, Map<String, StackPane> controlKeys, Group gizmoGroup) {
        this.scene = scene;
        this.stage = stage;
        this.camera = camera;
        this.mainGroup = group;
        this.model = model;
        this.controlKeys = controlKeys;
        this.gizmoGroup = gizmoGroup;
    }

    public void eventHandler() {
        setupRotationBindings();

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

        scrollZoomHandler();
        keyboardEventHandler();
    }

    private void setupRotationBindings() {
        Rotate xRotate = new Rotate(0, Rotate.X_AXIS);
        Rotate yRotate = new Rotate(0, Rotate.Y_AXIS);
        mainGroup.getTransforms().addAll(xRotate, yRotate);

        Rotate gizmoXRotate = new Rotate(0, Rotate.X_AXIS);
        Rotate gizmoYRotate = new Rotate(0, Rotate.Y_AXIS);
        gizmoGroup.getTransforms().addAll(gizmoXRotate, gizmoYRotate);

        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);
        gizmoXRotate.angleProperty().bind(angleX);
        gizmoYRotate.angleProperty().bind(angleY);
    }

    private void scrollZoomHandler() {
        stage.addEventHandler(ScrollEvent.SCROLL, event -> {
            double delta = event.getDeltaY();
            camera.translateZProperty().set(camera.getTranslateZ() + delta * 5);
        });
    }

    private void keyboardEventHandler() {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W: camera.translateYProperty().set(camera.getTranslateY() - 20); updateKeyColor("W", true); break;
                case S: camera.translateYProperty().set(camera.getTranslateY() + 20); updateKeyColor("S", true); break;
                case A: camera.translateXProperty().set(camera.getTranslateX() - 20); updateKeyColor("A", true); break;
                case D: camera.translateXProperty().set(camera.getTranslateX() + 20); updateKeyColor("D", true); break;
                case UP: angleX.set(angleX.get() - 10); break;
                case DOWN: angleX.set(angleX.get() + 10); break;
                case LEFT: angleY.set(angleY.get() - 10); break;
                case RIGHT: angleY.set(angleY.get() + 10); break;
                case M:
                    renderMode = (renderMode + 1) % 3;
                    model.redraw(renderMode);
                    updateKeyColor("M", true);
                    break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case W: updateKeyColor("W", false); break;
                case S: updateKeyColor("S", false); break;
                case A: updateKeyColor("A", false); break;
                case D: updateKeyColor("D", false); break;
                case M: updateKeyColor("M", false); break;
            }
        });
    }

    private void updateKeyColor(String key, boolean isActive) {
        if (controlKeys.containsKey(key)) {
            StackPane keyNode = controlKeys.get(key);
            Rectangle rect = (Rectangle) keyNode.getChildren().get(0);
            rect.setFill(isActive ? activeColor : inactiveColor);
        }
    }
}