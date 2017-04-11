package com.tokopedia.expandable.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tokopedia.expandable.BaseExpandableOption;
import com.tokopedia.expandable.ExpandableOptionRadio;

public class RadioButtonExpandableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button_expandable);

        ExpandableOptionRadio expandableOptionRadio = new ExpandableOptionRadio(this);
        expandableOptionRadio.setExpandableListener(new BaseExpandableOption.ExpandableListener() {
            @Override
            public void onExpandViewChange(boolean isExpand) {
                //do something
            }
        });
    }
}
