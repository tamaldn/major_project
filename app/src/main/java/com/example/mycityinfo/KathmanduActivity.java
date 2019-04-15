package com.example.mycityinfo;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KathmanduActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kathmandu);

        myDialog = new Dialog(this);
        expandableListView = (ExpandableListView)findViewById(R.id.expandablelistview);
        final List<String> Headings = new ArrayList<String>();
        List<String> L1 = new ArrayList<String>();
        List<String> L2 = new ArrayList<String>();
        List<String> L3 = new ArrayList<String>();
        List<String> L4 = new ArrayList<String>();
        List<String> L5 = new ArrayList<String>();
        List<String> L6 = new ArrayList<String>();
        List<String> L7 = new ArrayList<String>();
        List<String> L8 = new ArrayList<String>();
        final HashMap<String,List<String>> Childlist = new HashMap<String,List<String>>();
        String heading_items[] = getResources().getStringArray(R.array.header_titles);
        String l1[] = getResources().getStringArray(R.array.ktm_hot);
        String l2[] = getResources().getStringArray(R.array.ktm_tra);
        String l3[] = getResources().getStringArray(R.array.ktm_res);
        String l4[] = getResources().getStringArray(R.array.ktm_sho);
        String l5[] = getResources().getStringArray(R.array.ktm_ban);
        String l6[] = getResources().getStringArray(R.array.ktm_hea);
        String l7[] = getResources().getStringArray(R.array.ktm_edu);
        String l8[] = getResources().getStringArray(R.array.ktm_tem);

        for(String title : heading_items)
        {
            Headings.add(title);
        }
        for(String title : l1)
        {
            L1.add(title);
        }
        for(String title : l2)
        {
            L2.add(title);
        }
        for(String title : l3)
        {
            L3.add(title);
        }
        for(String title : l4)
        {
            L4.add(title);
        }
        for(String title : l5)
        {
            L5.add(title);
        }
        for(String title : l6)
        {
            L6.add(title);
        }
        for(String title : l7)
        {
            L7.add(title);
        }
        for(String title : l8)
        {
            L8.add(title);
        }
        Childlist.put(Headings.get(0),L1);
        Childlist.put(Headings.get(1),L2);
        Childlist.put(Headings.get(2),L3);
        Childlist.put(Headings.get(3),L4);
        Childlist.put(Headings.get(4),L5);
        Childlist.put(Headings.get(5),L6);
        Childlist.put(Headings.get(6),L7);
        Childlist.put(Headings.get(7),L8);
        ListAdapter listAdapter = new ListAdapter(this, Headings, Childlist);
        expandableListView.setAdapter(listAdapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick (ExpandableListView parent, View v, int groupPosition, final int childPosition, final long id) {

                String txt = Childlist.get(Headings.get(groupPosition)).get(childPosition);
                String idname = txt.toLowerCase().replaceAll("\\s+","");
                myDialog.setContentView(R.layout.custom_popup);
                TextView textView;
                textView = myDialog.findViewById(R.id.textView);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                myDialog.show();

                TextView textView1 = myDialog.findViewById(R.id.title);
                textView1.setText(txt);

                ImageView imageView = myDialog.findViewById(R.id.img);
                int imgres = getResources().getIdentifier(idname,"drawable", getPackageName());
                imageView.setImageResource(imgres);

                ImageButton loc = myDialog.findViewById(R.id.loc);
                loc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse("https://www.google.com/maps/dir//District+Hospital,+North+Tripura,+Chandrapur,+Dharmanagar,+Tripura+799251/@24.3886794,92.1591341,17.42z/data=!4m9!4m8!1m0!1m5!1m1!1s0x3751ecc77c92b9cb:0x5104dc9375ae5e91!2m2!1d92.1578064!2d24.3898786!3e0"));
                        startActivity(i);
                    }
                });

                return false;
            }
        });
    }
}
