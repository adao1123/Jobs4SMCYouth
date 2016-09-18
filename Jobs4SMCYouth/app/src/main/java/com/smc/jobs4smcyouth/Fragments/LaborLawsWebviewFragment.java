package com.smc.jobs4smcyouth.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.smc.jobs4smcyouth.MyApplication;

/**
 * A simple {@link Fragment} subclass.
 */
public class LaborLawsWebviewFragment extends Fragment {
    private final String URL = "http://www.dir.ca.gov/dlse/MinorsSummaryCharts.pdf";
    private static final String TAG = LaborLawsWebviewFragment.class.getSimpleName();

    WebView webView;
    CustomWebViewClient customWebViewClient;

    Tracker analyticsTracker;

    public LaborLawsWebviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        MyApplication application = (MyApplication) getActivity().getApplication();
        analyticsTracker = application.getDefaultTracker();
        sendScreenImageName();
    }

    private void sendScreenImageName(){
        String name = TAG;
        // [START screen_view_hit]
        Log.i(TAG, "Setting screen name: " + name);
        analyticsTracker.setScreenName("Screen~" + "LaborLawsWebviewFragment");
        analyticsTracker.send(new HitBuilders.ScreenViewBuilder().build());
        // [END screen_view_hit]

        analyticsTracker.send(new HitBuilders.EventBuilder()
                .setCategory("Action")
                .setAction("Share")
                .build());
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(com.smc.jobs4smcyouth.R.layout.fragment_labor_laws_webview, container, false);
        webView = (WebView) v.findViewById(com.smc.jobs4smcyouth.R.id.laborLaws_webView_id);

        customWebViewClient = new CustomWebViewClient();
        webView.setWebViewClient(customWebViewClient);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(URL);
        customWebViewClient.onLoadResource(webView, URL);

        return v;
    }


    private class CustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url); //try return false;
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
            Log.i("laborLaws", "onLoadResource: web page is loading");
        }
    }

}

// <url of webview fragment
