package edu.galileo.android.photofeed.photolist.ui.adapters;

import android.widget.ImageView;

import edu.galileo.android.photofeed.entities.Photo;

 public interface OnItemClickListener {
    void onPlaceClick(Photo photo);
    void onShareClick(Photo photo, ImageView img);
    void onDeleteClick(Photo photo);
}
