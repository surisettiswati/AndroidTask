package com.example.androidtask.APIs.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatImageView;

import com.example.androidtask.R;


public class RoundishImageView extends AppCompatImageView {

    private static final int CORNER_NONE = 0;
    private static final int CORNER_TOP_LEFT = 1;
    private static final int CORNER_TOP_RIGHT = 2;
    private static final int CORNER_BOTTOM_RIGHT = 4;
    private static final int CORNER_BOTTOM_LEFT = 8;

    private final RectF cornerRect = new RectF();
    private final Path path = new Path();
    private final int cornerRadius;
    private final int roundedCorners;

    public RoundishImageView(Context context) {
        this(context, null);
    }

    public RoundishImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundishImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundishImageView);
        cornerRadius = a.getDimensionPixelSize(R.styleable.RoundishImageView_cornerRadius, 0);
        roundedCorners = a.getInt(R.styleable.RoundishImageView_roundedCorners, CORNER_NONE);
        a.recycle();
    }





    @Override
    protected void onSizeChanged(int w, int h, int oldwidth, int oldheight) {
        super.onSizeChanged(w, h, oldwidth, oldheight);
        setPath();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!path.isEmpty()) {
            canvas.clipPath(path);
        }
        super.onDraw(canvas);
    }

    private void setPath() {
        path.rewind();

        if (cornerRadius >= 1f && roundedCorners != CORNER_NONE) {
            final int width = getWidth();
            final int height = getHeight();
            final float twoRadius = cornerRadius * 2;
            cornerRect.set(-cornerRadius, -cornerRadius, cornerRadius, cornerRadius);

            if (isRounded(CORNER_TOP_LEFT)) {
                cornerRect.offsetTo(0f, 0f);
                path.arcTo(cornerRect, 180f, 90f);
            } else {
                path.moveTo(0f, 0f);
            }

            if (isRounded(CORNER_TOP_RIGHT)) {
                cornerRect.offsetTo(width - twoRadius, 0f);
                path.arcTo(cornerRect, 270f, 90f);
            } else {
                path.lineTo(width, 0f);
            }

            if (isRounded(CORNER_BOTTOM_RIGHT)) {
                cornerRect.offsetTo(width - twoRadius, height - twoRadius);
                path.arcTo(cornerRect, 0f, 90f);
            } else {
                path.lineTo(width, height);
            }

            if (isRounded(CORNER_BOTTOM_LEFT)) {
                cornerRect.offsetTo(0f, height - twoRadius);
                path.arcTo(cornerRect, 90f, 90f);
            } else {
                path.lineTo(0f, height);
            }

            path.close();
        }
    }

    private boolean isRounded(int corner) {
        Log.d("isRounded", "corner=" + corner);
        Log.d("isRounded", "roundedCorners=" + roundedCorners);
        Log.d("isRounded", "(roundedCorners & corner)=" + (roundedCorners & corner));
        return (roundedCorners & corner) == corner;
    }
}