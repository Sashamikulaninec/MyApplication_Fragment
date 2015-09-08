package com.example.sasha.myapplication_fragment;

/**
 * Created by sasha on 14.08.15.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FragmentBrowser extends Fragment implements View.OnClickListener {

    private EditText mEText;
    private WebView mWebView;
    private Button mBtn;
    private Button mBtn2;
    public static final String APP_PREFERENCES_URL = "Url";
    public static final String APP_PREFERENCES = "myHome";
    public static final String APP_PREFERENCES_NAME = "homeurl";
    SharedPreferences mSettings;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_browser, null);
        mWebView     = (WebView) v.findViewById(R.id.webView);
        mEText       = (EditText)v.findViewById(R.id.editText);
        mBtn         = (Button)  v.findViewById(R.id.btnGo);
        mBtn2        = (Button)  v.findViewById(R.id.btnBack);
        mBtn.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        mWebView.setWebViewClient(new MyWebViewClient());
        mSettings    = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGo:
                if(mSettings.contains(APP_PREFERENCES_URL)){
                    mEText.setText(mSettings.getString(APP_PREFERENCES_URL, ""));

                    mWebView.loadUrl(mEText.getText().toString());
                }else {
                    String url = mEText.getText().toString();
                    mWebView.loadUrl(url);
                    mEText.setText(mWebView.getUrl());
                    mWebView.getSettings().setJavaScriptEnabled(true);
                }
                break;
            case R.id.btnBack:
                if (mWebView.canGoBack()) {
                    mWebView.goBack();
                } else
                    Toast.makeText(this.getActivity().getApplication(), "No way back", Toast.LENGTH_SHORT).show();
                break;

        }

    }

    public void onStop() {
        super.onStop();
        if (mWebView.getUrl() != null) {
            String loadUrl = mWebView.getUrl().toString();
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putString(APP_PREFERENCES_URL, loadUrl);
            editor.apply();
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.frag_browser, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.home) {
            if (mSettings.contains(APP_PREFERENCES_NAME)) {

                mEText.setText(mSettings.getString(APP_PREFERENCES_NAME, ""));

                mWebView.loadUrl(mEText.getText().toString());
            } else {
                mWebView.loadUrl("http://www.google.com.ua");
                mEText.setText(mWebView.getUrl());
                mWebView.getSettings().setJavaScriptEnabled(true);
                return true;
            }
        }
        if (id == R.id.newHome) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            final EditText input = new EditText(getActivity());
            builder.setMessage("Type new home address")
                    .setTitle("Home address")
                    .setView(input)
                    .setNegativeButton(android.R.string.cancel, null)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {


                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            String homeurl = input.getText().toString();
                            SharedPreferences.Editor editor = mSettings.edit();
                            editor.putString(APP_PREFERENCES_NAME, homeurl);
                            editor.apply();

                        }

                    });
            builder.create().show();
            return true;

        }
        return super.onOptionsItemSelected(item);

    }

}
