package edu.galileo.android.photofeed.lib.di;

import android.content.Context;
import android.support.v4.app.Fragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.photofeed.lib.CloudinaryImageStorage;
import edu.galileo.android.photofeed.lib.GlideImageLoader;
import edu.galileo.android.photofeed.lib.GreenRobotEventBus;
import edu.galileo.android.photofeed.lib.base.EventBus;
import edu.galileo.android.photofeed.lib.base.ImageLoader;
import edu.galileo.android.photofeed.lib.base.ImageStorage;

 @Module
public class LibsModule {
    private Fragment fragment;

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    EventBus providesEventBus() {
        return new GreenRobotEventBus();
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(Fragment fragment) {
        GlideImageLoader imageLoader = new GlideImageLoader();
        if (fragment != null) {
            imageLoader.setLoaderContext(fragment);
        }
        return imageLoader;
    }

    @Provides
    @Singleton
    ImageStorage providesImageStorage(Context context, EventBus eventBus) {
        ImageStorage imageStorage = new CloudinaryImageStorage(context, eventBus);
        return imageStorage;
    }

    @Provides
    @Singleton
    Fragment providesFragment(){
        return this.fragment;
    }
}
