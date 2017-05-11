package org.galileo.flickrlike;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface FlickrService {

    @GET("/services/rest/?method=flickr.photos.search")
    Call<PhotosResponse> search(@Query("tags") String tags, @Query("per_page") int perPage);
}
