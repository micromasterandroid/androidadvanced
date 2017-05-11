package org.galileo.flickrlike.database;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.galileo.flickrlike.Photo;

@Table(database = MyDatabase.class)
public class MyPhoto extends BaseModel {

    @Column
    @PrimaryKey
    String id;

    @Column
    String title;

    @Column
    String server;

    @Column
    String secret;

    @Column
    int farm;

    public MyPhoto() {
    }

    public MyPhoto(Photo photo) {
        this.id = photo.id;
        this.title = photo.title;
        this.server = photo.server;
        this.secret = photo.secret;
        this.farm = photo.farm;
    }

    public String getFlickrUrl() {
        return "https://farm" + farm + ".staticflickr.com/" + server + "/" + id + "_" + secret + "_m.jpg";
    }

    public String getShortFlickrUrl() {
        return "https://farm" + farm + ".staticflickr.com/" + server + "/" + id + "_" + secret + "_s.jpg";
    }
}
