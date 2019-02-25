package edu.galileo.android.twitterclient.hashtags.di;


import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.twitterclient.api.CustomTwitterApiClient;
import edu.galileo.android.twitterclient.entities.Hashtag;
import edu.galileo.android.twitterclient.hashtags.HashtagsInteractor;
import edu.galileo.android.twitterclient.hashtags.HashtagsInteractorImpl;
import edu.galileo.android.twitterclient.hashtags.HashtagsPresenter;
import edu.galileo.android.twitterclient.hashtags.HashtagsPresenterImpl;
import edu.galileo.android.twitterclient.hashtags.HashtagsRepository;
import edu.galileo.android.twitterclient.hashtags.HashtagsRepositoryImpl;
import edu.galileo.android.twitterclient.hashtags.ui.HashtagsView;
import edu.galileo.android.twitterclient.hashtags.ui.adapters.HashtagsAdapter;
import edu.galileo.android.twitterclient.hashtags.ui.adapters.OnItemClickListener;
import edu.galileo.android.twitterclient.lib.base.EventBus;

/**
 * Created by ykro.
 */
@Module
public class HashtagsModule {
    private HashtagsView view;
    private OnItemClickListener clickListener;


    @Inject
    public HashtagsModule(){}

    public HashtagsModule(HashtagsView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    HashtagsAdapter providesAdapter(List<Hashtag> dataset, OnItemClickListener clickListener){
        return new HashtagsAdapter(dataset, clickListener);
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener(){
        return this.clickListener;
    }

    @Provides
    @Singleton
    List<Hashtag> providesItemsList(){
        return new ArrayList<Hashtag>();
    }

    @Provides
    @Singleton
    HashtagsPresenter providesImagesPresenter(HashtagsView view, EventBus eventBus, HashtagsInteractor interactor){
        return new HashtagsPresenterImpl(view, eventBus, interactor);
    }

    @Provides
    @Singleton
    HashtagsView providesImagesView(){
        return this.view;
    }

    @Provides
    @Singleton
    HashtagsInteractor providesImagesInteractor(HashtagsRepository repository){
        return new HashtagsInteractorImpl(repository);
    }

    @Provides
    @Singleton
    HashtagsRepository providesImagesRepository(EventBus eventBus, CustomTwitterApiClient client){
        return new HashtagsRepositoryImpl(eventBus, client);
    }

    @Provides
    @Singleton
    CustomTwitterApiClient providesCustomTwitterApiClient(TwitterSession session) {
        return new CustomTwitterApiClient(session);
    }

    @Provides
    @Singleton
    TwitterSession providesTwitter() {
        return TwitterCore.getInstance().getSessionManager().getActiveSession();
    }
}
