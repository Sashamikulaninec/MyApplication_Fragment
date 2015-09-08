package com.example.sasha.myapplication_fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by sasha on 23.08.15.
 */
public class CustomView extends LinearLayout {

    public TextView textView1;
    public TextView textView2;

    public CustomView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();

    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();


    }

    public CustomView(Context context) {
        super(context);
        init();

    }

    public void init() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_item, this, true);

    }

    public void setTextName(String text1) {
        textView1 = (TextView) findViewById(R.id.name);
        textView1.setText(text1);

    }

    public void setTextNumber(String text2) {
        textView2 = (TextView) findViewById(R.id.number);
        textView2.setText(text2);

    }

}
