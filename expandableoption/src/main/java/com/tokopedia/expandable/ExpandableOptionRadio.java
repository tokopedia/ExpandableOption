package com.tokopedia.expandable;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;

/**
 * Created by zulfikarrahman on 2/22/17.
 */

public class ExpandableOptionRadio extends BaseExpandableOption {

    private RadioButtonExpandable radioButton;
    private int radioId = NO_ID;

    public ExpandableOptionRadio(Context context) {
        super(context);
    }

    public ExpandableOptionRadio(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandableOptionRadio(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ExpandableOptionRadio(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        radioButton.setText(titleText);
        radioButton.setChecked(optionChecked);
        setVisibleChildView(optionChecked);
        radioButton.addOnCheckedChangeListener(onCheckedChangeRadio());
        invalidate();
        requestLayout();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.item_expandable_option_radio;
    }

    @Override
    protected void initView(View view) {
        radioButton = (RadioButtonExpandable) view.findViewById(R.id.radio_button);
        radioButton.setId(radioId);
    }

    @Override
    protected void init(AttributeSet attrs) {
        TypedArray styledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.ExpandableOption);
        try {
            radioId = styledAttributes.getResourceId(R.styleable.ExpandableOption_radio_id, NO_ID);
        } finally {
            styledAttributes.recycle();
        }
        super.init(attrs);
    }

    private CompoundButton.OnCheckedChangeListener onCheckedChangeRadio() {
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setVisibleChildView(b);
            }
        };
    }

    protected void setChecked(boolean b) {
        radioButton.setChecked(b);
        super.setChecked(b);
    }

    @Override
    public void setTitleText(String titleText) {
        radioButton.setText(titleText);
        super.setTitleText(titleText);
    }

}
