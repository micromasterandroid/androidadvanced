package edu.galileo.android.photofeed.login.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.photofeed.domain.FirebaseAPI;
import edu.galileo.android.photofeed.lib.base.EventBus;
import edu.galileo.android.photofeed.login.LoginInteractor;
import edu.galileo.android.photofeed.login.LoginInteractorImpl;
import edu.galileo.android.photofeed.login.LoginPresenter;
import edu.galileo.android.photofeed.login.LoginPresenterImpl;
import edu.galileo.android.photofeed.login.LoginRepository;
import edu.galileo.android.photofeed.login.LoginRepositoryImpl;
import edu.galileo.android.photofeed.login.SignupInteractor;
import edu.galileo.android.photofeed.login.SignupInteractorImpl;
import edu.galileo.android.photofeed.login.ui.LoginView;

 @Module
public class LoginModule {
    LoginView view;

    public LoginModule(LoginView view) {
        this.view = view;
    }

    @Provides @Singleton
    LoginView providesLoginView() {
        return this.view;
    }

    @Provides @Singleton
    LoginPresenter providesLoginPresenter(EventBus eventBus, LoginView loginView, LoginInteractor loginInteractor, SignupInteractor signupInteractor) {
        return new LoginPresenterImpl(eventBus, loginView, loginInteractor, signupInteractor);
    }

    @Provides @Singleton
    LoginInteractor providesLoginInteractor(LoginRepository repository) {
        return new LoginInteractorImpl(repository);
    }

    @Provides @Singleton
    SignupInteractor providesSignupInteractor(LoginRepository repository) {
        return new SignupInteractorImpl(repository);
    }

    @Provides @Singleton
    LoginRepository providesLoginRepository(FirebaseAPI firebase, EventBus eventBus) {
        return new LoginRepositoryImpl(firebase, eventBus);
    }
}
