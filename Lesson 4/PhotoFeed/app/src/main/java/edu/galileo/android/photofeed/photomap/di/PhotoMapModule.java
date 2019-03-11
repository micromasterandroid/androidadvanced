package edu.galileo.android.photofeed.photomap.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.photofeed.domain.FirebaseAPI;
import edu.galileo.android.photofeed.lib.base.EventBus;
import edu.galileo.android.photofeed.photomap.PhotoMapInteractor;
import edu.galileo.android.photofeed.photomap.PhotoMapInteractorImpl;
import edu.galileo.android.photofeed.photomap.PhotoMapPresenter;
import edu.galileo.android.photofeed.photomap.PhotoMapPresenterImpl;
import edu.galileo.android.photofeed.photomap.PhotoMapRepository;
import edu.galileo.android.photofeed.photomap.PhotoMapRepositoryImpl;
import edu.galileo.android.photofeed.photomap.ui.PhotoMapView;

 @Module
public class PhotoMapModule {
    PhotoMapView view;

    public PhotoMapModule(PhotoMapView view) {
        this.view = view;
    }

    @Provides @Singleton
    PhotoMapView providesPhotoContentView() {
        return this.view;
    }

    @Provides @Singleton
    PhotoMapPresenter providesPhotoContentPresenter(EventBus eventBus, PhotoMapView view, PhotoMapInteractor listInteractor) {
        return new PhotoMapPresenterImpl(eventBus, view, listInteractor);
    }

    @Provides @Singleton
    PhotoMapInteractor providesPhotoContentInteractor(PhotoMapRepository repository) {
        return new PhotoMapInteractorImpl(repository);
    }

    @Provides @Singleton
    PhotoMapRepository providesPhotoContentRepository(FirebaseAPI firebase, EventBus eventBus) {
        return new PhotoMapRepositoryImpl(firebase, eventBus);
    }
}
