package com.example.jobs4smcyouth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by samsiu on 6/20/16.
 */
public class JobSiteWebViewFragment extends Fragment {
    private String url;
    WebView webView;
    CustomWebViewClient customWebViewClient;

    public JobSiteWebViewFragment(){
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_job_site_web_view, container, false);
        webView = (WebView)v.findViewById(R.id.jobSite_webView_id);
        customWebViewClient = new CustomWebViewClient();

        Bundle bundle = this.getArguments();
        url = "http://www." + bundle.getString("url", "http://hr.smcgov.org/SJFY");
        Log.d("JobWebViewFrag:", "This is returned url " + url);

        webView.setWebViewClient(customWebViewClient);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        customWebViewClient.onLoadResource(webView, url);
        return v;
    }

    private class CustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
            Log.d("JobSites", "onLoadResource: web page is loading");
        }
    }
}
