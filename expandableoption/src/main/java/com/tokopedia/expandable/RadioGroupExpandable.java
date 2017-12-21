package com.tokopedia.expandable;

/**
 * Created by zulfikarrahman on 2/22/17.
 */

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class RadioGroupExpandable extends LinearLayout
        implements ExpandableOptionRadio.OnRadioCheckChangedListener {

    private int checkedId = -1;
    private OnCheckedChangeListener onCheckedChangeListener;

    public RadioGroupExpandable(Context context) {
        super(context);
        setOrientation(VERTICAL);
        init();
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(RadioGroupExpandable group, @IdRes int checkedId);
    }

    public RadioGroupExpandable(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (checkedId != -1) {
            setCheckedStateForView(checkedId, true);
            setCheckedId(checkedId);
        }
    }


    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (child instanceof ExpandableOptionRadio) {
            ((ExpandableOptionRadio) child).setOnRadioCheckChangedListener(this);
            if (((ExpandableOptionRadio) child).isExpanded()) {
                checkedId = child.getId();
            }
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

    /**
     * make the view with viewId checked/not checked
     *
     * @param viewId  Res viewId
     * @param checked true/false
     */
    private void setCheckedStateForView(int viewId, boolean checked) {
        View checkedView = findViewById(viewId);
        if (checkedView != null && checkedView instanceof ExpandableOptionRadio) {
            ((ExpandableOptionRadio) checkedView).setExpand(checked);
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

    /**
     * When the radio button on child changed.
     *
     * @param expandableOptionRadio radio that changes.
     * @param id                       id of child radio button that changed
     * @param isChecked                should be true
     */
    @Override
    public void onCheckChangedListener(BaseExpandableOptionRadio expandableOptionRadio,
                                       int id, boolean isChecked) {
        check(id);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.childrenStates = new SparseArray();
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).saveHierarchyState(ss.childrenStates);
        }
        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
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