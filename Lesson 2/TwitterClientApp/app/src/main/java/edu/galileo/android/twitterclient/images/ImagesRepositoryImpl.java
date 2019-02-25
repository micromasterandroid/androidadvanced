package edu.galileo.android.twitterclient.images;

import android.support.design.widget.Snackbar;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.galileo.android.twitterclient.api.CustomTwitterApiClient;
import edu.galileo.android.twitterclient.entities.Image;
import edu.galileo.android.twitterclient.images.events.ImagesEvent;
import edu.galileo.android.twitterclient.lib.base.EventBus;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by ykro.
 */
public class ImagesRepositoryImpl implements ImagesRepository {
    private EventBus eventBus;
    private CustomTwitterApiClient client;
    private final static int TWEET_COUNT = 100;

    public ImagesRepositoryImpl(EventBus eventBus, CustomTwitterApiClient client) {
        this.eventBus = eventBus;
        this.client = client;
    }

    @Override
    public void getImages() {
        Call<List<Tweet>> call = client.getTimelineService().homeTimeline(TWEET_COUNT,true,true,true,true);
        call.enqueue(new retrofit2.Callback<List<Tweet>>() {
            @Override
            public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
                List<Image> items = new ArrayList<Image>();
                for (Tweet tweet : response.body()){
                    if (containsImages(tweet)) {
                        Image tweetModel = new Image();

                        tweetModel.setId(tweet.idStr);
                        tweetModel.setFavoriteCount(tweet.favoriteCount);

                        String tweetText = tweet.text;
                        int index = tweetText.indexOf("http");
                        if (index > 0) {
                            tweetText = tweetText.substring(0, index);
                        }
                        tweetModel.setTweetText(tweetText);

                        MediaEntity currentPhoto = tweet.entities.media.get(0);
                        String imageUrl = currentPhoto.mediaUrl;
                        tweetModel.setImageURL(imageUrl);

                        items.add(tweetModel);
                    }
                }

                Collections.sort(items, new Comparator<Image>() {
                    @Override
                    public int compare(Image image, Image t1) {
                        return t1.getFavoriteCount() - image.getFavoriteCount();
                    }
                });
                post(items);
            }

            @Override
            public void onFailure(Call<List<Tweet>> call, Throwable t) {
                post(t.getLocalizedMessage());
            }
        });
    }

    private boolean containsImages(Tweet tweet){
        return tweet.entities != null &&
                tweet.entities.media != null &&
                !tweet.entities.media.isEmpty();
    }

    private void post(List<Image> items) {
        post(items, null);
    }

    private void post(String error) {
        post(null, error);
    }

    private void post(List<Image> items, String error) {
        ImagesEvent event = new ImagesEvent();
        event.setError(error);
        event.setImages(items);
        eventBus.post(event);
    }
}
