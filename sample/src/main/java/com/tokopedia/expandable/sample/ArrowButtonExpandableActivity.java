package com.tokopedia.expandable.sample;

import android.animation.LayoutTransition;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

public class ArrowButtonExpandableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrow_button_expandable);

        ViewGroup layout = (ViewGroup) findViewById(R.id.linear_layout);
        LayoutTransition layoutTransition = layout.getLayoutTransition();
        layoutTransition.enableTransitionType(LayoutTransition.CHANGING);
    }
}
