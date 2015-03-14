package com.zhy.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.zhy.library.view.R;

public class RoundProgressBarWidthNumber extends
		HorizontalProgressBarWithNumber
{
	/**
	 * mRadius of view
	 */
	private int mRadius = dp2px(30);
	private int mMaxPaintWidth;

	public RoundProgressBarWidthNumber(Context context)
	{
		this(context, null);
	}

	public RoundProgressBarWidthNumber(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		mReachedProgressBarHeight = (int) (mUnReachedProgressBarHeight * 2.5f);
		TypedArray ta = context.obtainStyledAttributes(attrs,
				R.styleable.RoundProgressBarWidthNumber);
		mRadius = (int) ta.getDimension(
				R.styleable.RoundProgressBarWidthNumber_radius, mRadius);
		ta.recycle();

		mPaint.setStyle(Style.STROKE);
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setStrokeCap(Cap.ROUND);

	}

	/**
	 * 这里默认在布局中padding值要么不设置，要么全部设置
	 */
	@Override
	protected synchronized void onMeasure(int widthMeasureSpec,
			int heightMeasureSpec)
	{

		mMaxPaintWidth = Math.max(mReachedProgressBarHeight,
				mUnReachedProgressBarHeight);
		int expect = mRadius * 2 + mMaxPaintWidth + getPaddingLeft()
				+ getPaddingRight();
		int width = resolveSize(expect, widthMeasureSpec);
		int height = resolveSize(expect, heightMeasureSpec);
		int realWidth = Math.min(width, height);

		mRadius = (realWidth - getPaddingLeft() - getPaddingRight() - mMaxPaintWidth) / 2;

		setMeasuredDimension(realWidth, realWidth);

	}

	@Override
	protected synchronized void onDraw(Canvas canvas)
	{

		String text = getProgress() + "%";
		float textWidth = mPaint.measureText(text);
		float textHeight = (mPaint.descent() + mPaint.ascent()) / 2;

		canvas.save();
		canvas.translate(getPaddingLeft() + mMaxPaintWidth / 2, getPaddingTop()
				+ mMaxPaintWidth / 2);
		mPaint.setStyle(Style.STROKE);
		// draw unreaded bar
		mPaint.setColor(mUnReachedBarColor);
		mPaint.setStrokeWidth(mUnReachedProgressBarHeight);
		canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);
		// draw reached bar
		mPaint.setColor(mReachedBarColor);
		mPaint.setStrokeWidth(mReachedProgressBarHeight);
		float sweepAngle = getProgress() * 1.0f / getMax() * 360;
		canvas.drawArc(new RectF(0, 0, mRadius * 2, mRadius * 2), 0,
				sweepAngle, false, mPaint);
		// draw text
		mPaint.setStyle(Style.FILL);
		canvas.drawText(text, mRadius - textWidth / 2, mRadius - textHeight,
				mPaint);

		canvas.restore();

	}

}
