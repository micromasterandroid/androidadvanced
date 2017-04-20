package edu.galileo.android.twitterclient.hashtags.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.twitterclient.hashtags.HashtagsPresenter;
import edu.galileo.android.twitterclient.hashtags.ui.HashtagsFragment;
import edu.galileo.android.twitterclient.lib.di.LibsModule;

/**
 * Created by ykro.
 */
@Singleton @Component(modules = {LibsModule.class, HashtagsModule.class})
public interface HashtagsComponent {
    void inject(HashtagsFragment fragment);
    HashtagsPresenter getPresenter();
}
