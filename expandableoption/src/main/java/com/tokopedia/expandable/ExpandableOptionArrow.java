package com.tokopedia.expandable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by zulfikarrahman on 4/6/17.
 */

public class ExpandableOptionArrow extends BaseExpandableOptionText {

    private ImageView imageArrow;
    private Drawable imageUp;
    private Drawable imageDown;
    private View contentHeader;

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
    protected int getLayoutRes() {
        return R.layout.item_expandable_option_arrow;
    }

    @Override
    protected void init(AttributeSet attrs) {
        super.init(attrs);
        TypedArray styledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.ExpandableOption);
        try {
            imageUp = styledAttributes.getDrawable(R.styleable.ExpandableOption_eo_image_arrow_up);
            imageDown = styledAttributes.getDrawable(R.styleable.ExpandableOption_eo_image_arrow_down);
        } finally {
            styledAttributes.recycle();
        }

        if(imageUp == null){
            imageUp = ContextCompat.getDrawable(getContext(),R.drawable.ic_arrow_up);
        }

        if(imageDown == null){
            imageDown = ContextCompat.getDrawable(getContext(), R.drawable.ic_arrow_down);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentHeader.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(optionEnabled) {
                    toggle();
                }
            }
        });
    }

    @Override
    public void setVisibleChildView(boolean b) {
        super.setVisibleChildView(b);
        if(b){
            imageArrow.setImageDrawable(imageUp);
        }else{
            imageArrow.setImageDrawable(imageDown);
        }
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        imageArrow = (ImageView) view.findViewById(R.id.image_arrow);
        contentHeader = view.findViewById(R.id.content_header_option);
    }

}
