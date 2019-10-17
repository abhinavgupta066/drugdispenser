package com.gstar.drugaid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PatientRecords extends AppCompatActivity {

    String name,problem,number,presc,pills1,times1,tInterval1;
    EditText pName,pNumber,pProblem,pPrescription,pPills,pTimesPerDay,timeInterval;
    int pills,times,tInterval;
    Button AddRec;
    private TimePicker timePicker1;
   // final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("patients");
    static int i=1;

    Date currentTime = Calendar.getInstance().getTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_records);

        AddRec = findViewById(R.id.submit);
        pName = findViewById(R.id.pName);
        pNumber = findViewById(R.id.pNumber);
        pProblem = findViewById(R.id.pProblem);
        pPills = findViewById(R.id.pPills);
        pPrescription = findViewById(R.id.pDescription);
        pTimesPerDay = findViewById(R.id.pTimesPerDay);
        timePicker1 = findViewById(R.id.timePicker1);
        timeInterval = findViewById(R.id.TimeInterval);


        AddRec.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addPatients();
            }
        });
    }

    public void addPatients()
        {
            name = pName.getText().toString();
            number = pNumber.getText().toString();
            problem = pProblem.getText().toString();
            pills1 = pPills.getText().toString();
            pills = Integer.parseInt(pills1);
            presc = pPrescription.getText().toString();
            times1 = pTimesPerDay.getText().toString();
            times = Integer.parseInt(times1);
            tInterval1 = timeInterval.getText().toString();
            tInterval = Integer.parseInt(tInterval1);


            int hour = timePicker1.getHour();
            int min = timePicker1.getMinute();

            if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(number) && !TextUtils.isEmpty(problem) && !TextUtils.isEmpty(presc) && pills!=0 && times!=0 && tInterval!=0)
            {
                String id = "patient"+i;
                Patients patients = new Patients(id,name,number,problem,presc,pills,times,i, hour, min, tInterval);

                ref.child(id).setValue(patients);

                pName.setText("");
                pNumber.setText("");
                pProblem.setText("");
                pPills.setText("");
                pPrescription.setText("");
                pTimesPerDay.setText("");
                timeInterval.setText("");


                Toast.makeText(PatientRecords.this, "Data submitted doctor sahib", Toast.LENGTH_SHORT).show();

                Toast.makeText(PatientRecords.this, "Current time "+currentTime,Toast.LENGTH_LONG).show();

                i++;

            }
        }
}
