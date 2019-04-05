package com.example.mycityinfo;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DharmanagarActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dharmanagar);

        myDialog = new Dialog(this);
        expandableListView = (ExpandableListView)findViewById(R.id.expandablelistview);
        final List<String> Headings = new ArrayList<String>();
        List<String> L1 = new ArrayList<String>();
        List<String> L2 = new ArrayList<String>();
        List<String> L3 = new ArrayList<String>();
        final HashMap<String,List<String>> Childlist = new HashMap<String,List<String>>();
        String heading_items[] = getResources().getStringArray(R.array.header_titles);
        String l1[] = getResources().getStringArray(R.array.h1_items);
        String l2[] = getResources().getStringArray(R.array.h2_items);
        String l3[] = getResources().getStringArray(R.array.h3_items);

        for(String title : heading_items)
        {
            Headings.add(title);
        }
        for(String title : l1)
        {
            L1.add(title);
        }for(String title : l2)
        {
            L2.add(title);
        }for(String title : l3)
        {
            L3.add(title);
        }
        Childlist.put(Headings.get(0),L1);
        Childlist.put(Headings.get(1),L2);
        Childlist.put(Headings.get(2),L3);
        ListAdapter listAdapter = new ListAdapter(this, Headings, Childlist);
        expandableListView.setAdapter(listAdapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick (ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                String txt = Childlist.get(Headings.get(groupPosition)).get(childPosition);
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
                TextView textView1 = myDialog.findViewById(R.id.sobi);
                textView1.setText(txt);

                return false;
            }
        });
    }
}
