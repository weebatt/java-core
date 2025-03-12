package org.example.stages;

import javafx.scene.canvas.Canvas;

public class StickmanWrapper {
    private final Canvas canvas;
    private double dx;
    private double dy;
    private long groupId;
    private String username;

    public StickmanWrapper(Canvas canvas, double dx, double dy, long groupId, String username) {
        this.canvas = canvas;
        this.dx = dx;
        this.dy = dy;
        this.groupId = groupId;
        this.username = username;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public long getGroupId(){
        return groupId;
    }

    public String getUsername(){
        return username;
    }
}