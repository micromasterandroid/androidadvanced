package edu.galileo.android.photofeed.photolist;

import edu.galileo.android.photofeed.entities.Photo;

 public interface PhotoListRepository {
    void subscribe();
    void unsubscribe();
    void remove(Photo photo);
}
