package com.tokopedia.expandable;

import android.animation.LayoutTransition;
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
import android.view.ViewTreeObserver;
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
    private View footerView;
    private int headerLayoutRes;
    private int footerLayoutRes;
    private boolean isEnableAnimation;
    private ViewGroup vgRoot;

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

    private void checkParentAnimation() {
        if (getParent() != null) {
            ViewGroup parent = ((ViewGroup) getParent());
            LayoutTransition lt = parent.getLayoutTransition();
            if (isEnableAnimation) {
                if (lt == null) {
                    lt = new LayoutTransition();
                }
                lt.enableTransitionType(LayoutTransition.CHANGING);
                parent.setLayoutTransition(lt);
            }
        }
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
            isEnableAnimation = styledAttributes.getBoolean(R.styleable.BaseExpandableOption_eo_enable_animation, false);
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

        vgRoot = (ViewGroup) view.findViewById(R.id.vg_root);
        setEnableAnimation(isEnableAnimation);

        vgHeader = ((ViewGroup) view.findViewById(R.id.content_header_option));
        setUpHeaderFromRes();

        expandableChildViewLinear = (ViewGroup) view.findViewById(R.id.expandable_view);
        setUpFooterFromRes();

        addView(view);
        initView(view);

        setExpand(optionChecked);
        setEnabled(optionEnabled);

        this.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                checkParentAnimation();
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
    }

    public void setEnableAnimation(boolean enableAnimation) {
        isEnableAnimation = enableAnimation;
        if (vgRoot == null) {
            return;
        }
        if (isEnableAnimation) {
            LayoutTransition layoutTransition = vgRoot.getLayoutTransition();
            if (layoutTransition == null) {
                layoutTransition = new LayoutTransition();
            }
            layoutTransition.setStartDelay(LayoutTransition.CHANGE_DISAPPEARING, 0);
            layoutTransition.disableTransitionType(LayoutTransition.CHANGE_APPEARING);
            layoutTransition.disableTransitionType(LayoutTransition.APPEARING);
            vgRoot.setLayoutTransition(layoutTransition);
        } else {
            vgRoot.setLayoutTransition(null);
        }
        checkParentAnimation();
    }

    private void setUpHeaderFromRes() {
        if (getRootView() != null && headerLayoutRes > 0 && vgHeader != null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View headerView;
            headerView = inflater.inflate(headerLayoutRes, (ViewGroup) getRootView(), false);
            setHeaderView(headerView);
        }
    }

    private void setUpFooterFromRes() {
        if (getRootView() != null && footerLayoutRes > 0 && expandableChildViewLinear != null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View footerView;
            footerView = inflater.inflate(footerLayoutRes, (ViewGroup) getRootView(), false);
            setFooterView(footerView);
        }
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
            if (footerView != null) {
                if (footerView.getParent() != null) {
                    ((ViewGroup) footerView.getParent()).removeView(footerView);
                }
            }
            this.footerView = customFooterView;
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

    public boolean isChecked() {
        return isExpanded();
    }

    public void setChecked(boolean isChecked) {
        setExpand(isChecked);
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

    public void setHeaderLayoutRes(int headerLayoutRes) {
        this.headerLayoutRes = headerLayoutRes;
        setUpHeaderFromRes();
    }

    public void setFooterLayoutRes(int footerLayoutRes) {
        this.footerLayoutRes = footerLayoutRes;
        setUpFooterFromRes();
    }

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
