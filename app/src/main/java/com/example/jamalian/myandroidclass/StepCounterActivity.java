package com.example.jamalian.myandroidclass;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class StepCounterActivity extends AppCompatActivity {

    TextView steps;
    TextView from;
    TextView lnow;
    TextView lonow;
    Button estimate;
    LocationModel myloc = new LocationModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1500);
        }
        bind();
        myloc.setLongtitude(35.763637);
        myloc.setLatitude(51.284842);
        estimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent counterService = new Intent(StepCounterActivity.this, StepsCounterService.class);

                startService(counterService);



            }
        });

    }

    void bind() {

        steps = (TextView) findViewById(R.id.steps);
        from = (TextView) findViewById(R.id.from);
        estimate = (Button) findViewById(R.id.estimate);

        lnow = (TextView) findViewById(R.id.lnow);
        lonow = (TextView) findViewById(R.id.lonow);
    }

    @Subscribe
    public void count(LocationModel model) {


      steps.setText((int) (( myloc.getLatitude()-model.getLatitude())/(myloc.getLongtitude()-model.getLongtitude())));

        Toast.makeText(this, "mokhtasate feli resid", Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
}

