/**
 *
 */
package jp.redmine.opengantt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author dai
 *
 */
public class Chart extends ViewGroup {

	private Bitmap mBitmap;
	private Canvas mCanvas;
	private Path mPath;
	private Paint mPaint;


	/**
	 * @param context
	 */
	public Chart(Context context) {
		super(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public Chart(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public Chart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	protected void initView(){
		setFocusable(true);
	}

    /**
     * 画面サイズ変更時の通知
     * @param w, h, oldw, oldh
     */
	protected void onSizeChanged(int w, int h, int oldw, int oldh){
		Log.v("View", "onSizeChanged Width:" + w + ",Height:" + h );


	}



	/* 描画関数 */
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(mBitmap, 0, 0, mPaint);
		canvas.drawPath(mPath, mPaint);
	}

	@Override
	protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
