package me.leefeng.ios_loadingview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.lang.ref.SoftReference;

/**
 * Created by limxing on 16/1/7.
 */
public class LoadingView extends ImageView {
    private MyRunable runnable;
    private int width;
    private int height;
    public LoadingView(Context context) {
        super(context);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopLoad();
        runnable = null;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        init();

    }

    private void init() {
        setScaleType(ScaleType.MATRIX);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.loading);
        setImageBitmap(bitmap);
        width = bitmap.getWidth() / 2;
        height = bitmap.getHeight() / 2;
        runnable = new MyRunable(this);
        startLoad();
    }

    public void startLoad() {
        if (runnable != null) {
            runnable.startload();
        }
    }

    public void stopLoad() {
        if (runnable != null) {
            runnable.stopload();
        }
    }

    static class MyRunable implements Runnable {
        private boolean flag;
        private SoftReference<LoadingView> loadingViewSoftReference;
        private float degrees = 0f;
        private Matrix max;

        public MyRunable(LoadingView loadingView){
            loadingViewSoftReference=new SoftReference<LoadingView>(loadingView);
            max = new Matrix();
        }

        @Override
        public void run() {
            if (loadingViewSoftReference.get().runnable != null && max != null) {
                degrees += 30f;
                max.setRotate(degrees,  loadingViewSoftReference.get().width,  loadingViewSoftReference.get().height);
                loadingViewSoftReference.get().setImageMatrix(max);
                if (degrees == 360) {
                    degrees = 0;
                }
                if (flag) {
                    loadingViewSoftReference.get().postDelayed(loadingViewSoftReference.get().runnable, 80);
                }
            }
        }

        public void stopload() {
            flag = false;
        }

        public void startload() {
            flag = true;
            if (!flag&&loadingViewSoftReference.get().runnable != null && max != null) {
                loadingViewSoftReference.get().postDelayed(loadingViewSoftReference.get().runnable, 80);
            }
        }
    }
}