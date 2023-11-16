package com.example.psworkout.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.psworkout.R;
import com.example.psworkout.adapter.WorkoutSessionsAdapter;
import com.example.psworkout.adapter.WorkoutTypeAdapter;
import com.example.psworkout.databinding.ActivityListWorkoutsBinding;
import com.example.psworkout.databinding.ActivityMainBinding;
import com.example.psworkout.model.WorkoutSession;
import com.example.psworkout.model.WorkoutType;
import com.example.psworkout.utils.WorkoutUtils;

import java.util.ArrayList;
import java.util.List;

public class ListWorkouts extends AppCompatActivity {

    ActivityListWorkoutsBinding workoutsBinding;

    private List<WorkoutSession> workoutSessionList = new ArrayList<>();

    WorkoutSessionsAdapter workoutSessionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        workoutsBinding = ActivityListWorkoutsBinding.inflate(getLayoutInflater());
        View view = workoutsBinding.getRoot();
        setContentView(view);
        init();

    }

    private void init(){

        setLayout();
        loadDataInList();
    }



    private void loadDataInList()
    {
        workoutSessionList.clear();
        workoutSessionList.addAll(WorkoutUtils.getAllWorkoutSessions(this));
        bindAdapter();
    }

    private void bindAdapter(){
        workoutSessionsAdapter = new WorkoutSessionsAdapter(workoutSessionList, getApplicationContext());
        workoutsBinding.wrcWorkouts.setAdapter(workoutSessionsAdapter);
        workoutSessionsAdapter.notifyDataSetChanged();
    }

    private void setLayout(){

        workoutsBinding.wrcWorkouts.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        workoutsBinding.wrcWorkouts.setLayoutManager(layoutManager);
    }

}