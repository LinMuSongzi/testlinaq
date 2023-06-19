package com.splite.test;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;


public class GalleryTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.8f;

    private static final float MIN_ALPHA = 0.8f;

    private Context context;

    private float elevation;

    public GalleryTransformer(Context context) {

        this.context = context;

        elevation = 30;
    }

    @Override
    public void transformPage(View page, float position) {
        page.setTranslationX(-700* position);
//给卡片添加阴影 ------开始-------

//        if (position <= -1 || position >= 1) {
//
//        } else {
//
//            if (position < 0) {
//
//                ((CardView) page).setCardElevation((1 + position) * elevation);
//
//            } else {
//
//                ((CardView) page).setCardElevation((1 - position) * elevation);
//
//            }
//
//        }

//给卡片添加阴影 ------结束-------

//给卡片添加缩放效果 ------开始-------

        if (position < -1 || position > 1) {

            page.setAlpha(0f);

            page.setScaleX(MIN_SCALE);

            page.setScaleY(MIN_SCALE);

        } else if (position <= 1) {// [-1,1]

            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));

            if (position < 0) {

                float scaleX = 1 + 0.2f * position;

                Log.d("google_lenve_fb", "transformPage: scaleX:" + scaleX);

                page.setScaleX(scaleX);

                page.setScaleY(scaleX);

            } else {

                float scaleX = 1 - 0.2f * position;

                page.setScaleX(scaleX);

                page.setScaleY(scaleX);

            }

            page.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));

        }

//给卡片添加缩放效果 ------结束-------

    }


}
