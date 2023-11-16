package com.example.psworkout.model;

public class WorkoutSession
{
    private String id;
    private String workoutDetails;

    public WorkoutSession(String id, String workoutDetails) {
        this.id = id;
        this.workoutDetails = workoutDetails;
    }

    public String getId() {
        return id;
    }

    public String getWorkoutDetails() {
        return workoutDetails;
    }
}
