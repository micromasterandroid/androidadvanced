package edu.galileo.android.twitterclient.lib.di;


import android.support.v4.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.twitterclient.lib.GlideImageLoader;
import edu.galileo.android.twitterclient.lib.GreenRobotEventBus;
import edu.galileo.android.twitterclient.lib.base.EventBus;
import edu.galileo.android.twitterclient.lib.base.ImageLoader;

/**
 * Created by ykro.
 */
@Module
public class LibsModule {
    private Fragment fragment;

    public LibsModule(){}

    public LibsModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(RequestManager requestManager) {
        return new GlideImageLoader(requestManager);
    }

    @Provides
    @Singleton
    RequestManager providesRequestManager(Fragment fragment) {
        return Glide.with(fragment);
    }

    @Provides
    @Singleton
    Fragment providesFragment() {
        return this.fragment;
    }

    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus) {
        return new GreenRobotEventBus(eventBus);
    }

    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus() {
        return org.greenrobot.eventbus.EventBus.getDefault();
    }
}
