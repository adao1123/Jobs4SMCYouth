package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.jobs4smcyouth.R;

import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class LaborLawsWebviewFragment extends Fragment {
    private static final String URL = "http://www.dir.ca.gov/dlse/MinorsSummaryCharts.pdf";
    WebView webView;
    CustomWebViewClient customWebViewClient;
    public LaborLawsWebviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_labor_laws_webview, container, false);
        webView = (WebView) v.findViewById(R.id.laborLaws_webView_id);
        customWebViewClient = new CustomWebViewClient();
        webView.setWebViewClient(customWebViewClient);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(URL);
        return v;
    }


    private class CustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url); //try return false;
        }
    }

}

// <url of webview fragment
