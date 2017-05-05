package com.tokopedia.expandable;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.view.ContextThemeWrapper;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by zulfikarrahman on 4/6/17.
 */

public abstract class BaseExpandableOption extends LinearLayout {

    public static final String OPTION_CHECKED = "OPTION_CHECKED";
    public static final String TITLE_TEXT = "TITLE_TEXT";
    public static final String OPTION_ENABLED = "OPTION_ENABLED";
    public static final String SUPER_STATE = "superState";
    @StyleRes
    private int expandableLayoutTheme;
    protected ExpandableViewLinear expandableViewLinear;

    protected boolean optionChecked;
    protected boolean optionEnabled = true;
    protected String titleText ="";
    private ExpandableListener expandableListener;

    public BaseExpandableOption(Context context) {
        super(context);
        init();
    }

    public BaseExpandableOption(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
        init();
    }

    public BaseExpandableOption(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BaseExpandableOption(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
        init();
    }

    @Override
    public int getOrientation() {
        return VERTICAL;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childs = getChildCount();
        for(int i = 1; i<childs; i++){
            View childView = getChildAt(i);
            ((ViewGroup)childView.getParent()).removeView(childView);
            expandableViewLinear.addView(childView);
        }
    }

    public void toggle() {
        if(expandableViewLinear.getVisibility() == GONE){
            setVisibleChildView(true);
        }else{
            setVisibleChildView(false);
        }
    }

    public void setVisibleChildView(boolean b) {
        optionChecked = b;
        if (b) {
            expandableViewLinear.expand();
        } else {
            expandableViewLinear.collapse();
        }

        if(expandableListener != null){
            expandableListener.onExpandViewChange(b);
        }
    }

    public void setExpand(boolean isChecked){
        optionChecked = isChecked;
        setVisibleChildView(isChecked);
    }

    public boolean isExpanded(){
        return optionChecked;
    }

    protected void setTitleText(String titleText){
        this.titleText = titleText;
    }

    protected String getTitleText(){
        return titleText;
    }

    public void setExpandableListener(ExpandableListener expandableListener){
        this.expandableListener = expandableListener;
    }

    @SuppressWarnings("RestrictedApi")
    protected void init(AttributeSet attributeSet){
        TypedArray styledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ExpandableOption);
        try {
            expandableLayoutTheme = styledAttributes.getResourceId(R.styleable.ExpandableOption_eo_theme, R.style.style_expandable_option_default);
            optionChecked = styledAttributes.getBoolean(R.styleable.ExpandableOption_eo_checked, false);
            titleText = styledAttributes.getString(R.styleable.ExpandableOption_eo_title);
            optionEnabled = styledAttributes.getBoolean(R.styleable.ExpandableOption_eo_enabled, true);
        } finally {
            styledAttributes.recycle();
        }
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getContext(), getContext().getTheme());
        contextThemeWrapper.setTheme(expandableLayoutTheme);
    }

    protected void init() {
        View view = inflate(getContext(), getLayoutRes(), this);
        initView(view);
        expandableViewLinear = (ExpandableViewLinear) view.findViewById(R.id.expandable_view);
        setVisibleChildView(optionChecked);
        setEnabled(optionEnabled);
        setOrientation(VERTICAL);
    }

    protected abstract void initView(View view);

    protected abstract int getLayoutRes();

    public interface ExpandableListener{
        void onExpandViewChange(boolean isExpand);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.childrenStates = new SparseArray();
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).saveHierarchyState(ss.childrenStates);
        }
        return ss;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).restoreHierarchyState(ss.childrenStates);
        }
    }

    @Override
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        dispatchFreezeSelfOnly(container);
    }

    @Override
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        dispatchThawSelfOnly(container);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        optionEnabled = enabled;
    }
}
