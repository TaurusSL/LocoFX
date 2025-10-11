package com.example.eva01lab.Components;

import com.example.eva01lab.Elements.Panel;
import com.example.eva01lab.GroupExtended;
import javafx.scene.paint.Color;

public class Eva01 {
    private final int x, y, z;
    private final GroupExtended mainGroup;
    private GroupExtended modelGroup = new GroupExtended();

    private int renderMode;

    public Eva01(int x, int y, int z, GroupExtended mainGroup, int renderMode) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.mainGroup = mainGroup;
        this.renderMode = renderMode;
    }

    public void redraw(int newRenderMode) {
        this.renderMode = newRenderMode;
        this.mainGroup.getChildren().remove(this.modelGroup);
        this.modelGroup = new GroupExtended();
        this.init();
    }

    public void init() {
        boolean isDefaultWireframe = (renderMode != 0);
        boolean isBoilerWireframe = (renderMode == 1);

        double chassisWidth = 800, chassisHeight = 40, chassisDepth = 300;
        double chassisBaseY = y + 80;

        Panel chassis = new Panel(chassisWidth, chassisHeight, chassisDepth, Color.DARKSLATEGRAY, isDefaultWireframe);
        modelGroup.getChildren().add(chassis.setPosition(x, chassisBaseY, z).getPanel());

        double boilerRadius = 130;
        double boilerY = chassisBaseY - chassisHeight / 2 - boilerRadius;
        CylinderP boiler = new CylinderP(boilerRadius, 650, Color.BLACK, isBoilerWireframe);
        modelGroup.getChildren().add(boiler.setRotate(0, 0, 90).setPosition(x - 50, boilerY, z).getCylinder());

        double cabHeight = 280;
        double cabY = chassisBaseY - chassisHeight / 2 - cabHeight / 2;
        Panel cab = new Panel(250, cabHeight, 280, Color.DARKRED, isDefaultWireframe);
        modelGroup.getChildren().add(cab.setPosition(x + (chassisWidth/2 - 125), cabY, z).getPanel());

        Panel cabRoof = new Panel(280, 20, 300, Color.BLACK, isDefaultWireframe);
        modelGroup.getChildren().add(cabRoof.setPosition(x + (chassisWidth/2 - 125), cabY - cabHeight/2 - 10, z).getPanel());

        double smokestackY = boilerY - boilerRadius - 50;
        CylinderP smokestack = new CylinderP(40, 100, Color.DARKSLATEGRAY, isDefaultWireframe);
        modelGroup.getChildren().add(smokestack.setPosition(x - 280, smokestackY, z).getCylinder());

        double wheelRadius = 80;
        double wheelWidth = 30;
        Color wheelColor = Color.GRAY;
        double wheelY = chassisBaseY + chassisHeight / 2;
        double wheelZOffset = chassisDepth / 2 + wheelWidth / 2;

        CylinderP wheel1 = new CylinderP(wheelRadius, wheelWidth, wheelColor, isDefaultWireframe);
        modelGroup.getChildren().add(wheel1.setRotate(90, 0, 0).setPosition(x + 250, wheelY, z + wheelZOffset).getCylinder());
        CylinderP wheel2 = new CylinderP(wheelRadius, wheelWidth, wheelColor, isDefaultWireframe);
        modelGroup.getChildren().add(wheel2.setRotate(90, 0, 0).setPosition(x + 250, wheelY, z - wheelZOffset).getCylinder());

        CylinderP wheel3 = new CylinderP(wheelRadius, wheelWidth, wheelColor, isDefaultWireframe);
        modelGroup.getChildren().add(wheel3.setRotate(90, 0, 0).setPosition(x + 100, wheelY, z + wheelZOffset).getCylinder());
        CylinderP wheel4 = new CylinderP(wheelRadius, wheelWidth, wheelColor, isDefaultWireframe);
        modelGroup.getChildren().add(wheel4.setRotate(90, 0, 0).setPosition(x + 100, wheelY, z - wheelZOffset).getCylinder());

        CylinderP wheel5 = new CylinderP(wheelRadius, wheelWidth, wheelColor, isDefaultWireframe);
        modelGroup.getChildren().add(wheel5.setRotate(90, 0, 0).setPosition(x - 250, wheelY, z + wheelZOffset).getCylinder());
        CylinderP wheel6 = new CylinderP(wheelRadius, wheelWidth, wheelColor, isDefaultWireframe);
        modelGroup.getChildren().add(wheel6.setRotate(90, 0, 0).setPosition(x - 250, wheelY, z - wheelZOffset).getCylinder());

        Panel frontBumper = new Panel(20, 100, 350, Color.DARKRED, isDefaultWireframe);
        modelGroup.getChildren().add(frontBumper.setPosition(x - chassisWidth/2 - 10, chassisBaseY, z).getPanel());

        mainGroup.getChildren().add(modelGroup);
    }
}