package edu.temple.webbrowserapp;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

public class PageControlFragment extends Fragment {

    PageControlListener pc;
    String string;
    EditText editText;
    ImageButton goButton;
    ImageButton nextButton;
    ImageButton backButton;
    public interface PageControlListener
    {
        void onURLSend(String string);
        void onNextButtonClick();
        void onBackButtonClick();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page_control, container, false);
        Bundle bundle = this.getArguments();
        editText = view.findViewById(R.id.editText);
        goButton = view.findViewById(R.id.goButton);
        nextButton = view.findViewById(R.id.nextButton);
        backButton = view.findViewById(R.id.backButton);

        if(bundle != null)
        {
            string = bundle.getString("url");
            editText.setText(string);
        }


        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                switch (keyEvent.getAction())
                {
                    case KeyEvent.ACTION_UP:
                        String string = editText.getText().toString();
                        editText.setText(string);
                    case KeyEvent.ACTION_DOWN:
                        break;
                }
                pc.onURLSend(string);
                return false;
            }

        });

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = editText.getText().toString();
                if(!string.equals(""))
                {
                    editText.setText(string);
                    pc.onURLSend(string);
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pc.onNextButtonClick();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pc.onBackButtonClick();
            }
        });

        return view;
    }

    public void updateText(String newURL)
    {
        this.string = newURL;
        editText.getText().clear();
        editText.setText(string);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PageControlListener) {
            pc = (PageControlListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement PCF");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}