package edu.temple.webbrowserapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.WindowManager;

public class BrowserActivity extends AppCompatActivity implements PageControlFragment.PageControlListener, PageViewerFragment.PageViewerListener{

    PageControlFragment pcf;
    PageViewerFragment pvf;
    FragmentManager fm;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
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

    //URL from control
    @Override
    public void onURLSend(String string) {

        final String head = "https://";

        if(!string.substring(0,head.length()).equals(head))
        {
            string = head.concat(string);
        }
        pvf.loadNewPage(string);
    }


    //URL from viewer
    @Override
    public void onDataSend(String string) {
        pcf.updateText(string);
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