package com.example.xzkj123.recyclerviewtest.gallery;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by xzkj123 on 2017/7/21.
 */

public class CopyOfMyRecyclerView extends RecyclerView {

    public CopyOfMyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private View mCurrentView;

    /**
     * 滚动时回调的接口
     */
    private OnItemScrollChangeListener mItemScrollChangeListener;

    public void setOnItemScrollChangeListener(
            OnItemScrollChangeListener mItemScrollChangeListener)
    {
        this.mItemScrollChangeListener = mItemScrollChangeListener;
    }

    public interface OnItemScrollChangeListener
    {
        void onChange(View view, int position);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        super.onLayout(changed, l, t, r, b);

        mCurrentView = getChildAt(0);

        if (mItemScrollChangeListener != null)
        {
            mItemScrollChangeListener.onChange(mCurrentView,
                    getChildPosition(mCurrentView));
        }
    }

   /* @Override
    public boolean onTouchEvent(MotionEvent e)
    {

        if (e.getAction() == MotionEvent.ACTION_MOVE)
        {
            mCurrentView = getChildAt(0);
            // Log.e("TAG", getChildPosition(getChildAt(0)) + "");
            if (mItemScrollChangeListener != null)
            {
                mItemScrollChangeListener.onChange(mCurrentView,
                        getChildPosition(mCurrentView));

            }

        }

        return super.onTouchEvent(e);
    }*/

    @Override
    public void onScrollStateChanged(int arg0)
    {
    }

    /**
     *
     * 滚动时，判断当前第一个View是否发生变化，发生才回调
     */
    @Override
    public void onScrolled(int arg0, int arg1)
    {
        View newView = getChildAt(0);

        if (mItemScrollChangeListener != null)
        {
            if (newView != null && newView != mCurrentView)
            {
                mCurrentView = newView ;
                mItemScrollChangeListener.onChange(mCurrentView,
                        getChildPosition(mCurrentView));

            }
        }

    }
}
