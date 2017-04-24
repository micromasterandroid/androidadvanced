package edu.galileo.android.photofeed.main.ui;

 public interface MainView {
    void onUploadInit();
    void onUploadComplete();
    void onUploadError(String error);
}
