package com.tokopedia.expandable;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.CompoundButton;

/**
 * Created by zulfikarrahman on 2/22/17.
 */

public abstract class BaseExpandableOptionRadio extends BaseExpandableOption {

    public BaseExpandableOptionRadio(Context context) {
        super(context);
    }

    private OnRadioCheckChangedListener onRadioCheckChangedListener;
    public interface OnRadioCheckChangedListener {
        void onCheckChangedListener(BaseExpandableOptionRadio expandableOptionRadio,
                                    int id, boolean isChecked);
    }

    public void setOnRadioCheckChangedListener(OnRadioCheckChangedListener onRadioCheckChangedListener) {
        this.onRadioCheckChangedListener = onRadioCheckChangedListener;
    }

    public BaseExpandableOptionRadio(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseExpandableOptionRadio(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BaseExpandableOptionRadio(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void initView(View view) {
        CompoundButton checkable = getCheckable(view);
        if (checkable != null) {
            getCheckable(view).setText(titleText);
            checkable.setChecked(isExpanded());
            checkable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    onHeaderClicked();
                }
            });
        }
        setExpand(isExpanded());

    }

    public abstract CompoundButton getCheckable(View view);

    private void onRadioClicked(){
        setExpand(true);
        if (onRadioCheckChangedListener != null) {
            onRadioCheckChangedListener.onCheckChangedListener(this, getId(), true);
        }
    }

    @Override
    protected void onHeaderClicked() {
        if (!isExpanded()) {
            onRadioClicked();
        }
    }

    public void setExpand(boolean b) {
        if (b != isExpanded()) {
            CompoundButton compoundButton = getCheckable(this.getRootView());
            if (compoundButton!= null) {
                compoundButton.setChecked(b);
            }
        }
        super.setExpand(b);
    }

    @Override
    public void setTitleText(String titleText) {
        if (getCheckable(this.getRootView())!= null) {
            getCheckable(this.getRootView()).setText(titleText);
        }
        super.setTitleText(titleText);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (getCheckable(this.getRootView())!= null) {
            getCheckable(this.getRootView()).setEnabled(enabled);
        }
    }
}
