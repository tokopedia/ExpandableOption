package com.tokopedia.expandable;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.view.ContextThemeWrapper;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by zulfikarrahman on 4/6/17.
 */

public abstract class BaseExpandableOption extends LinearLayout implements View.OnClickListener {

    protected String titleText = "";
    private ExpandableListener expandableListener;
    private ViewGroup vgHeader;
    protected ViewGroup expandableChildViewLinear;
    private boolean optionChecked;
    private boolean optionEnabled = true;

    private View headerView;
    private int headerLayoutRes;

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

    @SuppressWarnings("RestrictedApi")
    protected void init(AttributeSet attributeSet) {
        TypedArray styledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.BaseExpandableOption);
        int expandableLayoutTheme;
        try {
            expandableLayoutTheme = styledAttributes.getResourceId(R.styleable.BaseExpandableOption_eo_theme,
                    R.style.style_expandable_option_default);
            optionChecked = styledAttributes.getBoolean(R.styleable.BaseExpandableOption_eo_checked, false);
            titleText = styledAttributes.getString(R.styleable.BaseExpandableOption_eo_title);
            optionEnabled = styledAttributes.getBoolean(R.styleable.BaseExpandableOption_eo_enabled, true);
            headerLayoutRes = styledAttributes.getResourceId(R.styleable.BaseExpandableOption_eo_header_layout, -1);
        } finally {
            styledAttributes.recycle();
        }
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getContext(), getContext().getTheme());
        contextThemeWrapper.setTheme(expandableLayoutTheme);
    }

    protected void init() {
        setOrientation(getOrientation());

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.item_expandable_option_base, this, false);

        vgHeader = ((ViewGroup) view.findViewById(R.id.content_header_option));
        View headerView;
        if (headerLayoutRes != -1) {
            headerView = inflater.inflate(headerLayoutRes, (ViewGroup)getRootView(), false);
        } else {
            headerView = getHeaderLayout(inflater, (ViewGroup)getRootView());
        }
        setHeaderView(headerView);

        expandableChildViewLinear = (ViewGroup) view.findViewById(R.id.expandable_view);
        View footerView = getFooterLayout(inflater, (ViewGroup)getRootView());
        setFooterView(footerView);

        addView(view);
        initView(view);

        setExpand(optionChecked);
        setEnabled(optionEnabled);
    }


    public boolean isHeaderCanToogle() {
        return true;
    }

    public void setHeaderView(View customHeaderView) {
        if (customHeaderView != null) {
            if (headerView != null) {
                if (headerView.getParent() != null) {
                    ((ViewGroup) headerView.getParent()).removeView(headerView);
                }
            }
            this.headerView = customHeaderView;
            vgHeader.addView(customHeaderView, 0);
            if (isHeaderCanToogle()) {
                customHeaderView.setClickable(true);
                customHeaderView.setOnClickListener(this);
            }
        }
    }

    public void setFooterView(View customFooterView) {
        if (customFooterView != null) {
            expandableChildViewLinear.addView(customFooterView);
        }
    }

    public void toggle() {
        if (isExpanded()) {
            setExpand(false);
        } else {
            setExpand(true);
        }
    }

    public void setExpand(boolean isExpanded) {
        if (isExpanded) {
            expandableChildViewLinear.setVisibility(View.VISIBLE);
        } else {
            expandableChildViewLinear.setVisibility(View.GONE);
        }

        if (expandableListener != null) {
            expandableListener.onExpandViewChange(isExpanded);
        }
    }

    public boolean isExpanded() {
        return expandableChildViewLinear.getVisibility() == View.VISIBLE;
    }

    protected void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    protected String getTitleText() {
        return titleText;
    }

    public void setExpandableListener(ExpandableListener expandableListener) {
        this.expandableListener = expandableListener;
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (child.getId() == R.id.vg_root) {
            super.addView(child, index, params);
        } else {
            // remove the link between child and previous parent before add (if any)
            if (child.getParent() != null) {
                ViewGroup viewParent = (ViewGroup) child.getParent();
                viewParent.removeView(child);
            }
            expandableChildViewLinear.addView(child, params);
        }
    }

    protected abstract void initView(View view);

    protected abstract View getHeaderLayout(LayoutInflater inflater, ViewGroup parent);

    protected abstract View getFooterLayout(LayoutInflater inflater, ViewGroup parent);

    @Override
    public void onClick(View view) {
        if (view == headerView) {
            if (isEnabled()) {
                onHeaderClicked();
            }
        }
    }

    protected void onHeaderClicked() {
        toggle();
    }

    public interface ExpandableListener {
        void onExpandViewChange(boolean isExpand);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.childrenStates = new SparseArray<>();
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

}
