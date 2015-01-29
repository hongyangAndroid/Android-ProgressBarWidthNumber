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
		HorizontalProgressBarWithNumber {
	/**
	 * mRadius of view
	 */
	private int mRadius = dp2px(30);

	public RoundProgressBarWidthNumber(Context context) {
		this(context, null);
	}

	public RoundProgressBarWidthNumber(Context context, AttributeSet attrs) {
		super(context, attrs);

		mReachedProgressBarHeight = (int) (mUnReachedProgressBarHeight * 2.5f);
		TypedArray ta = context.obtainStyledAttributes(attrs,
				R.styleable.RoundProgressBarWidthNumber);
		mRadius = (int) ta.getDimension(
				R.styleable.RoundProgressBarWidthNumber_radius, mRadius);
		ta.recycle();

		mTextSize = sp2px(14);

		mPaint.setStyle(Style.STROKE);
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setStrokeCap(Cap.ROUND);

	}

	@Override
	protected synchronized void onMeasure(int widthMeasureSpec,
			int heightMeasureSpec) {
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);

		int paintWidth = Math.max(mReachedProgressBarHeight,
				mUnReachedProgressBarHeight);

		if (heightMode != MeasureSpec.EXACTLY) {

			int exceptHeight = (int) (getPaddingTop() + getPaddingBottom()
					+ mRadius * 2 + paintWidth);
			heightMeasureSpec = MeasureSpec.makeMeasureSpec(exceptHeight,
					MeasureSpec.EXACTLY);
		}
		if (widthMode != MeasureSpec.EXACTLY) {
			int exceptWidth = (int) (getPaddingLeft() + getPaddingRight()
					+ mRadius * 2 + paintWidth);
			widthMeasureSpec = MeasureSpec.makeMeasureSpec(exceptWidth,
					MeasureSpec.EXACTLY);
		}

		super.onMeasure(heightMeasureSpec, heightMeasureSpec);

	}

	@Override
	protected synchronized void onDraw(Canvas canvas) {

		String text = getProgress() + "%";
		// mPaint.getTextBounds(text, 0, text.length(), mTextBound);
		float textWidth = mPaint.measureText(text);
		float textHeight = (mPaint.descent() + mPaint.ascent()) / 2;

		canvas.save();
		canvas.translate(getPaddingLeft(), getPaddingTop());
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
