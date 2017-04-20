package edu.galileo.android.twitterclient.hashtags;

import org.greenrobot.eventbus.Subscribe;

import edu.galileo.android.twitterclient.hashtags.events.HashtagsEvent;
import edu.galileo.android.twitterclient.hashtags.ui.HashtagsView;
import edu.galileo.android.twitterclient.lib.base.EventBus;

/**
 * Created by ykro.
 */
public class HashtagsPresenterImpl implements HashtagsPresenter {
    private HashtagsView view;
    private EventBus eventBus;
    private HashtagsInteractor interactor;

    public HashtagsPresenterImpl(HashtagsView view, EventBus eventBus, HashtagsInteractor interactor) {
        this.view = view;
        this.eventBus = eventBus;
        this.interactor = interactor;
    }

    @Override
    public void onResume() {
        eventBus.register(this);
    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void getHashtagTweets() {
        if (view != null) {
            view.hideImages();
            view.showProgress();
        }
        interactor.execute();
    }

    @Override
    @Subscribe
    public void onEventMainThread(HashtagsEvent event) {
        String errorMsg = event.getError();
        if (view != null) {
            view.showImages();
            view.hideProgress();
            if (errorMsg != null) {
                view.onError(errorMsg);
            } else {
                view.setContent(event.getHashtags());
            }
        }
    }
}
