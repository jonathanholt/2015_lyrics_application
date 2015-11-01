package com.project.jholt_000.pamyupamyulyrics;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by jholt_000 on 01/08/2015.
 */
public class FirstFragment extends Fragment{

    private WebView browser;
    private String name;
    private WebSettings settings;
    private ImageView leftswipe;
    private ImageView rightswipe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.first_frag, container, false);
        browser=(WebView) v.findViewById(R.id.webkit);
        browser.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        rightswipe = (ImageView) v.findViewById(R.id.imageView2);
        leftswipe = (ImageView) v.findViewById(R.id.imageView);
        settings = browser.getSettings();
        String urlstring = getArguments().getString("msg");
        name = urlstring;
        urlstring = urlstring.replaceAll("\\s+", "");
        urlstring = urlstring.replaceAll("\\-", "");
        urlstring = urlstring.replaceAll("\\%", "");
        urlstring = urlstring.replaceAll("100", "onehundred");
        urlstring = urlstring.replaceAll("\\u00b0", "");
        urlstring = urlstring.replaceAll("\\u014D", "o");
        urlstring = urlstring.toLowerCase();
        if(urlstring.charAt(urlstring.length() - 1) == '1'){
            rightswipe.setVisibility(View.INVISIBLE);
        }
        if(urlstring.charAt(urlstring.length() - 1) == 'e'){
            leftswipe.setVisibility(View.INVISIBLE);
        }
        browser.loadUrl("file:///android_res/raw/" + urlstring + ".html");

        ImageButton imgbtnplus = (ImageButton) v.findViewById(R.id.imageButton3);
        imgbtnplus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                IncreaseTextSize();
            }
        });

        ImageButton imgbtn = (ImageButton) v.findViewById(R.id.imageButton2);
        imgbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DecreaseTextSize();
            }
        });
        return v;
    }

    public static FirstFragment newInstance(String text) {

        FirstFragment f = new FirstFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);
        return f;
    }

    public void IncreaseTextSize(){
        this.settings.setTextZoom(this.settings.getTextZoom() + 10);
    }

    public void DecreaseTextSize(){
        settings.setTextZoom(settings.getTextZoom() - 10);
    }

}