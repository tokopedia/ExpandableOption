package com.tokopedia.expandable;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by zulfikarrahman on 4/6/17.
 */

public class ExpandableViewLinear extends LinearLayout implements ExpandableView {

    public ExpandableViewLinear(Context context) {
        super(context);
    }

    public ExpandableViewLinear(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandableViewLinear(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ExpandableViewLinear(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void expand() {
        setVisibility(VISIBLE);
    }

    @Override
    public void collapse() {
        setVisibility(GONE);
    }
}
