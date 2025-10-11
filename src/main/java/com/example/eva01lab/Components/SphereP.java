package com.example.eva01lab.Components;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

public class SphereP {
    private final Sphere sphere;

    public SphereP(double radius, Color color, boolean isWireframe) {
        this.sphere = new Sphere(radius);
        PhongMaterial material = new PhongMaterial(color);
        this.sphere.setMaterial(material);

        if (isWireframe) {
            this.sphere.setDrawMode(DrawMode.LINE);
        } else {
            this.sphere.setDrawMode(DrawMode.FILL);
        }
    }

    public SphereP setPosition(double x, double y, double z) {
        sphere.setTranslateX(x);
        sphere.setTranslateY(y);
        sphere.setTranslateZ(z);
        return this;
    }

    public SphereP setRotate(double xAngle, double yAngle, double zAngle) {
        sphere.getTransforms().addAll(
                new Rotate(xAngle, Rotate.X_AXIS),
                new Rotate(yAngle, Rotate.Y_AXIS),
                new Rotate(zAngle, Rotate.Z_AXIS)
        );
        return this;
    }

    public Sphere getSphere() {
        return this.sphere;
    }
}