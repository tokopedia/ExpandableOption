package com.tokopedia.expandable;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;


public class ExpandableOption extends BaseExpandableOption {

    public ExpandableOption(Context context) {
        super(context);
    }

    public ExpandableOption(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandableOption(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ExpandableOption(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void initView(View view) {

    }

}
