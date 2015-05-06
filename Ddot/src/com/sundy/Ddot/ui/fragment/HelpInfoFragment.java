package com.sundy.Ddot.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.androidquery.AQuery;
import com.sundy.Ddot.R;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sundy on 15/5/6.
 */
public class HelpInfoFragment extends BaseFragment {

    private final String TAG = "HelpInfoFragment";
    private View v;
    private int type = 0;
    private WebView webView;

    public HelpInfoFragment() {
    }

    public HelpInfoFragment(int type) {
        this.type = type;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.d_help_info, container, false);

        aq = new AQuery(v);
        init();

        return v;
    }

    private void init() {
        aq.id(R.id.btnBack).clicked(onClick);
        aq.id(R.id.linear_all).clicked(onClick);

        webView = aq.id(R.id.webView).getWebView();
        if (type == 1) {
            webView.loadUrl("http://www.baidu.com");
        } else if (type == 2) {
            webView.loadUrl("http://www.weibo.com/");
        } else if (type == 3) {
            webView.loadUrl("https://www.google.com.hk/");
        } else if (type == 4) {
            webView.loadUrl("https://github.com/");
        } else if (type == 5) {
            webView.loadUrl("http://www.imooc.com/");
        }

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }
        });

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnBack:
                    onBackPressed();
                    break;
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}