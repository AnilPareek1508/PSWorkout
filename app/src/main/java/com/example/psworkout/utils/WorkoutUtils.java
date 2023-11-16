package com.example.psworkout.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.psworkout.model.WorkoutSession;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WorkoutUtils
{
    public static void saveWorkout(WorkoutSession workoutSession, Context context) {
        if (workoutSession != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("workout_session_details", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(workoutSession.getId(), workoutSession.getWorkoutDetails());
            editor.commit();
        }
    }

    public static List<WorkoutSession> getAllWorkoutSessions(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("workout_session_details", Context.MODE_PRIVATE);
        List<WorkoutSession> workoutSessionList = new ArrayList<>();
        Map<String, ?> map = sharedPreferences.getAll();
        Set set = map.entrySet();
        Iterator itr = set.iterator();
        while (itr.hasNext()) {
            Map.Entry entry = (Map.Entry) itr.next();
            String savedWorkoutSession = (String) entry.getValue();
            if (savedWorkoutSession != null) {
                WorkoutSession workoutSession = new WorkoutSession(entry.getKey().toString(), savedWorkoutSession);
                workoutSessionList.add(workoutSession);
            }
        }
        return workoutSessionList;
    }
}
