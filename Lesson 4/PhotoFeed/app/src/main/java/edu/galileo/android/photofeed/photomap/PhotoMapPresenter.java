package edu.galileo.android.photofeed.photomap;

import edu.galileo.android.photofeed.photomap.events.PhotoMapEvent;

 public interface PhotoMapPresenter {
    void onCreate();
    void onDestroy();

    void subscribe();
    void unsubscribe();

    void onEventMainThread(PhotoMapEvent event);
}
