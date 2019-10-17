package com.gstar.drugaid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreen extends AppCompatActivity {

    Button CreateAccount, LoginBtn;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListner;
    FirebaseUser mUser;
    String email, password;
    EditText Email, Password;
    public static final String userEmail = "";

    public static final String TAG = "LOGIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        CreateAccount = findViewById(R.id.createAccount);
        LoginBtn = findViewById(R.id.login);

        Email = findViewById(R.id.loginEmail);
        Password = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (mUser != null) {
                    Intent intent = new Intent(LoginScreen.this, DashboardActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Log.d(TAG, "AuthStateChanged:Logout");
                }

            }
        };

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Calling EditText is empty or no method.
                userSign();


            }
        });

        CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginScreen.this, SignupScreen.class);
                startActivity(intent);
            }
        });

    }

    private void userSign() {
        email = Email.getText().toString().trim();
        password = Password.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(LoginScreen.this, "Kindly Enter Email", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(LoginScreen.this, "Kindly Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {

                    Toast.makeText(LoginScreen.this, "Login not successfull", Toast.LENGTH_SHORT).show();

                } else {

                    //checkIfEmailVerified();
                    Email.getText().clear();

                    Password.getText().clear();
                    Intent intent = new Intent(LoginScreen.this, DashboardActivity.class);

            // Sending Email to Dashboard Activity using intent.
                    intent.putExtra(userEmail,email);

                    startActivity(intent);
                }
            }
        });
    }

//    private void checkIfEmailVerified(){
//        FirebaseUser users=FirebaseAuth.getInstance().getCurrentUser();
//        boolean emailVerified=users.isEmailVerified();
//        if(!emailVerified){
//            Toast.makeText(this,"Verify the Email Id",Toast.LENGTH_SHORT).show();
//            mAuth.signOut();
//            finish();
//        }
//        else {
//            Email.getText().clear();
//
//            Password.getText().clear();
//            Intent intent = new Intent(LoginScreen.this, DashboardActivity.class);
//
//            // Sending Email to Dashboard Activity using intent.
//            intent.putExtra(userEmail,email);
//
//            startActivity(intent);

//        }
//    }
}
