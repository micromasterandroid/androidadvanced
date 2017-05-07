package org.galileo.flickrlike;

import static org.galileo.flickrlike.MainActivity.TAGS_EXTRA;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.galileo.flickrlike.database.MyPhoto;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private static final int PAGE_SIZE = 15;

    @BindView(R.id.displayImage) ImageView displayImageView;
    @BindView(R.id.titleTextView) TextView titleTextView;
    @BindView(R.id.progress_bar) ProgressBar progressBar;

    private List<Photo> photosList;
    private int selectedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        String tags = getIntent().getStringExtra(TAGS_EXTRA);
        doSearch(tags);
        setImageViewListener();
    }

    private void setImageViewListener() {
        displayImageView.setOnTouchListener(new OnSwipeTouchListener(this) {

            @Override
            public void onSwipeDown() {
                showNextPhoto();
                Snackbar.make(titleTextView, "Passed", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onSwipeLeft() {
                save(photosList.get(selectedIndex));
                showNextPhoto();
                Snackbar.make(titleTextView, "Saved", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onSwipeUp() {
                showNextPhoto();
                Snackbar.make(titleTextView, "Passed", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onSwipeRight() {
                MyPhoto myPhoto = new MyPhoto(photosList.get(selectedIndex));
                myPhoto.save();
                showNextPhoto();
                Snackbar.make(titleTextView, "Saved", Snackbar.LENGTH_LONG).show();
            }

        });
    }

    private void doSearch(String tags) {
        FlickrClient flickrClient = new FlickrClient();

        FlickrService flickrService = flickrClient.getFlickrService();

        Call<PhotosResponse> call = flickrService.search(tags, PAGE_SIZE);
        call.enqueue(new Callback<PhotosResponse>() {

            @Override
            public void onResponse(Call<PhotosResponse> call, Response<PhotosResponse> response) {
                if (response.isSuccessful()) {
                    PhotosResponse photosResponse = response.body();
                    Photos photos = photosResponse.photos;

                    photosList = photos.photo;

                    loadPhoto(photos.photo.get(selectedIndex).getFlickrUrl());
                } else {
                    Toast.makeText(getBaseContext(), "There was an error in the Flickr response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PhotosResponse> call, Throwable t) {
                Toast.makeText(getBaseContext(), "There was an error when calling the Flickr search endpoint.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadPhoto(String url) {
        progressBar.setVisibility(View.VISIBLE);
        titleTextView.setText("");

        Glide.with(getApplicationContext())
            .load(url)
            .listener(new RequestListener<String, GlideDrawable>() {

                @Override
                public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                    progressBar.setVisibility(View.GONE);
                    return false;
                }

                @Override
                public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    progressBar.setVisibility(View.GONE);
                    titleTextView.setText("Title: " + photosList.get(selectedIndex).title);
                    return false;
                }
            })
            .into(displayImageView);
    }

    private void save(Photo photo) {
        MyPhoto myPhoto = new MyPhoto(photo);
        myPhoto.save();
    }

    private void showNextPhoto() {
        if (selectedIndex < photosList.size() - 1) {
            selectedIndex = selectedIndex + 1;

            loadPhoto(photosList.get(selectedIndex).getFlickrUrl());
        } else {
            Toast.makeText(getBaseContext(), "No more photos to show!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
