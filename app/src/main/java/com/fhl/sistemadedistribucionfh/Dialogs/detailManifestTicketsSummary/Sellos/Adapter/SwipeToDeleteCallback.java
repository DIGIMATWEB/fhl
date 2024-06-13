package com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.Adapter;

import android.graphics.Canvas;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.Adapter.adapterSellosManifestDetail;

public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {
    private adapterSellosManifestDetail mAdapter;

    public SwipeToDeleteCallback(adapterSellosManifestDetail adapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        mAdapter = adapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false; // We don't want support for moving items up/down
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        mAdapter.removeItem(position);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                            float dX, float dY, int actionState, boolean isCurrentlyActive) {
        // Custom drawing for swipe (optional)
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}