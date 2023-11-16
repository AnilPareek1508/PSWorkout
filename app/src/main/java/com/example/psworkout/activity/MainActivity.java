package com.example.psworkout.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.example.psworkout.R;
import com.example.psworkout.adapter.WorkoutTypeAdapter;
import com.example.psworkout.databinding.ActivityMainBinding;
import com.example.psworkout.model.WorkoutSession;
import com.example.psworkout.model.WorkoutType;
import com.example.psworkout.utils.WorkoutUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static String workoutType;

    ActivityMainBinding activityMainBinding;

    private List<WorkoutType> workoutTypeList = new ArrayList<>();

    int minutes;

    private WorkoutTypeAdapter workoutTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);
        init();
    }

    private void init(){

        loadDataInList();
        bindAdapter();
        setLayout();
    }



    private void loadDataInList()
    {
        workoutTypeList.add(new WorkoutType("Walking", R.drawable.walking_img));
        workoutTypeList.add(new WorkoutType("Running", R.drawable.running_img));
        workoutTypeList.add(new WorkoutType("Cycling", R.drawable.cycling_img));
    }

    private void bindAdapter(){
        workoutTypeAdapter = new WorkoutTypeAdapter(workoutTypeList, getApplicationContext());
        activityMainBinding.wrcWorkoutTypes.setAdapter(workoutTypeAdapter);
        workoutTypeAdapter.notifyDataSetChanged();
    }

    private void setLayout(){

        activityMainBinding.wrcWorkoutTypes.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        activityMainBinding.wrcWorkoutTypes.setLayoutManager(layoutManager);

        activityMainBinding.btnAddWorkout.setOnClickListener(this);
    }

    private WorkoutSession createWorkoutSession(String id, String workoutSession){
        if(id==null){
            id=String.valueOf(System.currentTimeMillis());}
        return new WorkoutSession(id , workoutSession);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.btnAddWorkout)
        {
            String mins = activityMainBinding.edtDuration.getText().toString().trim();

            if(workoutType==null || workoutType.equalsIgnoreCase(""))
            {
                activityMainBinding.edtDuration.setError(getString(R.string.error_msg_selectType));
            }
            else
            {
                if(mins==null || mins.equalsIgnoreCase(""))
                {
                    activityMainBinding.edtDuration.setError(getString(R.string.error_msg_empty));
                }
                else
                {

                    try
                    {
                        minutes = Integer.parseInt(mins);

                        int calories = caluclateCaloriesBurned(workoutType, minutes);

                        String msg = workoutType+" - "+minutes+" minutes Burned: "+calories+" Cal";

                        WorkoutSession workoutSession = createWorkoutSession(null, msg);

                        WorkoutUtils.saveWorkout(workoutSession, this);

                        Intent intent = new Intent(this, ListWorkouts.class);
                        startActivity(intent);


                    }
                    catch (NumberFormatException e)
                    {
                        activityMainBinding.edtDuration.setError(getString(R.string.error_msg_number));
                    }
                }
            }
        }
    }

    private int caluclateCaloriesBurned(String type, int minutes)
    {
        float met=0;

        if(type.equalsIgnoreCase("Walking"))
        {
            met = 3.5f;
        }
        else if(type.equalsIgnoreCase("Running"))
        {
            met = 9.8f;
        }
        else if(type.equalsIgnoreCase("Cycling"))
        {
            met = 11.5f;
        }
        int caloryPerMinute = (int) (met * 3.5 * 70/200);

        int totalCaloriesBurned = caloryPerMinute * minutes;

        return totalCaloriesBurned;
    }
}