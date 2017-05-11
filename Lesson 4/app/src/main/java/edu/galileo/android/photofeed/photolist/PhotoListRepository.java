package edu.galileo.android.photofeed.photolist;

import edu.galileo.android.photofeed.entities.Photo;

 public interface PhotoListRepository {
    void subscribe();
    void unSubscribe();
    void remove(Photo photo);
}
