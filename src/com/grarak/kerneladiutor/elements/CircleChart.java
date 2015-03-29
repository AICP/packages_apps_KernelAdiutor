/*
 * Copyright (C) 2015 Willi Ye
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.grarak.kerneladiutor.elements;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.grarak.kerneladiutor.R;

/**
 * Created by willi on 05.02.15.
 */
public class CircleChart extends View {

    private int mProgress = 0;
    private int mMax = 100;
    private final Paint mPaintCircle;
    private final Paint mPaintBackground;
    private final RectF mRectF;
    private final int mCircleColor;
    private final float density;

    public CircleChart(Context context) {
        this(context, null);
    }

    public CircleChart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mCircleColor = getResources().getColor(R.color.circlebar_text);
        density = getResources().getDisplayMetrics().density;

        mPaintBackground = new Paint();
        mPaintBackground.setStrokeWidth(Math.round(1 * density));
        mPaintBackground.setAntiAlias(true);
        mPaintBackground.setStyle(Paint.Style.STROKE);
        mPaintBackground.setColor(getResources().getColor(R.color.circlebar_background));
        mPaintBackground.setStrokeCap(Paint.Cap.ROUND);

        mPaintCircle = new Paint();
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setStyle(Paint.Style.STROKE);
        mPaintCircle.setStrokeWidth(Math.round(1 * density));
        mPaintCircle.setStrokeCap(Paint.Cap.ROUND);
        mPaintCircle.setColor(mCircleColor);

        mRectF = new RectF();
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        super.draw(canvas);

        draw(canvas, getWidth() - 10, getHeight() - 10);
    }

    private void draw(Canvas canvas, int x, int y) {
        int padding = Math.round(5 * density);
        mRectF.set(padding, padding, x - padding, y - padding);
        canvas.drawArc(mRectF, 0, 360, false, mPaintBackground);
        float offset = 360 / (float) mMax;
        canvas.drawArc(mRectF, 270, offset * mProgress, false, mPaintCircle);

        TextPaint textPaint = new TextPaint();
        textPaint.setColor(mCircleColor);
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(Math.round(20 * density));
        float textHeight = textPaint.descent() - textPaint.ascent();
        float textOffset = (textHeight / 2) - textPaint.descent();

        RectF bounds = new RectF(padding, padding, x - padding, y - padding);
        String text = String.valueOf(mProgress);
        canvas.drawText(text, bounds.centerX(), bounds.centerY() + textOffset, textPaint);
    }

    public void setProgress(int progress) {
        mProgress = progress;
        invalidate();
    }

    public void setMax(int max) {
        mMax = max;
    }

}
