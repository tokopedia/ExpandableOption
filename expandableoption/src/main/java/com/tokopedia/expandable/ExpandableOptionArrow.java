package com.tokopedia.expandable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

/**
 * Created by zulfikarrahman on 4/6/17.
 */

public class ExpandableOptionArrow extends BaseExpandableOptionText {

    private ImageView imageArrow;
    private Drawable imageUp;
    private Drawable imageDown;
    boolean isUseRotateAnimation;
    private View contentHeader;

    private boolean hasFinishInflate = false;

    public ExpandableOptionArrow(Context context) {
        super(context);
    }

    public ExpandableOptionArrow(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandableOptionArrow(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ExpandableOptionArrow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void init() {
        setHeaderLayoutRes(R.layout.item_expandable_option_arrow_header);
        super.init();
    }

    @Override
    protected void init(AttributeSet attrs) {
        super.init(attrs);
        hasFinishInflate = false;
        TypedArray styledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.BaseExpandableOption);
        try {
            imageUp = styledAttributes.getDrawable(R.styleable.BaseExpandableOption_eo_image_arrow_up);
            imageDown = styledAttributes.getDrawable(R.styleable.BaseExpandableOption_eo_image_arrow_down);
            isUseRotateAnimation = styledAttributes.getBoolean(R.styleable.BaseExpandableOption_eo_use_rotate_animation, false);
        } finally {
            styledAttributes.recycle();
        }

        if (imageDown == null) {
            imageDown = ContextCompat.getDrawable(getContext(), R.drawable.ic_arrow_down);
        }

        if (imageUp == null) {
            imageUp = ContextCompat.getDrawable(getContext(), R.drawable.ic_arrow_up);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentHeader.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEnabled()) {
                    toggle();
                }
            }
        });
        hasFinishInflate = true;
    }

    @Override
    public void setExpand(boolean isExpanded) {
        super.setExpand(isExpanded);
        if (isExpanded) {
            if (isUseRotateAnimation) {
                animateExpand(hasFinishInflate);
            } else {
                imageArrow.setImageDrawable(imageUp);
            }
        } else {
            if (isUseRotateAnimation) {
                animateCollapse(hasFinishInflate);
            } else {
                imageArrow.setImageDrawable(imageDown);
            }
        }
    }

    private void animateExpand(boolean isAnimate) {
        imageArrow.setImageDrawable(imageDown);
        RotateAnimation rotate =
                new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        if (isAnimate) {
            rotate.setDuration(300);
        } else {
            rotate.setDuration(1);
        }
        rotate.setFillAfter(true);
        imageArrow.startAnimation(rotate);
    }

    private void animateCollapse(boolean isAnimate) {
        imageArrow.setImageDrawable(imageDown);
        RotateAnimation rotate =
                new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        if (isAnimate) {
            rotate.setDuration(300);
        } else {
            rotate.setDuration(1);
        }
        rotate.setFillAfter(true);
        imageArrow.startAnimation(rotate);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        imageArrow = (ImageView) view.findViewById(R.id.image_arrow);
        contentHeader = view.findViewById(R.id.content_header_option);
    }

}
