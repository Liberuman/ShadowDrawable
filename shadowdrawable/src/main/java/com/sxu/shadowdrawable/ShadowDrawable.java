package com.sxu.shadowdrawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.View;

public class ShadowDrawable extends Drawable {

	private Paint mShadowPaint;
	private Paint mBgPaint;
	private int mShadowRadius;
	private int mShape;
	private int mShapeRadius;
	private int mOffsetX;
	private int mOffsetY;
	private int mBgColor[];
	private RectF mRect;

	public final static int SHAPE_ROUND = 1;
	public final static int SHAPE_CIRCLE = 2;

	private ShadowDrawable(int shape, int[] bgColor, int shapeRadius, int shadowColor, int shadowRadius, int offsetX, int offsetY) {
		this.mShape = shape;
		this.mBgColor = bgColor;
		this.mShapeRadius = shapeRadius;
		this.mShadowRadius = shadowRadius;
		this.mOffsetX = offsetX;
		this.mOffsetY = offsetY;

		mShadowPaint = new Paint();
		mShadowPaint.setColor(Color.TRANSPARENT);
		mShadowPaint.setAntiAlias(true);
		mShadowPaint.setShadowLayer(shadowRadius, offsetX, offsetY, shadowColor);
		mShadowPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));

		mBgPaint = new Paint();
		mBgPaint.setAntiAlias(true);
	}

	@Override
	public void setBounds(int left, int top, int right, int bottom) {
		super.setBounds(left, top, right, bottom);
		mRect = new RectF(left + mShadowRadius - mOffsetX, top + mShadowRadius - mOffsetY, right - mShadowRadius - mOffsetX,
				bottom - mShadowRadius - mOffsetY);
	}

	@Override
	public void draw(@NonNull Canvas canvas) {
		if (mBgColor != null) {
			if (mBgColor.length == 1) {
				mBgPaint.setColor(mBgColor[0]);
			} else {
				mBgPaint.setShader(new LinearGradient(mRect.left, mRect.height() / 2, mRect.right,
						mRect.height() / 2, mBgColor, null, Shader.TileMode.CLAMP));
			}
		}

		if (mShape == SHAPE_ROUND) {
			canvas.drawRoundRect(mRect, mShapeRadius, mShapeRadius, mShadowPaint);
			canvas.drawRoundRect(mRect, mShapeRadius, mShapeRadius, mBgPaint);
		} else {
			canvas.drawCircle(mRect.centerX(), mRect.centerY(), Math.min(mRect.width(), mRect.height())/ 2, mShadowPaint);
			canvas.drawCircle(mRect.centerX(), mRect.centerY(), Math.min(mRect.width(), mRect.height())/ 2, mBgPaint);
		}
	}

	@Override
	public void setAlpha(int alpha) {
		mShadowPaint.setAlpha(alpha);
	}

	@Override
	public void setColorFilter(@Nullable ColorFilter colorFilter) {
		mShadowPaint.setColorFilter(colorFilter);
	}

	@Override
	public int getOpacity() {
		return PixelFormat.TRANSLUCENT;
	}

	public static void setShadowDrawable(View view, Drawable drawable) {
		view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		ViewCompat.setBackground(view, drawable);
	}

	/**
	 * 为指定View添加阴影
	 * @param view 目标View
	 * @param shapeRadius View的圆角
	 * @param shadowColor 阴影的颜色
	 * @param shadowRadius 阴影的宽度
	 * @param offsetX 阴影水平方向的偏移量
	 * @param offsetY 阴影垂直方向的偏移量
	 */
	public static void setShadowDrawable(View view, int shapeRadius, int shadowColor, int shadowRadius, int offsetX, int offsetY) {
		ShadowDrawable drawable = new ShadowDrawable.Builder()
				.setShapeRadius(shapeRadius)
				.setShadowColor(shadowColor)
				.setShadowRadius(shadowRadius)
				.setOffsetX(offsetX)
				.setOffsetY(offsetY)
				.builder();
		view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		ViewCompat.setBackground(view, drawable);
	}

	/**
	 * 为指定View设置带阴影的背景
	 * @param view 目标View
	 * @param bgColor View背景色
	 * @param shapeRadius View的圆角
	 * @param shadowColor 阴影的颜色
	 * @param shadowRadius 阴影的宽度
	 * @param offsetX 阴影水平方向的偏移量
	 * @param offsetY 阴影垂直方向的偏移量
	 */
	public static void setShadowDrawable(View view, int bgColor, int shapeRadius, int shadowColor, int shadowRadius, int offsetX, int offsetY) {
		ShadowDrawable drawable = new ShadowDrawable.Builder()
				.setBgColor(bgColor)
				.setShapeRadius(shapeRadius)
				.setShadowColor(shadowColor)
				.setShadowRadius(shadowRadius)
				.setOffsetX(offsetX)
				.setOffsetY(offsetY)
				.builder();
		view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		ViewCompat.setBackground(view, drawable);
	}

	/**
	 * 为指定View设置指定形状并带阴影的背景
	 * @param view 目标View
	 * @param shape View的形状 取值可为：GradientDrawable.RECTANGLE， GradientDrawable.OVAL， GradientDrawable.RING
	 * @param bgColor View背景色
	 * @param shapeRadius View的圆角
	 * @param shadowColor 阴影的颜色
	 * @param shadowRadius 阴影的宽度
	 * @param offsetX 阴影水平方向的偏移量
	 * @param offsetY 阴影垂直方向的偏移量
	 */
	public static void setShadowDrawable(View view, int shape, int bgColor, int shapeRadius, int shadowColor, int shadowRadius, int offsetX, int offsetY) {
		ShadowDrawable drawable = new ShadowDrawable.Builder()
				.setShape(shape)
				.setBgColor(bgColor)
				.setShapeRadius(shapeRadius)
				.setShadowColor(shadowColor)
				.setShadowRadius(shadowRadius)
				.setOffsetX(offsetX)
				.setOffsetY(offsetY)
				.builder();
		view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		ViewCompat.setBackground(view, drawable);
	}

	/**
	 * 为指定View设置带阴影的渐变背景
	 * @param view
	 * @param bgColor
	 * @param shapeRadius
	 * @param shadowColor
	 * @param shadowRadius
	 * @param offsetX
	 * @param offsetY
	 */
	public static void setShadowDrawable(View view, int[] bgColor, int shapeRadius, int shadowColor, int shadowRadius, int offsetX, int offsetY) {
		ShadowDrawable drawable = new ShadowDrawable.Builder()
				.setBgColor(bgColor)
				.setShapeRadius(shapeRadius)
				.setShadowColor(shadowColor)
				.setShadowRadius(shadowRadius)
				.setOffsetX(offsetX)
				.setOffsetY(offsetY)
				.builder();
		view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		ViewCompat.setBackground(view, drawable);
	}

	public static class Builder {
		private int mShape;
		private int mShapeRadius;
		private int mShadowColor;
		private int mShadowRadius;
		private int mOffsetX;
		private int mOffsetY;
		private int[] mBgColor;

		public Builder() {
			mShape = ShadowDrawable.SHAPE_ROUND;
			mShapeRadius = 12;
			mShadowColor = Color.parseColor("#4d000000");
			mShadowRadius = 18;
			mOffsetX = 0;
			mOffsetY = 0;
			mBgColor = new int[1];
			mBgColor[0] = Color.TRANSPARENT;
		}

		public Builder setShape(int mShape) {
			this.mShape = mShape;
			return this;
		}

		public Builder setShapeRadius(int ShapeRadius) {
			this.mShapeRadius = ShapeRadius;
			return this;
		}

		public Builder setShadowColor(int shadowColor) {
			this.mShadowColor = shadowColor;
			return this;
		}

		public Builder setShadowRadius(int shadowRadius) {
			this.mShadowRadius = shadowRadius;
			return this;
		}

		public Builder setOffsetX(int OffsetX) {
			this.mOffsetX = OffsetX;
			return this;
		}

		public Builder setOffsetY(int OffsetY) {
			this.mOffsetY = OffsetY;
			return this;
		}

		public Builder setBgColor(int BgColor) {
			this.mBgColor[0] = BgColor;
			return this;
		}

		public Builder setBgColor(int[] BgColor) {
			this.mBgColor = BgColor;
			return this;
		}

		public ShadowDrawable builder() {
			return new ShadowDrawable(mShape, mBgColor, mShapeRadius, mShadowColor, mShadowRadius, mOffsetX, mOffsetY);
		}
	}
}