package edu.galileo.android.twitterclient;

import android.app.Application;
import android.support.v4.app.Fragment;
import android.util.Log;


import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;


import dagger.Binds;


import edu.galileo.android.twitterclient.hashtags.di.DaggerHashtagsComponent;
import edu.galileo.android.twitterclient.hashtags.di.HashtagsComponent;
import edu.galileo.android.twitterclient.hashtags.di.HashtagsModule;
import edu.galileo.android.twitterclient.hashtags.ui.HashtagsView;
import edu.galileo.android.twitterclient.images.di.DaggerImagesComponent;
import edu.galileo.android.twitterclient.images.di.ImagesComponent;
import edu.galileo.android.twitterclient.images.di.ImagesModule;
import edu.galileo.android.twitterclient.images.ui.ImagesView;
import edu.galileo.android.twitterclient.lib.di.LibsModule;

/**
 * Created by ykro.
 */
public class TwitterClientApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TwitterConfig config = new TwitterConfig.Builder(this)
                .twitterAuthConfig(new TwitterAuthConfig(BuildConfig.TWITTER_KEY,BuildConfig.TWITTER_SECRET))
                .build();
        Twitter.initialize(config);
    }

    

    public ImagesComponent getImagesComponent(Fragment fragment, ImagesView view, edu.galileo.android.twitterclient.images.ui.adapters.OnItemClickListener clickListener){
        return DaggerImagesComponent
                .builder()
                .libsModule(new LibsModule(fragment))
                .imagesModule(new ImagesModule(view, clickListener))
                .build();
    }

    public HashtagsComponent getHashtagsComponent(HashtagsView view, edu.galileo.android.twitterclient.hashtags.ui.adapters.OnItemClickListener clickListener){
        return DaggerHashtagsComponent
                .builder()
                .libsModule(new LibsModule(null))
                .hashtagsModule(new HashtagsModule(view, clickListener))
                .build();
    }
}
