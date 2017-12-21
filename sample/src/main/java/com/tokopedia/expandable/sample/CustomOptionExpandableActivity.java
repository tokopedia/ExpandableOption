package com.tokopedia.expandable.sample;

import android.animation.LayoutTransition;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tokopedia.expandable.BaseExpandableOption;

public class CustomOptionExpandableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_style);

        ViewGroup layout = (ViewGroup) findViewById(R.id.linear_layout);
        LayoutTransition layoutTransition = layout.getLayoutTransition();
        layoutTransition.enableTransitionType(LayoutTransition.CHANGING);

        // header can be set programmatically here, instead of xml
        BaseExpandableOption baseExpandableOption = (BaseExpandableOption) findViewById(R.id.expandable_option);
        View headerView = LayoutInflater.from(this).inflate(R.layout.custom_header,
                (ViewGroup)baseExpandableOption.getRootView(), false);
        baseExpandableOption.setHeaderView(headerView);
    }
}
