package com.fhl.sistemadedistribucionfh.mainContainer;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class LinearSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spacing;

    public LinearSpacingItemDecoration(int spacing) {
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        if (position == 0) {
            outRect.left = spacing; // Add spacing to the left of the first item
        }
        // Remove right spacing for the last item
        if (position == parent.getAdapter().getItemCount() - 1) {
            outRect.right = 0;
        } else {
            outRect.right = spacing; // Add spacing to the right of all other items
        }
    }
}