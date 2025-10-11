package com.example.eva01lab;

import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

public class GroupExtended extends Group {
    private Rotate rotate;
    private Transform transform = new Rotate();

    public GroupExtended() {}

    void rotateByX(int angle) {
        rotate = new Rotate(angle, Rotate.X_AXIS);
        transform = transform.createConcatenation(rotate);
        this.getTransforms().clear();
        this.getTransforms().add(transform);
    }

    void rotateByY(int angle) {
        rotate = new Rotate(angle, Rotate.Y_AXIS);
        transform = transform.createConcatenation(rotate);
        this.getTransforms().clear();
        this.getTransforms().add(transform);
    }
}