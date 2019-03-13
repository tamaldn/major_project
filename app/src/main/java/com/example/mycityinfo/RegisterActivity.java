package com.example.mycityinfo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button registerButton;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText cnfpasswordEditText;
    private Button loginButton;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        registerButton = (Button)findViewById(R.id.button);
        emailEditText = (EditText)findViewById(R.id.editText1);
        passwordEditText = (EditText)findViewById(R.id.editText2);
        cnfpasswordEditText = (EditText)findViewById(R.id.editText3);
        loginButton = (Button)findViewById(R.id.button2);
        progressDialog = new ProgressDialog(this,R.style.MyAlertDialogStyle);

        firebaseAuth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
    }

    public void registeruser(){

        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String cnfpassword = cnfpasswordEditText.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(cnfpassword)){
            Toast.makeText(this,"Please confirm your password",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.equals(password,cnfpassword)) {
            progressDialog.setTitle("Registering user");
            progressDialog.setMessage("Please wait");
            progressDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                        openlogin();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else {
            Toast.makeText(this,"Passwords Does not match",Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public void openlogin(){
        Intent i=new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {

        if(v == registerButton){
            registeruser();
        }

        if(v == loginButton){
            openlogin();
        }

    }
}
