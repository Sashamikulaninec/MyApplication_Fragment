package com.example.sasha.myapplication_fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;


public class MainActivity extends FragmentActivity implements View.OnClickListener{

    FragmentBrowser fragmentBrowser;
    FragmentContacts fragmentContacts;
    FragmentFile fragmentFile;

    private Button mBtnBrowser;
    private Button mBtnContacts;
    private Button mBtnFiles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentBrowser  = new FragmentBrowser();
        fragmentContacts = new FragmentContacts();
        fragmentFile  = new FragmentFile();

        mBtnBrowser      = (Button) findViewById(R.id.btnBrowser_AM);
        mBtnContacts     = (Button) findViewById(R.id.btnContacts_AM);
        mBtnFiles     = (Button) findViewById(R.id.btnFiles_AM);

        mBtnBrowser.setOnClickListener(this);
        mBtnContacts.setOnClickListener(this);
        mBtnFiles.setOnClickListener(this);


    }

    public void onClick(View v) {
        android.support.v4.app.FragmentManager fragMan = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fragMan.beginTransaction();
        switch (v.getId()) {
            case R.id.btnBrowser_AM:
                ft.add(R.id.fragment2, fragmentBrowser);
                break;
            case R.id.btnContacts_AM:
                ft.replace(R.id.fragment2, fragmentContacts);
                break;
            case R.id.btnFiles_AM:
                ft.replace(R.id.fragment2, fragmentFile);
                break;
        }
        ft.addToBackStack(null);
        ft.commit();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();

    }
}

