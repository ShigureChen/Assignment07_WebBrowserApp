package edu.temple.webbrowserapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class BrowserActivity extends AppCompatActivity implements PageControlFragment.PageControlListener, PageViewerFragment.PageViewerListener{

    PageControlFragment pcf;
    PageViewerFragment pvf;
    FragmentManager fm;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pcf = new PageControlFragment();
        pvf = new PageViewerFragment();

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.add(R.id.page_control, pcf);
        ft.add(R.id.page_viewer, pvf);
        ft.commit();
    }

    @Override
    public void onURLSend(String string) {
        final String head = "https://";

        if(!string.substring(0,head.length()).equals(head))
        {
            string = head.concat(string);
        }

        Bundle bundle = new Bundle();
        bundle.putString("url", string);
        pvf.setArguments(bundle);
    }

    @Override
    public void onDataSend(String string) {
        Bundle bundle = new Bundle();
        bundle.putString("url", string);
        pcf.setArguments(bundle);
    }

    @Override
    public void onNextButtonClick() {
        pvf.webView.goForward();
    }

    @Override
    public void onBackButtonClick() {
        pvf.webView.goBack();
    }
}