package edu.temple.webbrowserapp;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PageViewerFragment extends Fragment {

    PageViewerListener pv;
    WebView webView;
    String string;
    public interface PageViewerListener
    {
        void onDataSend(String string);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page_viewer, container, false);
        webView = view.findViewById(R.id.webView);
        Bundle bundle = this.getArguments();

        if(bundle != null)
        {
            string = bundle.getString("url");
        }
        else
        {
            string = "https://www.google.com";
        }

        webView.loadUrl(string);


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            public void onPageFinished(WebView view, String url)
            {
                super.onPageFinished(view, url);
                string = webView.getUrl();
                pv.onDataSend(string);
            }
        });

        return view;
    }

    public void loadNewPage(String newUrl)
    {
        webView.loadUrl(newUrl);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PageViewerListener) {
            pv = (PageViewerListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentAListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}