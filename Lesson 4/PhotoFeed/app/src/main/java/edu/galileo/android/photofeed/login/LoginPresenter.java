package edu.galileo.android.photofeed.login;

import edu.galileo.android.photofeed.login.events.LoginEvent;

 public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void onEventMainThread(LoginEvent event);
    void login(String email, String password);
    void registerNewUser(String email, String password);
}
