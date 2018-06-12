
package com.xinze.xinze.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;

/**
 * RecyclerView的间距
 * 
 * @author anjihang
 */
public class ImageItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public ImageItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        super.getItemOffsets(outRect, itemPosition, parent);
        if (itemPosition != 0) {
            outRect.left = space;
        }
    }
}
