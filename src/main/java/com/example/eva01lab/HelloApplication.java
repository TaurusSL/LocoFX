package com.example.eva01lab;

import com.example.eva01lab.Components.Eva01;
import com.example.eva01lab.Control;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class HelloApplication extends Application {

    private static final int WIDTH = 1400;
    private static final int HEIGHT = 800;

    @Override
    public void start(Stage stage) throws IOException {
        GroupExtended mainGroup = new GroupExtended();
        Eva01 model = new Eva01(0, 0, 0, mainGroup, 0);
        model.init();

        SubScene mainSubScene = new SubScene(mainGroup, WIDTH, HEIGHT, true, SceneAntialiasing.BALANCED);
        mainSubScene.setFill(Color.web("#242424"));

        Camera camera = new PerspectiveCamera(true);
        camera.setNearClip(1);
        camera.setFarClip(10000);
        camera.translateZProperty().set(-2500);
        mainSubScene.setCamera(camera);

        Map<String, StackPane> controlKeys = createControlsDisplay();
        GridPane keysLayout = new GridPane();
        keysLayout.setVgap(5);
        keysLayout.setHgap(5);

        keysLayout.add(controlKeys.get("W"), 1, 0);
        keysLayout.add(controlKeys.get("A"), 0, 1);
        keysLayout.add(controlKeys.get("S"), 1, 1);
        keysLayout.add(controlKeys.get("D"), 2, 1);

        keysLayout.add(controlKeys.get("M"), 10, 1);

        Group gizmoGroup = createAxisGizmo();
        SubScene gizmoSubScene = new SubScene(gizmoGroup, 150, 150, true, SceneAntialiasing.BALANCED);

        PerspectiveCamera gizmoCamera = new PerspectiveCamera(true);
        gizmoCamera.setTranslateZ(-200);
        gizmoCamera.setNearClip(0.1);
        gizmoCamera.setFarClip(1000);
        gizmoSubScene.setCamera(gizmoCamera);

        gizmoSubScene.setFill(Color.TRANSPARENT);

        StackPane root = new StackPane();
        root.getChildren().addAll(mainSubScene, keysLayout, gizmoSubScene);

        StackPane.setAlignment(keysLayout, Pos.BOTTOM_LEFT);
        StackPane.setMargin(keysLayout, new Insets(20));
        StackPane.setAlignment(gizmoSubScene, Pos.TOP_RIGHT);
        StackPane.setMargin(gizmoSubScene, new Insets(20));

        Scene scene = new Scene(root, WIDTH, HEIGHT, true);

        Control control = new Control(scene, stage, camera, mainGroup, model, controlKeys, gizmoGroup);
        control.eventHandler();

        stage.setTitle("Потяг v1.1.0");
        stage.setScene(scene);
        stage.show();
    }

    private Map<String, StackPane> createControlsDisplay() {
        StackPane keyW = createKeyNode("W");
        StackPane keyA = createKeyNode("A");
        StackPane keyS = createKeyNode("S");
        StackPane keyD = createKeyNode("D");
        StackPane keyM = createKeyNode("M");
        return Map.of("W", keyW, "A", keyA, "S", keyS, "D", keyD, "M", keyM);
    }

    private StackPane createKeyNode(String letter) {
        Rectangle rect = new Rectangle(50, 50, Color.web("#444"));
        rect.setArcWidth(10);
        rect.setArcHeight(10);
        rect.setStroke(Color.web("#666"));
        Text text = new Text(letter);
        text.setFont(Font.font("Verdana", 24));
        text.setFill(Color.WHITE);
        return new StackPane(rect, text);
    }

    private Group createAxisGizmo() {
        final int axisLength = 35;
        final int axisWidth = 1;
        final int sphereRadius = 5;

        Group gizmoGroup = new Group();

        Cylinder xAxis = new Cylinder(axisWidth, axisLength);
        xAxis.setMaterial(new PhongMaterial(Color.RED));
        xAxis.setRotationAxis(Rotate.Z_AXIS);
        xAxis.setRotate(-90);
        xAxis.setTranslateX(axisLength / 2.0);

        Sphere xSphere = new Sphere(sphereRadius);
        xSphere.setMaterial(new PhongMaterial(Color.RED));
        xSphere.setTranslateX(axisLength);

        Text xText = new Text("X");
        xText.setFont(Font.font("Arial", 10));
        xText.setFill(Color.WHITE);
        xText.setTranslateX(axisLength + 10);
        xText.setTranslateY(3);

        Cylinder yAxis = new Cylinder(axisWidth, axisLength);
        yAxis.setMaterial(new PhongMaterial(Color.LIME));
        yAxis.setTranslateY(-axisLength / 2.0);

        Sphere ySphere = new Sphere(sphereRadius);
        ySphere.setMaterial(new PhongMaterial(Color.LIME));
        ySphere.setTranslateY(-axisLength);

        Text yText = new Text("Y");
        yText.setFont(Font.font("Arial", 10));
        yText.setFill(Color.WHITE);
        yText.setTranslateY(-axisLength - 10);
        yText.setTranslateX(-3);

        Cylinder zAxis = new Cylinder(axisWidth, axisLength);
        zAxis.setMaterial(new PhongMaterial(Color.BLUE));
        zAxis.setRotationAxis(Rotate.X_AXIS);
        zAxis.setRotate(90);
        zAxis.setTranslateZ(axisLength / 2.0);

        Sphere zSphere = new Sphere(sphereRadius);
        zSphere.setMaterial(new PhongMaterial(Color.BLUE));
        zSphere.setTranslateZ(axisLength);

        Text zText = new Text("Z");
        zText.setFont(Font.font("Arial", 10));
        zText.setFill(Color.WHITE);
        zText.setTranslateZ(axisLength + 10);
        zText.setTranslateY(3);

        Sphere centerSphere = new Sphere(sphereRadius);
        centerSphere.setMaterial(new PhongMaterial(Color.DARKGRAY));

        gizmoGroup.getChildren().addAll(
                xAxis, xSphere, xText,
                yAxis, ySphere, yText,
                zAxis, zSphere, zText,
                centerSphere
        );

        return gizmoGroup;
    }

    public static void main(String[] args) {
        launch(args);
    }
}