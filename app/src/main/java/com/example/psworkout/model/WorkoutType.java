package com.example.psworkout.model;

public class WorkoutType
{
    private String type;
    private int image;

    public WorkoutType(String type, int image) {
        this.type = type;
        this.image=image;
    }

    public String getType() {
        return type;
    }

    public int getImage() {
        return image;
    }
}
