package com.zcy.circlemenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by zcy on 2017/2/20.
 */

public class TopMenuView extends BaseView {

    /**
     * the path of this view
     */
    private Path mPath, mInnerPath;
    /**
     * the region of this view
     */
    private Region mRegion;

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
     * the paint of this view when it's region be touched
     */
    private Paint mPressedPaint;

    /**
     * the flag become true when this drawPath be touched
     * else become false
     */
    private boolean touchFlag;

    /**
     * the listener of this view
     */
    private OnMenuClickListener mOnMenuClickListener;

    public TopMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TopMenuView(Context context) {
        this(context, null);
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
     * set listener
     *
     * @param mOnMenuClickListener
     */
    public void setOnMenuClickListener(OnMenuClickListener mOnMenuClickListener) {
        this.mOnMenuClickListener = mOnMenuClickListener;
    }

    private void init(Context context, AttributeSet attrs) {
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MenuView);
        mDefaultColor = typedArray.getColor(R.styleable.MenuView_color_normal, Color.parseColor("#d0d0d0"));
        mTouchedColor = typedArray.getColor(R.styleable.MenuView_color_pressed, Color.parseColor("#d0d0d0"));
        mStrokeWidth = typedArray.getInt(R.styleable.MenuView_width_stroke, 10);
        typedArray.recycle();
        mPath = new Path();
        mInnerPath = new Path();
        mRegion = new Region();
        mPressedPaint = new Paint();
        mPressedPaint.setColor(mTouchedColor);
        mPressedPaint.setAntiAlias(true);
        mPressedPaint.setStyle(Paint.Style.FILL);
        mDefaultPaint.setColor(mTouchedColor);
        mDefaultPaint.setAntiAlias(true);
        mDefaultPaint.setStyle(Paint.Style.STROKE);
        mDefaultPaint.setStrokeWidth(mStrokeWidth);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Region globalRegion = new Region(0, 0, mViewWidth, mViewHeight);
        RectF bigCircle = new RectF(
                mStrokeWidth / 2,
                mStrokeWidth / 2,
                w - mStrokeWidth / 2,
                h - mStrokeWidth / 2);
        RectF smallCircle = new RectF(
                mViewWidth * 3 / 10 + mStrokeWidth / 2,
                mViewHeight * 3 / 10 + mStrokeWidth / 2,
                mViewWidth * 7 / 10 - mStrokeWidth / 2,
                mViewHeight * 7 / 10 - mStrokeWidth / 2);
        mPath.addArc(bigCircle, 225, 90);
        mPath.addArc(smallCircle, 225, 90);
        mInnerPath.addArc(bigCircle, 225, 90);
        mInnerPath.arcTo(smallCircle, 315, -90);
        mInnerPath.close();
        mRegion.setPath(mInnerPath, globalRegion);
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mDefaultPaint);
        if (touchFlag) {
            canvas.drawPath(mInnerPath, mPressedPaint);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchFlag = getTouchedPath(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                touchFlag = getTouchedPath(x, y);
                break;
            case MotionEvent.ACTION_UP:
                touchFlag = getTouchedPath(x, y);
                if (touchFlag && mOnMenuClickListener != null) {
                    mOnMenuClickListener.onTopMenuClick();
                }
                touchFlag = false;
                break;
            case MotionEvent.ACTION_CANCEL:
                touchFlag = false;
                break;
        }
        invalidate();
        return touchFlag;
    }

    private boolean getTouchedPath(int x, int y) {
        if (mRegion.contains(x, y)) {
            return true;
        }
        return false;
    }

    /**
     * set defaultColor
     *
     * @param mDefaultColor
     */
    public void setDefaultColor(int mDefaultColor) {
        this.mDefaultColor = mDefaultColor;
    }

    /**
     * set touchedColor
     *
     * @param mTouchedColor
     */
    public void setTouchedColor(int mTouchedColor) {
        this.mTouchedColor = mTouchedColor;
        mPressedPaint.setColor(mTouchedColor);
        mDefaultPaint.setColor(mTouchedColor);
    }

    /**
     * set strokeWidth
     *
     * @param mStrokeWidth
     */
    public void setStrokeWidth(int mStrokeWidth) {
        this.mStrokeWidth = mStrokeWidth;
        mDefaultPaint.setStrokeWidth(mStrokeWidth);
    }
}
