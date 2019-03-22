package edu.galileo.android.facebookrecipes.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ykro.
 */
public interface RecipeService {
    @GET("search")
    Call<RecipeSearchResponse> search(@Query("key") String key,
                                      @Query("sort") String sort,
                                      @Query("count") int count,
                                      @Query("page") int page);
}
