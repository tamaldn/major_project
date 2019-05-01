package com.example.mycityinfo;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ContentActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton btn1,btn2,btn3,btn4;
    private FirebaseAuth firebaseAuth;
    private Dialog dialog;
    private Dialog exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);

        btn1 = findViewById(R.id.imageButton1);
        btn2 = findViewById(R.id.imageButton2);
        btn3 = findViewById(R.id.imageButton3);
        btn4 = findViewById(R.id.imageButton4);
        firebaseAuth = FirebaseAuth.getInstance();

        dialog = new Dialog(this);
        exit = new Dialog(this);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        showexit();
    }

    public void logout(){
        firebaseAuth.signOut();
        finish();
        Intent i = new Intent(this,LoginActivity.class);
        finish();
        startActivity(i);
        Toast.makeText(this,"Successfully Logged out",Toast.LENGTH_SHORT).show();
    }

    public void showpopup()
    {
        dialog.setContentView(R.layout.logout_popup);
        dialog.show();
        TextView no = dialog.findViewById(R.id.no);
        TextView yes = dialog.findViewById(R.id.yes);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
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
            showpopup();
        }
    }
    public void showexit()
    {
        exit.setContentView(R.layout.exit);
        exit.show();
        TextView yes = exit.findViewById(R.id.yes);
        TextView no = exit.findViewById(R.id.no);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
                startActivity(intent);
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit.dismiss();
            }
        });
    }
}
