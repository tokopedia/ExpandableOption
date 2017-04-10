package com.tokopedia.expandable;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

/**
 * Created by zulfikarrahman on 4/6/17.
 */

public class ExpandableOptionChecked extends BaseExpandableOptionText {

    CheckBox checkBox;

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
        checkBox.setChecked(optionChecked);
        checkBox.setOnCheckedChangeListener(onCheckedChange());
        super.onFinishInflate();
    }

    private CompoundButton.OnCheckedChangeListener onCheckedChange() {
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setVisibleChildView(isChecked);
            }
        };
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        checkBox = (CheckBox) view.findViewById(R.id.check_button);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.item_expandable_option_checked;
    }

    @Override
    protected void setChecked(boolean isChecked) {
        checkBox.setChecked(isChecked);
        super.setChecked(isChecked);
    }
}
