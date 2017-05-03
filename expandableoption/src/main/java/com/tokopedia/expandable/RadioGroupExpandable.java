package com.tokopedia.expandable;

/**
 * Created by zulfikarrahman on 2/22/17.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;



public class RadioGroupExpandable extends LinearLayout {
    public static final String CHECKED_ID = "CheckedId";
    public static final String PROTECT_FROM_CHECKED = "PROTECT_FROM_CHECKED";
    public static final String SUPER_STATE = "superState";

    private int checkedId = -1;
    private CompoundButton.OnCheckedChangeListener checkedChangeListener;
    private boolean protectFromCheckedChange = false;
    private OnCheckedChangeListener onCheckedChangeListener;
    private HierarchyChangeListener hierarcyChangeListener;

    public RadioGroupExpandable(Context context) {
        super(context);
        setOrientation(VERTICAL);
        init();
    }

    public RadioGroupExpandable(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        checkedChangeListener = new CheckedStateTracker();
        hierarcyChangeListener = new HierarchyChangeListener();
        super.setOnHierarchyChangeListener(hierarcyChangeListener);
    }

    @Override
    public void setOnHierarchyChangeListener(OnHierarchyChangeListener listener) {
        hierarcyChangeListener.onHierarchyChangeListener = listener;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (checkedId != -1) {
            protectFromCheckedChange = true;
            setCheckedStateForView(checkedId, true);
            protectFromCheckedChange = false;
            setCheckedId(checkedId);
        }
    }


    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (child instanceof RadioButtonExpandable) {
            final RadioButtonExpandable button = (RadioButtonExpandable) child;
            setFlagRadioButton(button);
        }

        super.addView(child, index, params);
    }

    public void check(@IdRes int id) {
        if (id != -1 && (id == checkedId)) {
            return;
        }

        if (checkedId != -1) {
            setCheckedStateForView(checkedId, false);
        }

        if (id != -1) {
            setCheckedStateForView(id, true);
        }

        setCheckedId(id);
    }

    private void setCheckedId(@IdRes int id) {
        checkedId = id;
        if (onCheckedChangeListener != null) {
            onCheckedChangeListener.onCheckedChanged(this, checkedId);
        }
    }

    private void setCheckedStateForView(int viewId, boolean checked) {
        View checkedView = findViewById(viewId);
        if (checkedView != null && checkedView instanceof RadioButtonExpandable) {
            ((RadioButtonExpandable) checkedView).setChecked(checked);
        }
    }

    @IdRes
    public int getCheckedRadioButtonId() {
        return checkedId;
    }

    public void clearCheck() {
        check(-1);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        onCheckedChangeListener = listener;
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new RadioGroupExpandable.LayoutParams(getContext(), attrs);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof RadioGroup.LayoutParams;
    }

    @Override
    protected LinearLayout.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    public CharSequence getAccessibilityClassName() {
        return RadioGroup.class.getName();
    }

    public static class LayoutParams extends LinearLayout.LayoutParams {

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int w, int h) {
            super(w, h);
        }

        public LayoutParams(int w, int h, float initWeight) {
            super(w, h, initWeight);
        }

        public LayoutParams(ViewGroup.LayoutParams p) {
            super(p);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        @Override
        protected void setBaseAttributes(TypedArray a,
                                         int widthAttr, int heightAttr) {

            if (a.hasValue(widthAttr)) {
                width = a.getLayoutDimension(widthAttr, "layout_width");
            } else {
                width = WRAP_CONTENT;
            }

            if (a.hasValue(heightAttr)) {
                height = a.getLayoutDimension(heightAttr, "layout_height");
            } else {
                height = WRAP_CONTENT;
            }
        }
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(RadioGroupExpandable group, @IdRes int checkedId);
    }

    private class CheckedStateTracker implements CompoundButton.OnCheckedChangeListener {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if (protectFromCheckedChange) {
                return;
            }

            protectFromCheckedChange = true;
            if (checkedId != -1) {
                setCheckedStateForView(checkedId, false);
            }
            protectFromCheckedChange = false;

            int id = buttonView.getId();
            setCheckedId(id);
        }
    }

    private class HierarchyChangeListener implements
            ViewGroup.OnHierarchyChangeListener {
        private ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener;

        public void traverseTree(View view) {
            if (view instanceof RadioButtonExpandable) {
                int id = view.getId();
                if (id == View.NO_ID) {
                    id = generateId();
                    view.setId(id);
                }
                RadioButtonExpandable radioButton = ((RadioButtonExpandable) view);
                radioButton.addOnCheckedChangeListener(
                        checkedChangeListener);

                setFlagRadioButton(radioButton);
            }
            if (!(view instanceof ViewGroup)) {
                return;
            }
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.getChildCount() == 0) {
                return;
            }
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                traverseTree(viewGroup.getChildAt(i));
            }
        }

        public void onChildViewAdded(View parent, View child) {
            traverseTree(child);
            if (parent == RadioGroupExpandable.this && child instanceof RadioButtonExpandable) {
                int id = child.getId();
                if (id == View.NO_ID) {
                    id = generateId();
                    child.setId(id);
                }
                RadioButtonExpandable radioButton = ((RadioButtonExpandable) child);

                setFlagRadioButton(radioButton);
            }

            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewAdded(parent, child);
            }
        }

        public void onChildViewRemoved(View parent, View child) {
            if (parent == RadioGroupExpandable.this && child instanceof RadioButtonExpandable) {
                ((RadioButtonExpandable) child).clearOnCheckedChangeListener();
            }

            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewRemoved(parent, child);
            }
        }
    }

    private int generateId() {
        int id;
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            id = Utils.generateViewId();
        }else{
            id = View.generateViewId();
        }
        return id;
    }

    private void setFlagRadioButton(RadioButtonExpandable radioButton) {
        if (radioButton.isChecked()) {
            protectFromCheckedChange = true;
            if (checkedId != -1) {
                setCheckedStateForView(checkedId, false);
            }
            protectFromCheckedChange = false;
            setCheckedId(radioButton.getId());
        }
    }
}