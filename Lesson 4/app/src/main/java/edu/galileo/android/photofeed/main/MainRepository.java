package edu.galileo.android.photofeed.main;

import android.location.Location;

 public interface MainRepository {
    void logout();
    void uploadPhoto(Location location, String path);
}
