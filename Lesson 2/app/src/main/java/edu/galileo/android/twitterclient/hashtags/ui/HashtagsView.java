package edu.galileo.android.twitterclient.hashtags.ui;

import java.util.List;

import edu.galileo.android.twitterclient.entities.Hashtag;

/**
 * Created by ykro.
 */
public interface HashtagsView {
    void showImages();
    void hideImages();
    void showProgress();
    void hideProgress();

    void onError(String error);
    void setContent(List<Hashtag> items);
}
