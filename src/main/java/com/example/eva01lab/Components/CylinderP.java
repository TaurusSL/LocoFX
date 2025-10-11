package com.example.eva01lab.Components;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Rotate;

public class CylinderP {
    private final Cylinder cylinder;

    public CylinderP(double radius, double height, Color color, Boolean isWireframe) {
        this.cylinder = new Cylinder(radius, height);
        PhongMaterial material = new PhongMaterial(color);
        this.cylinder.setMaterial(material);

        if (isWireframe) {
            this.cylinder.setDrawMode(DrawMode.LINE);
        } else {
            this.cylinder.setDrawMode(DrawMode.FILL);
        }
    }

    public CylinderP setPosition(double x, double y, double z) {
        cylinder.setTranslateX(x);
        cylinder.setTranslateY(y);
        cylinder.setTranslateZ(z);
        return this;
    }

    public CylinderP setRotate(double xAngle, double yAngle, double zAngle) {
        cylinder.getTransforms().addAll(
                new Rotate(xAngle, Rotate.X_AXIS),
                new Rotate(yAngle, Rotate.Y_AXIS),
                new Rotate(zAngle, Rotate.Z_AXIS)
        );
        return this;
    }

    public Cylinder getCylinder() {
        return this.cylinder;
    }
}