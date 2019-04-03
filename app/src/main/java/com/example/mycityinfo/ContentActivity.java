package com.example.mycityinfo;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ContentActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton btn1,btn2,btn3,btn4;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);

        btn1 = findViewById(R.id.imageButton1);
        btn2 = findViewById(R.id.imageButton2);
        btn3 = findViewById(R.id.imageButton3);
        btn4 = findViewById(R.id.imageButton4);
        firebaseAuth = FirebaseAuth.getInstance();

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    public void logout(){
        firebaseAuth.signOut();
        firebaseAuth.getCurrentUser().delete();
        finish();
        Intent i = new Intent(this,LoginActivity.class);
        startActivity(i);
        Toast.makeText(this,"Successfully Logged out",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v==btn1)
        {
            Intent i = new Intent(this,BirgunjActivity.class);
            startActivity(i);
        }
        if(v==btn2)
        {
            Intent i = new Intent(this,DharmanagarActivity.class);
            startActivity(i);
        }
        if(v==btn3)
        {
            Intent i = new Intent(this,KathmanduActivity.class);
            startActivity(i);
        }
        if(v==btn4) {
            logout();
        }
    }
}
