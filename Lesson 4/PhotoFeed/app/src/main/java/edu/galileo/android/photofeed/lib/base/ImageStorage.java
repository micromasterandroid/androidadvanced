package edu.galileo.android.photofeed.lib.base;

import java.io.File;

 public interface ImageStorage {
    String getImageUrl(String id);
    void upload(File file, String id, ImageStorageFinishedListener listener);
}
