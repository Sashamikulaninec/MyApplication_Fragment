package com.example.sasha.myapplication_fragment;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by sasha on 27.07.15.
 */
public class MyWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url)
    {
        view.loadUrl(url);
        return true;
    }
}