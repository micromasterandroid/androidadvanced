package edu.galileo.android.facebookrecipes.support;

import android.view.MotionEvent;
import android.widget.ImageView;

import org.robolectric.annotation.Implements;
import org.robolectric.annotation.RealObject;
import org.robolectric.shadows.ShadowView;

/**
 * Created by ykro.
 */
@Implements(ImageView.class)
public class ShadowImageView extends ShadowView {
    @RealObject
    private ImageView realImageView;

    public void performSwipe(float xStart, float yStart, float xEnd, float yEnd, long duration){
        long downTime = 0;
        long moveTime = downTime + duration/2;
        long upTime = downTime + duration;

        realImageView.dispatchTouchEvent(MotionEvent.obtain(downTime, downTime, MotionEvent.ACTION_DOWN, xStart, yStart, 0));
        realImageView.dispatchTouchEvent(MotionEvent.obtain(downTime, moveTime, MotionEvent.ACTION_MOVE, xEnd/2, yEnd/2, 0));
        realImageView.dispatchTouchEvent(MotionEvent.obtain(downTime, moveTime, MotionEvent.ACTION_MOVE, xEnd/2, yEnd/2, 0));
        realImageView.dispatchTouchEvent(MotionEvent.obtain(downTime, upTime, MotionEvent.ACTION_UP, xEnd, yEnd, 0));
    }
}
