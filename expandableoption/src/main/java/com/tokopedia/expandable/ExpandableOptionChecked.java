package com.tokopedia.expandable;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

/**
 * Created by zulfikarrahman on 4/6/17.
 */

public class ExpandableOptionChecked extends BaseExpandableOptionText {

    private CheckBox checkBox;

    public ExpandableOptionChecked(Context context) {
        super(context);
    }

    public ExpandableOptionChecked(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandableOptionChecked(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ExpandableOptionChecked(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        checkBox.setChecked(isExpanded());
        checkBox.setOnCheckedChangeListener(onCheckedChange());
        super.onFinishInflate();
    }

    private CompoundButton.OnCheckedChangeListener onCheckedChange() {
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setExpand(isChecked);
            }
        };
    }

    @Override
    protected void init() {
        setHeaderLayoutRes(R.layout.item_expandable_option_checked_header);
        super.init();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        checkBox = (CheckBox) view.findViewById(R.id.check_button);
    }

    @Override
    public void setExpand(boolean isChecked) {
        checkBox.setChecked(isChecked);
        super.setExpand(isChecked);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        checkBox.setEnabled(enabled);
    }
}
