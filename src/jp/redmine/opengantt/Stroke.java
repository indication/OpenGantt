/**
 *
 */
package jp.redmine.opengantt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class Stroke extends View {
	static final String TAG = "Stroke";
	private Paint mPaintFill;
	private Paint mPaintStroke;
	private Path mPathBackground;
	private Path mPathForeground;
	private int Max = 100;
	private int Progress = 0;
	public interface OnSizeChanged{
		public boolean onBeforeSizeChange();
		public boolean onAfterSizeChange();
	}


	/**
	 * @param context
	 */
	public Stroke(Context context) {
		super(context);
		setup();
		setFocusable(true);

	}

	/**
	 * @param context
	 * @param attrs
	 */
	public Stroke(Context context, AttributeSet attrs) {
		super(context, attrs);
		setup();
		if(attrs.getAttributeValue(android.R.attr.focusable) == null)
			setFocusable(true);
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public Stroke(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setup();
		if(attrs.getAttributeValue(android.R.attr.focusable) == null)
			setFocusable(true);
	}
	protected void setup(){
		mPaintFill = new Paint();
		mPaintFill.setStyle(Paint.Style.FILL);
		mPaintFill.setColor(Color.GRAY);
		mPaintFill.setAlpha(75);
		mPaintFill.setStrokeWidth(1F);

		mPaintStroke = new Paint();
		mPaintStroke.setAntiAlias(true);
		mPaintStroke.setStyle(Paint.Style.STROKE);
		mPaintStroke.setColor(Color.GRAY);
		mPaintStroke.setStrokeWidth(1F);

		mPathBackground = new Path();
		mPathForeground = new Path();
	}

	protected void setStroke(){
		RectF r = new RectF();
		r.bottom = getBottom();
		r.top = getTop();
		r.left = getLeft();
		r.right = getRight();

		mPathBackground.reset();
		mPathBackground.addRect(r, Direction.CW);

		requestLayout();
		invalidate();

	}

	protected void setArrow(){
		float h = getBottom() - getTop();
		float x = getLeft();
		float y = getTop();
		mPathBackground.reset();
		mPathBackground.moveTo(x, y);
		y += h;
		x += h/2;
		mPathBackground.lineTo(x, y);
		y += h/2;
		x += h/4;
		mPathBackground.lineTo(x, y);
		x = getRight() - ((3 * h)/4);
		mPathBackground.lineTo(x, y);
		y = getBottom();
		x = getRight() - (h / 2);
		mPathBackground.lineTo(x, y);
		mPathBackground.lineTo(getRight(), getTop());
		mPathBackground.lineTo(getLeft(), getTop());
		mPathBackground.close();


		requestLayout();
		invalidate();
	}

	/**
	 * 画面サイズ変更時の通知
	 * @param w, h, oldw, oldh
	 */
	protected void onSizeChanged(int w, int h, int oldw, int oldh){
		Log.v("View", "onSizeChanged Width:" + w + ",Height:" + h );
		requestLayout();
		invalidate();
	}

	/*
	public boolean onTouchEvent(MotionEvent event) {
		//float x = event.getX();
		//float y = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			//touch_start(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			//touch_move(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			//touch_up();
			invalidate();
			break;
		}
		return true;
	}
	*/
	/* 描画関数 */
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawPath(mPathBackground, mPaintFill);
		canvas.drawPath(mPathBackground, mPaintStroke);
		super.onDraw(canvas);
	}

}
