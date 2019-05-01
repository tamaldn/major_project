package com.example.mycityinfo;

import android.app.Dialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class BirgunjActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private Dialog myDialog;
    private Dialog infoDialog;
    Geocoder geocoder;
    List<Address> addresses;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.birgunj);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        geocoder = new Geocoder(this, Locale.getDefault());
        myDialog = new Dialog(this);
        infoDialog = new Dialog(this);
        expandableListView = findViewById(R.id.expandablelistview);
        final List<String> Headings = new ArrayList<>();
        List<String> L1 = new ArrayList<>();
        List<String> L2 = new ArrayList<>();
        List<String> L3 = new ArrayList<>();
        List<String> L4 = new ArrayList<>();
        List<String> L5 = new ArrayList<>();
        List<String> L6 = new ArrayList<>();
        List<String> L7 = new ArrayList<>();
        List<String> L8 = new ArrayList<>();
        final HashMap<String,List<String>> Childlist = new HashMap<>();
        String heading_items[] = getResources().getStringArray(R.array.header_titles);
        String l1[] = getResources().getStringArray(R.array.bir_hot);
        String l2[] = getResources().getStringArray(R.array.bir_tra);
        String l3[] = getResources().getStringArray(R.array.bir_res);
        String l4[] = getResources().getStringArray(R.array.bir_sho);
        String l5[] = getResources().getStringArray(R.array.bir_ban);
        String l6[] = getResources().getStringArray(R.array.bir_hea);
        String l7[] = getResources().getStringArray(R.array.bir_edu);
        String l8[] = getResources().getStringArray(R.array.bir_tem);

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

                final String txt = Childlist.get(Headings.get(groupPosition)).get(childPosition);
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

                final int locid = getResources().getIdentifier(idname,"string",getPackageName());
                final String locat = getString(locid);
                ImageButton loc = myDialog.findViewById(R.id.loc);
                loc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(locat));
                        startActivity(i);
                    }
                });

                String navig = idname+"n";
                final int navid = getResources().getIdentifier(navig,"string",getPackageName());
                final String navigat = getString(navid);
                ImageButton nav = myDialog.findViewById(R.id.navig);
                nav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(navigat));
                        startActivity(i);
                    }
                });

                ImageButton info = myDialog.findViewById(R.id.info);
                info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gotoinfo(locat,txt);
                    }
                });
                return false;
            }
        });
    }

    public void gotoinfo(String locat, String txt)
    {
        infoDialog.setContentView(R.layout.info_popup);
        infoDialog.show();
        String loc = locat;
        TextView title = infoDialog.findViewById(R.id.title);
        TextView featurename = infoDialog.findViewById(R.id.featurename);
        TextView locality = infoDialog.findViewById(R.id.locality);
        TextView adminarea = infoDialog.findViewById(R.id.adminarea);
        TextView countryname = infoDialog.findViewById(R.id.countryname);
        TextView postalcode = infoDialog.findViewById(R.id.postalcode);
        TextView close = infoDialog.findViewById(R.id.closebutton);

        String[] part1 = loc.split(Pattern.quote("@"));
        String[] part2 = part1[1].split(Pattern.quote(","));
        Double latitude = Double.parseDouble(part2[0]);
        Double longitude = Double.parseDouble(part2[1]);
        try
        {
            addresses = geocoder.getFromLocation(latitude,longitude,1);
            String feature = addresses.get(0).getFeatureName();
            String localit = addresses.get(0).getLocality();
            String area = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String code = addresses.get(0).getPostalCode();

            title.setText(txt);
            featurename.setText(feature);
            locality.setText(localit);
            adminarea.setText(area);
            countryname.setText(country);
            postalcode.setText(code);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoDialog.dismiss();
            }
        });
    }
}
