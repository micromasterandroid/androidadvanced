package edu.galileo.android.twitterclient.images;

import edu.galileo.android.twitterclient.images.events.ImagesEvent;

/**
 * Created by ykro.
 */
public interface ImagesPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getImageTweets();
    void onEventMainThread(ImagesEvent event);
}
