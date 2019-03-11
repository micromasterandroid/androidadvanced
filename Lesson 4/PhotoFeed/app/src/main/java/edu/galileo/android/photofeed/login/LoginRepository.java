package edu.galileo.android.photofeed.login;

 public interface LoginRepository {
    void signUp(final String email, final String password);
    void signIn(String email, String password);
}
