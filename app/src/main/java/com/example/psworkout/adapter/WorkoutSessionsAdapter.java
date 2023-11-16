package com.example.psworkout.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.wear.activity.ConfirmationActivity;

import com.example.psworkout.databinding.WorkoutRowBinding;
import com.example.psworkout.model.WorkoutSession;

import java.util.List;

public class WorkoutSessionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    List<WorkoutSession> listWorkout;
    Context context;

    WorkoutRowBinding workoutRowBinding;

    public WorkoutSessionsAdapter(List<WorkoutSession> listWorkout, Context context)
    {
        this.listWorkout=listWorkout;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        workoutRowBinding=WorkoutRowBinding.inflate(layoutInflater,parent,false);
        return new ViewHolder(workoutRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).bindView(listWorkout.get(position));
    }

    @Override
    public int getItemCount() {
        return listWorkout.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        WorkoutRowBinding workoutRowBinding;
        public ViewHolder(WorkoutRowBinding WorkoutSessionsRowBinding){
            super(WorkoutSessionsRowBinding.getRoot());
            this.workoutRowBinding=WorkoutSessionsAdapter.this.workoutRowBinding;
        }
        public void bindView(WorkoutSession workoutSession){
            workoutRowBinding.txtWorkout.setText(workoutSession.getWorkoutDetails());
        }

        @Override
        public void onClick(View v) {

        }
    }
}
