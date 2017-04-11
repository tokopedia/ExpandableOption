package com.tokopedia.expandable.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonExpandableRadio;
    Button switchButtonExpandable;
    Button checkedButtonExpandable;
    Button arrowButtonExpandable;
    Button customButtonExpandable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonExpandableRadio = (Button) findViewById(R.id.radio_button_expandable_with_group);
        buttonExpandableRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RadioButtonExpandableActivity.class));
            }
        });
        switchButtonExpandable = (Button) findViewById(R.id.switch_button_expandable);
        switchButtonExpandable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SwitchButtonExpandableActivity.class));
            }
        });
        checkedButtonExpandable = (Button) findViewById(R.id.checked_button_expandable);
        checkedButtonExpandable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CheckedButtonExpandableActivity.class));
            }
        });
        arrowButtonExpandable = (Button) findViewById(R.id.arrow_button_expandable);
        arrowButtonExpandable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ArrowButtonExpandableActivity.class));
            }
        });
        customButtonExpandable = (Button) findViewById(R.id.custom_button_expandable);
        customButtonExpandable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CustomOptionExpandableActivity.class));
            }
        });
    }
}
