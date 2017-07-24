package com.example.xzkj123.recyclerviewtest.animator;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import it.gmariotti.recyclerview.itemanimator.BaseItemAnimator;

/**
 * Created by xzkj123 on 2017/7/21.
 */

public class SlideInOutLeftItemAnimator extends BaseItemAnimator {
    public SlideInOutLeftItemAnimator(RecyclerView recyclerView) {
        super(recyclerView);
    }

    @Override
    protected void prepareAnimateAdd(final RecyclerView.ViewHolder holder) {
        ViewCompat.setTranslationX(holder.itemView, -mRecyclerView.getLayoutManager().getWidth());

    }

    @Override
    protected void animateAddImpl(final RecyclerView.ViewHolder holder) {
        final View view = holder.itemView;
        final ViewPropertyAnimatorCompat animation = ViewCompat.animate(view);
        mAddAnimations.add(holder);
        animation.translationX(0)
                .alpha(1)
                .setDuration(getAddDuration())
                .setListener(new VpaListenerAdapter() {
                    @Override
                    public void onAnimationStart(View view) {
                        dispatchAddStarting(holder);
                    }

                    @Override
                    public void onAnimationCancel(View view) {
                        ViewCompat.setTranslationX(view, 0);
                        ViewCompat.setAlpha(view, 1);
                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        animation.setListener(null);
                        ViewCompat.setTranslationX(view, 0);
                        ViewCompat.setAlpha(view, 1);
                        dispatchAddFinished(holder);
                        mAddAnimations.remove(holder);
                        dispatchFinishedWhenDone();
                    }
                }).start();
    }

    @Override
    protected void animateRemoveImpl(final RecyclerView.ViewHolder holder) {
        final View view = holder.itemView;
        final ViewPropertyAnimatorCompat animator = ViewCompat.animate(view);
        mRemoveAnimations.add(holder);
        animator
                .setDuration(getRemoveDuration())
                .alpha(0)
                .translationX(-mRecyclerView.getLayoutManager().getWidth())
                .setListener(new VpaListenerAdapter() {

                    @Override
                    public void onAnimationStart(View view) {

                        animator.setListener(null);
                        ViewCompat.setAlpha(view ,1);
                        ViewCompat.setTranslationX(view ,-mRecyclerView.getLayoutManager().getWidth());
                        dispatchRemoveFinished(holder);
                        mRemoveAnimations.remove(holder);
                        dispatchFinishedWhenDone();
                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        dispatchRemoveStarting(holder);
                    }
                });
    }
}
