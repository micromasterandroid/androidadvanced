package org.galileo.flickrlike;

public class Photo {

    public String id;
    public String title;
    public String server;
    public String secret;
    public int farm;

    //https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg
    public String getFlickrUrl() {
        return "https://farm" + farm + ".staticflickr.com/" + server + "/" + id + "_" + secret + "_b.jpg";
    }
}
