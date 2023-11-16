package com.example.psworkout.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.wear.activity.ConfirmationActivity;

import com.example.psworkout.R;
import com.example.psworkout.activity.MainActivity;
import com.example.psworkout.databinding.WorkouttypeRowBinding;
import com.example.psworkout.model.WorkoutType;

import java.util.List;

public class WorkoutTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    List<WorkoutType> listWorkout;
    Context context;

    WorkouttypeRowBinding workouttypeRowBinding;

    public WorkoutTypeAdapter(List<WorkoutType> listWorkout, Context context)
    {
        this.listWorkout=listWorkout;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        workouttypeRowBinding=WorkouttypeRowBinding.inflate(layoutInflater,parent,false);
        return new ViewHolder(workouttypeRowBinding);
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
        WorkouttypeRowBinding workouttypeRowBinding;

        WorkoutType workoutType;
        public ViewHolder(WorkouttypeRowBinding workouttypeRowBinding){
            super(workouttypeRowBinding.getRoot());
            this.workouttypeRowBinding=workouttypeRowBinding;
        }
        public void bindView(WorkoutType workoutType){
            this.workoutType=workoutType;
            workouttypeRowBinding.txtWorkoutType.setText(workoutType.getType());
            workouttypeRowBinding.workoutIcon.setImageResource(workoutType.getImage());

            workouttypeRowBinding.txtWorkoutType.setOnClickListener(this);
            workouttypeRowBinding.workoutIcon.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            if(v.getId() == R.id.txtWorkoutType || v.getId() == R.id.workoutIcon)
            {
                MainActivity.workoutType=workoutType.getType();
            }
        }
    }
}
