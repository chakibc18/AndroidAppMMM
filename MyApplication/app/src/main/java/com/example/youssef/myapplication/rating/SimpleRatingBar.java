package com.example.youssef.myapplication.rating;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;

/**
 * Created by willy on 2017/5/10.
 */
interface SimpleRatingBar {

    int getNumStars();

    void setNumStars(int numStars);

    float getRating();

    void setRating(float rating);

    int getStarPadding();

    void setStarPadding(int ratingPadding);

    void setEmptyDrawable(Drawable drawable);

    void setEmptyDrawableRes(@DrawableRes int res);

    void setFilledDrawable(Drawable drawable);

    void setFilledDrawableRes(@DrawableRes int res);


}
