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
 * CenterMenuView
 * Created by zcy on 2017/2/20.
 */

public class CenterMenuView extends BaseView {

    /**
     * the path of this view
     */
    private Path mPath;
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
     * paints
     */
    private Paint mPressedPaint, mTextPaint;

    /**
     * the flag become true when this drawPath be touched
     * else become false
     */
    private boolean touchFlag;

    /**
     * the textColor of this view's center text
     */
    private int mTextColor;

    /**
     * the textSize of this view's center text
     */
    private float mTextSize;

    /**
     * the text of this view
     */
    private String mText;

    /**
     * the listener of this view when it's region be touched
     */
    private OnMenuClickListener mOnMenuClickListener;

    /**
     * the paint's strokeWidth
     */
    private int mStrokeWidth;


    public CenterMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CenterMenuView(Context context) {
        this(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CenterMenuView);
        mDefaultColor = typedArray.getColor(R.styleable.CenterMenuView_color_normal, Color.parseColor("#ffffff"));
        mTouchedColor = typedArray.getColor(R.styleable.CenterMenuView_color_pressed, Color.parseColor("#d0d0d0"));
        mTextColor = typedArray.getColor(R.styleable.CenterMenuView_textColor, Color.parseColor("#000000"));
        mTextSize = typedArray.getDimension(R.styleable.CenterMenuView_textSize, 20);
        mText = typedArray.getString(R.styleable.CenterMenuView_text);
        mStrokeWidth = typedArray.getInt(R.styleable.CenterMenuView_width_stroke, 10);
        typedArray.recycle();
        mPath = new Path();
        mTextPaint = new Paint();
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setAntiAlias(true);
        mRegion = new Region();
        mPressedPaint = new Paint();
        mPressedPaint.setColor(mTouchedColor);
        mPressedPaint.setAntiAlias(true);
        mPressedPaint.setStyle(Paint.Style.FILL);
        mDefaultPaint.setColor(mDefaultColor);
        mDefaultPaint.setAntiAlias(true);
        mDefaultPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Region globalRegion = new Region(0, 0, mViewWidth, mViewHeight);
        RectF rectF = new RectF(
                mViewWidth * 3 / 10 + mStrokeWidth * 3 / 4,
                mViewHeight * 3 / 10 + mStrokeWidth * 3 / 4,
                mViewWidth * 7 / 10 - mStrokeWidth * 3 / 4,
                mViewHeight * 7 / 10 - mStrokeWidth * 3 / 4);
        mPath.addArc(rectF, 0, 359.99999f);
        mRegion.setPath(mPath, globalRegion);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (touchFlag) {
            canvas.drawPath(mPath, mPressedPaint);
        } else {
            canvas.drawPath(mPath, mDefaultPaint);
        }
        int textHeight = (int) (mTextPaint.descent() - mTextPaint.ascent());
        int textWidth = (int) mTextPaint.measureText(mText);
        float textStartX = mViewWidth / 2 - textWidth / 2;
        float textStartY = mViewHeight / 2 + textHeight / 3;
        canvas.drawText(mText, textStartX, textStartY, mTextPaint);
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
                    mOnMenuClickListener.onCenterMenuClick();
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
     * set StrokeWidth
     *
     * @param mStrokeWidth
     */
    public void setStrokeWidth(int mStrokeWidth) {
        this.mStrokeWidth = mStrokeWidth;
        mDefaultPaint.setStrokeWidth(mStrokeWidth);
    }

    /**
     * setDefaultColor
     *
     * @param mDefaultColor
     */
    public void setDefaultColor(int mDefaultColor) {
        this.mDefaultColor = mDefaultColor;
        mDefaultPaint.setColor(mDefaultColor);
    }

    /**
     * set TouchedColor
     *
     * @param mTouchedColor
     */
    public void setTouchedColor(int mTouchedColor) {
        this.mTouchedColor = mTouchedColor;
        mPressedPaint.setColor(mTouchedColor);
    }

    /**
     * set textColor
     *
     * @param mTextColor
     */
    public void setTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
        mTextPaint.setColor(mTextColor);
    }

    /**
     * set textSize
     *
     * @param mTextSize
     */
    public void setTextSize(float mTextSize) {
        this.mTextSize = mTextSize;
        mTextPaint.setTextSize(mTextSize);
    }

    /**
     * set text
     *
     * @param mText
     */
    public void setText(String mText) {
        this.mText = mText;
        invalidate();
    }

    /**
     * set listener
     *
     * @param onMenuClickListener
     */
    public void setOnMenuClickListener(OnMenuClickListener onMenuClickListener) {
        this.mOnMenuClickListener = onMenuClickListener;
    }
}
