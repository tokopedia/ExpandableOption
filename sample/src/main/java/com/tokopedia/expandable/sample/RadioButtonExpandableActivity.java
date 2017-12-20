package com.tokopedia.expandable.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tokopedia.expandable.BaseExpandableOption;


public class RadioButtonExpandableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button_expandable);

        BaseExpandableOption baseExpandableOption = (BaseExpandableOption) findViewById(R.id.radio2);
        View headerView = LayoutInflater.from(this).inflate(R.layout.custom_header,
                (ViewGroup)baseExpandableOption.getRootView(), false);
        baseExpandableOption.setHeaderView(headerView);

        BaseExpandableOption baseExpandableOption2 = (BaseExpandableOption) findViewById(R.id.radio3);
        View headerView2 = LayoutInflater.from(this).inflate(R.layout.custom_header,
                (ViewGroup)baseExpandableOption.getRootView(), false);
        baseExpandableOption2.setHeaderView(headerView2);

    }
}
