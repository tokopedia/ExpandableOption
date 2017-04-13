package com.tokopedia.expandable;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by zulfikarrahman on 4/6/17.
 */

public abstract class BaseExpandableOptionText extends BaseExpandableOption {

    private TextView titleOption;

    public BaseExpandableOptionText(Context context) {
        super(context);
    }

    public BaseExpandableOptionText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseExpandableOptionText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BaseExpandableOptionText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        titleOption.setText(titleText);
        invalidate();
        requestLayout();
    }

    @Override
    protected void initView(View view) {
        titleOption = (TextView) view.findViewById(R.id.text_switch);
    }

    @Override
    protected void setTitleText(String titleText) {
        titleOption.setText(titleText);
        super.setTitleText(titleText);
    }
}
