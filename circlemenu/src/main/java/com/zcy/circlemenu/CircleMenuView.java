package com.zcy.circlemenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * CircleMenuView
 * Created by zcy on 2017/2/21.
 */

public class CircleMenuView extends RelativeLayout {

    /**
     * the leftMenuView
     */
    private LeftMenuView mLeftMenuView;
    /**
     * the topMenuView
     */
    private TopMenuView mTopMenuView;
    /**
     * the rightMenuView
     */
    private RightMenuView mRightMenuView;
    /**
     * the bottomMenuView
     */
    private BottomMenuView mBottomMenuView;
    /**
     * the centerMenuView
     */
    private CenterMenuView mCenterMenuView;
    /**
     * the arrowViews
     */
    private ImageView mLeftArrow, mTopArrow, mRightArrow, mBottomArrow;
    /**
     * the drawables of arrowViews
     */
    private Drawable mLeftDrawable, mTopDrawable, mRightDrawable, mBottomDrawable;
    /**
     * the width and height of the viewGroup
     * when this viewGroup's size changed,
     * width and height will change too
     */
    private int mWidth = 300, mHeight = 300;
    /**
     * the  default color of background
     */
    private int mDefaultColor;

    /**
     * the color  changed when this view been touched;
     */
    private int mTouchedColor;

    /**
     * the strokeWidth of the Paint
     */
    private int mStrokeWidth;

    /**
     * the textColor of centerMenuView
     * this viewGroup's childView
     */
    private int mTextColor;

    /**
     * the textSize of centerMenuView
     * this viewGroup's childView
     */
    private float mTextSize;

    /**
     * the text of centerMenuView
     * this viewGroup's childView
     */
    private String mText;

    /**
     * the listener
     */
    private BaseView.OnMenuClickListener mOnMenuClickListener;

    public CircleMenuView(Context context) {
        this(context, null);
    }

    /**
     * set listener
     *
     * @param listener
     */
    public void setOnMenuClickListener(BaseView.OnMenuClickListener listener) {
        this.mOnMenuClickListener = listener;
        if (mOnMenuClickListener == null)
            return;
        if (mLeftMenuView != null) {
            mLeftMenuView.setOnMenuClickListener(mOnMenuClickListener);
        }
        if (mTopMenuView != null) {
            mTopMenuView.setOnMenuClickListener(mOnMenuClickListener);
        }
        if (mRightMenuView != null) {
            mRightMenuView.setOnMenuClickListener(mOnMenuClickListener);
        }
        if (mBottomMenuView != null) {
            mBottomMenuView.setOnMenuClickListener(mOnMenuClickListener);
        }
        if (mCenterMenuView != null) {
            mCenterMenuView.setOnMenuClickListener(mOnMenuClickListener);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        post(new Runnable() {
            @Override
            public void run() {
                updateMargins();
            }
        });
    }


    private void updateLayoutParams() {
        LayoutParams params = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        mLeftMenuView.setLayoutParams(params);
        mTopMenuView.setLayoutParams(params);
        mRightMenuView.setLayoutParams(params);
        mBottomMenuView.setLayoutParams(params);
        mCenterMenuView.setLayoutParams(params);
    }

    public CircleMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        obtainAttributes(context, attrs);
        initView(context);
    }

    private void obtainAttributes(Context context, AttributeSet attrs) {
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleMenuView);
        mLeftDrawable = typedArray.getDrawable(R.styleable.CircleMenuView_arrow_left);
        mTopDrawable = typedArray.getDrawable(R.styleable.CircleMenuView_arrow_top);
        mRightDrawable = typedArray.getDrawable(R.styleable.CircleMenuView_arrow_right);
        mBottomDrawable = typedArray.getDrawable(R.styleable.CircleMenuView_arrow_bottom);
        mDefaultColor = typedArray.getColor(R.styleable.CircleMenuView_color_normal, Color.parseColor("#ffffff"));
        mTouchedColor = typedArray.getColor(R.styleable.CircleMenuView_color_pressed, Color.parseColor("#d0d0d0"));
        mStrokeWidth = typedArray.getInt(R.styleable.CircleMenuView_width_stroke, 8);
        mTextColor = typedArray.getColor(R.styleable.CircleMenuView_textColor, Color.parseColor("#000000"));
        mTextSize = typedArray.getDimension(R.styleable.CircleMenuView_textSize, 20);
        mText = typedArray.getString(R.styleable.CircleMenuView_text);
        typedArray.recycle();
    }

    private void initView(Context context) {
        initArrowView(context);
        initMenuView(context);
        addAllViews();
    }

    private void addAllViews() {
        addView(mLeftMenuView);
        addView(mTopMenuView);
        addView(mRightMenuView);
        addView(mBottomMenuView);
        addView(mLeftArrow);
        addView(mTopArrow);
        addView(mRightArrow);
        addView(mBottomArrow);
        addView(mCenterMenuView);
    }

    private void initMenuView(Context context) {
        mLeftMenuView = new LeftMenuView(context);
        mTopMenuView = new TopMenuView(context);
        mRightMenuView = new RightMenuView(context);
        mBottomMenuView = new BottomMenuView(context);
        mCenterMenuView = new CenterMenuView(context);
        updateLayoutParams();
        updateDefaultColor();
        updateTouchedColor();
        updateStrokeWidth();
        updateTextSize();
        updateText();
        updateTextColor();
    }

    private void updateTextColor() {
        mCenterMenuView.setTextColor(mTextColor);
    }

    private void updateText() {
        mCenterMenuView.setText(mText);
    }

    private void updateTextSize() {
        mCenterMenuView.setTextSize(mTextSize);
    }

    private void updateStrokeWidth() {
        mLeftMenuView.setStrokeWidth(mStrokeWidth);
        mTopMenuView.setStrokeWidth(mStrokeWidth);
        mRightMenuView.setStrokeWidth(mStrokeWidth);
        mBottomMenuView.setStrokeWidth(mStrokeWidth);
        mCenterMenuView.setStrokeWidth(mStrokeWidth + 2);
    }

    private void updateTouchedColor() {
        mLeftMenuView.setTouchedColor(mTouchedColor);
        mTopMenuView.setTouchedColor(mTouchedColor);
        mRightMenuView.setTouchedColor(mTouchedColor);
        mBottomMenuView.setTouchedColor(mTouchedColor);
        mCenterMenuView.setTouchedColor(mTouchedColor);
    }

    private void updateDefaultColor() {
        mLeftMenuView.setDefaultColor(mDefaultColor);
        mTopMenuView.setDefaultColor(mDefaultColor);
        mRightMenuView.setDefaultColor(mDefaultColor);
        mBottomMenuView.setDefaultColor(mDefaultColor);
        mCenterMenuView.setDefaultColor(mDefaultColor);
    }

    private void initArrowView(Context context) {
        mLeftArrow = new ImageView(context);
        mTopArrow = new ImageView(context);
        mRightArrow = new ImageView(context);
        mBottomArrow = new ImageView(context);
        mLeftArrow.setImageDrawable(mLeftDrawable);
        mTopArrow.setImageDrawable(mTopDrawable);
        mRightArrow.setImageDrawable(mRightDrawable);
        mBottomArrow.setImageDrawable(mBottomDrawable);
        mLeftArrow.setClickable(false);
        mTopArrow.setClickable(false);
        mRightArrow.setClickable(false);
        mBottomArrow.setClickable(false);
        updateMargins();
    }


    private void updateMargins() {
        LayoutParams leftParams = new LayoutParams(
                mWidth / 8,
                mHeight / 8);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        leftParams.addRule(RelativeLayout.CENTER_VERTICAL);
        leftParams.leftMargin = mWidth / 11;
        mLeftArrow.setLayoutParams(leftParams);
        LayoutParams topParams = new LayoutParams(
                mWidth / 8,
                mHeight / 8);
        topParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        topParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        topParams.topMargin = mHeight / 11;
        mTopArrow.setLayoutParams(topParams);
        LayoutParams rightParams = new LayoutParams(
                mWidth / 8,
                mHeight / 8);
        rightParams.addRule(RelativeLayout.CENTER_VERTICAL);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rightParams.rightMargin = mWidth / 11;
        mRightArrow.setLayoutParams(rightParams);
        LayoutParams bottomParams = new LayoutParams(
                mWidth / 8,
                mHeight / 8);
        bottomParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        bottomParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        bottomParams.bottomMargin = mHeight / 11;
        mBottomArrow.setLayoutParams(bottomParams);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (widthMeasureSpec < heightMeasureSpec) {
            heightMeasureSpec = widthMeasureSpec;
        } else {
            widthMeasureSpec = heightMeasureSpec;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * set defaultColor
     *
     * @param mDefaultColor
     */
    public void setDefaultColor(int mDefaultColor) {
        this.mDefaultColor = mDefaultColor;
        updateDefaultColor();
    }

    /**
     * set touchedColor
     *
     * @param mTouchedColor
     */
    public void setTouchedColor(int mTouchedColor) {
        this.mTouchedColor = mTouchedColor;
        updateTouchedColor();
    }

    /**
     * set strokeWidth
     *
     * @param mStrokeWidth
     */
    public void setStrokeWidth(int mStrokeWidth) {
        this.mStrokeWidth = mStrokeWidth;
        updateStrokeWidth();
    }

    /**
     * set textColor
     *
     * @param mTextColor
     */
    public void setTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
        updateTextColor();
    }

    /**
     * set textSize
     *
     * @param mTextSize
     */
    public void setTextSize(float mTextSize) {
        this.mTextSize = mTextSize;
        updateTextSize();
    }

    /**
     * set text
     *
     * @param mText
     */
    public void setText(String mText) {
        this.mText = mText;
        updateText();
    }

    /**
     * set bottomDrawable
     *
     * @param mBottomDrawable
     */
    public void setBottomDrawable(Drawable mBottomDrawable) {
        this.mBottomDrawable = mBottomDrawable;
        mBottomArrow.setImageDrawable(mBottomDrawable);
    }

    /**
     * set rightDrawable
     *
     * @param mRightDrawable
     */
    public void setRightDrawable(Drawable mRightDrawable) {
        this.mRightDrawable = mRightDrawable;
        mRightArrow.setImageDrawable(mRightDrawable);
    }

    /**
     * setLeftDrawable
     *
     * @param mLeftDrawable
     */
    public void setLeftDrawable(Drawable mLeftDrawable) {
        this.mLeftDrawable = mLeftDrawable;
        mLeftArrow.setImageDrawable(mLeftDrawable);
    }

    /**
     * set topDrawable
     *
     * @param mTopDrawable
     */
    public void setTopDrawable(Drawable mTopDrawable) {
        this.mTopDrawable = mTopDrawable;
        mTopArrow.setImageDrawable(mTopDrawable);
    }
}
