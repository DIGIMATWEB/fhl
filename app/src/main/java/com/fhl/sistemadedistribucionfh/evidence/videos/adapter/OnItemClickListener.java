package com.fhl.sistemadedistribucionfh.evidence.videos.adapter;

import android.net.Uri;

public interface OnItemClickListener {
    void onItemClick(Uri videoUri, int position);

    void removeformmedia(Uri uri);
    void startRecord(int position);
}
