package com.gstar.drugaid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {

    Button createPatientRecords, OpenLid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        createPatientRecords = findViewById(R.id.createRec);
        OpenLid = findViewById(R.id.openLid);

        createPatientRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, PatientRecords.class);
                startActivity(intent);
            }
        });

        OpenLid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, OpenLid.class);
                startActivity(intent);
            }
        });

    }
}
