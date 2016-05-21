package com.example.jobs4smcyouth;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class TransportWebViewFragment extends Fragment {
    private final String URL = "https://docs.google.com/spreadsheets/d/1f6V2nEdJ4HuXhshnqMPJfHyRtLxc-ivOBOXT2BjYYeo/edit#gid=1990877982";
    WebView webView;
    CustomWebViewClient customWebViewClient;

    public TransportWebViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_transport_web_view, container, false);
        webView = (WebView) v.findViewById(R.id.laborLaws_webView_id);
        webView.loadUrl(URL);
        customWebViewClient = new CustomWebViewClient();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(customWebViewClient);
        return v;

    }

    private class CustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url); //try return false;
        }
    }

}
