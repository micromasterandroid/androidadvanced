package edu.galileo.android.photofeed.login.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.photofeed.PhotoFeedAppModule;
import edu.galileo.android.photofeed.domain.di.DomainModule;
import edu.galileo.android.photofeed.lib.di.LibsModule;
import edu.galileo.android.photofeed.login.ui.LoginActivity;

 @Singleton
@Component(modules = {LoginModule.class, DomainModule.class, LibsModule.class, PhotoFeedAppModule.class})
public interface LoginComponent {
    void inject(LoginActivity activity);
}
