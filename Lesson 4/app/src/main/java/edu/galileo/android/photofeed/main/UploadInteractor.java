package edu.galileo.android.photofeed.main;

import android.location.Location;

 public interface UploadInteractor {
    void execute(Location location, String path);
}
