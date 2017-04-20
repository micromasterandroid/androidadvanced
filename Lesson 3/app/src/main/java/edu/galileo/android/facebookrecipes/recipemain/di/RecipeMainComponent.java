package edu.galileo.android.facebookrecipes.recipemain.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.facebookrecipes.libs.base.ImageLoader;
import edu.galileo.android.facebookrecipes.libs.di.LibsModule;
import edu.galileo.android.facebookrecipes.recipemain.RecipeMainPresenter;

/**
 * Created by ykro.
 */
@Singleton
@Component(modules = {RecipeMainModule.class, LibsModule.class})
public interface RecipeMainComponent {
    //void inject(RecipeMainActivity activity);
    ImageLoader getImageLoader();
    RecipeMainPresenter getPresenter();
}
