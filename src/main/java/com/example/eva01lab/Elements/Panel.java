package com.example.eva01lab.Elements;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Rotate;

public class Panel {
    private final Box box;

    public Panel(double width, double height, double depth, Color color, boolean isWireframe) {
        this.box = new Box(width, height, depth);
        PhongMaterial material = new PhongMaterial(color);
        this.box.setMaterial(material);

        if (isWireframe) {
            this.box.setDrawMode(DrawMode.LINE);
        } else {
            this.box.setDrawMode(DrawMode.FILL);
        }
    }

    public Panel setPosition(double x, double y, double z) {
        box.setTranslateX(x);
        box.setTranslateY(y);
        box.setTranslateZ(z);
        return this;
    }

    public Panel setRotate(double xAngle, double yAngle, double zAngle) {
        box.getTransforms().addAll(
                new Rotate(xAngle, Rotate.X_AXIS),
                new Rotate(yAngle, Rotate.Y_AXIS),
                new Rotate(zAngle, Rotate.Z_AXIS)
        );
        return this;
    }

    public Box getPanel() {
        return this.box;
    }
}