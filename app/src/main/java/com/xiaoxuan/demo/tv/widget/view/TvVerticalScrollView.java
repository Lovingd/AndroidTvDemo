package com.xiaoxuan.demo.tv.widget.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.xiaoxuan.demo.androidtvdemoxx.R;

public class TvVerticalScrollView extends ScrollView
{
    
    public TvVerticalScrollView(Context context)
    {
        super(context, null, 0);
    }
    
    public TvVerticalScrollView(Context context, AttributeSet attrs)
    {
        super(context, attrs, 0);
    }
    
    public TvVerticalScrollView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }
    
    @Override
    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect)
    {
        if (getChildCount() == 0)
            return 0;
        int height = getHeight();
        int screenTop = getScrollY();
        int screenBottom = screenTop + height;
        int fadingEdge = this.getResources().getDimensionPixelSize(R.dimen.fading_edge);
        if (rect.top > 0)
        {
            screenTop += fadingEdge;
        }
        if (rect.bottom < getChildAt(0).getHeight())
        {
            screenBottom -= fadingEdge;
        }
        int scrollYDelta = 0;
        if (rect.bottom > screenBottom && rect.top > screenTop)
        {
            if (rect.height() > height)
            {
                scrollYDelta += (rect.top - screenTop);
            }
            else
            {
                scrollYDelta += (rect.bottom - screenBottom);
            }
            int bottom = getChildAt(0).getBottom();
            int distanceToBottom = bottom - screenBottom;
            scrollYDelta = Math.min(scrollYDelta, distanceToBottom);
        }
        else if (rect.top < screenTop && rect.bottom < screenBottom)
        {
            if (rect.height() > height)
            {
                scrollYDelta -= (screenBottom - rect.bottom);
            }
            else
            {
                scrollYDelta -= (screenTop - rect.top);
            }
            scrollYDelta = Math.max(scrollYDelta, -getScrollY());
        }
        return scrollYDelta;
    }
    
}
