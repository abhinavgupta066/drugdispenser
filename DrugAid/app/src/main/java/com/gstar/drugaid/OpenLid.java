package com.gstar.drugaid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class OpenLid extends AppCompatActivity {

    EditText PatientNumber;
    Button Open;
    String pnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_lid);

        PatientNumber = findViewById(R.id.patientNumber);

        Open = findViewById(R.id.open);

        Open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pnum = PatientNumber.getText().toString();

                HashMap<String,Object> result = new HashMap<>();
                result.put("dispenser1",1.0);

                FirebaseDatabase.getInstance().getReference().child("patients").child(pnum).updateChildren(result);

            }
        });

    }
}
