package com.example.logicalgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

/**
 * Created by Greg on 2/24/15.
 * This will hold the lines connecting the nodes.
 */
public class ScreenGameSurface extends View
{
    private Paint mPaint;
    private GridView mGridView;
    public ScreenGameSurface(Context context, AttributeSet attrSet)
    {
        super(context, attrSet);

        //this.mGridView = (GridView)findViewById(R.id.game_gridview);

        this.mPaint = new Paint();
        this.mPaint.setColor(Color.WHITE);
        this.mPaint.setTextSize(48.0f);
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        canvas.drawText("SCORE: 0001", 0, 50, this.mPaint);
    }
}
