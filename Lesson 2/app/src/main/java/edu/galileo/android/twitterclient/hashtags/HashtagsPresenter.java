package edu.galileo.android.twitterclient.hashtags;

import edu.galileo.android.twitterclient.hashtags.events.HashtagsEvent;

/**
 * Created by ykro.
 */
public interface HashtagsPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getHashtagTweets();
    void onEventMainThread(HashtagsEvent event);
}
