package edu.galileo.android.facebookrecipes.support;

import android.support.v7.widget.RecyclerView;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.shadows.ShadowViewGroup;

/**
 * Created by ykro.
 */
@Implements(RecyclerView.class)
public class ShadowRecyclerView extends ShadowViewGroup {
    private int smoothScrollPosition = -1;

    @Implementation
    public void smoothScrollToPosition(int pos) {
        setSmoothScrollPosition(pos);
    }

    public int getSmoothScrollPosition() {
        return smoothScrollPosition;
    }

    public void setSmoothScrollPosition(int smoothScrollPosition) {
        this.smoothScrollPosition = smoothScrollPosition;
    }
}
