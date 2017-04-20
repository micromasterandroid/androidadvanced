package edu.galileo.android.facebookrecipes.recipelist.di;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.facebookrecipes.entities.Recipe;
import edu.galileo.android.facebookrecipes.libs.base.EventBus;
import edu.galileo.android.facebookrecipes.libs.base.ImageLoader;
import edu.galileo.android.facebookrecipes.recipelist.RecipeListInteractor;
import edu.galileo.android.facebookrecipes.recipelist.RecipeListInteractorImpl;
import edu.galileo.android.facebookrecipes.recipelist.RecipeListPresenter;
import edu.galileo.android.facebookrecipes.recipelist.RecipeListPresenterImpl;
import edu.galileo.android.facebookrecipes.recipelist.RecipeListRepository;
import edu.galileo.android.facebookrecipes.recipelist.RecipeListRepositoryImpl;
import edu.galileo.android.facebookrecipes.recipelist.StoredRecipesInteractor;
import edu.galileo.android.facebookrecipes.recipelist.StoredRecipesInteractorImpl;
import edu.galileo.android.facebookrecipes.recipelist.ui.RecipeListView;
import edu.galileo.android.facebookrecipes.recipelist.ui.adapters.OnItemClickListener;
import edu.galileo.android.facebookrecipes.recipelist.ui.adapters.RecipesAdapter;

/**
 * Created by ykro.
 */
@Module
public class RecipeListModule {
    RecipeListView view;
    OnItemClickListener clickListener;

    public RecipeListModule(RecipeListView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides @Singleton
    RecipeListView providesRecipeListView(){
        return this.view;
    }

    @Provides @Singleton
    RecipeListPresenter providesRecipeListPresenter(EventBus eventBus, RecipeListView view, RecipeListInteractor listInteractor, StoredRecipesInteractor storedInteractor){
        return new RecipeListPresenterImpl(eventBus, view, listInteractor, storedInteractor);
    }

    @Provides @Singleton
    StoredRecipesInteractor providesStoredRecipesInteractor(RecipeListRepository repository){
        return new StoredRecipesInteractorImpl(repository);
    }

    @Provides @Singleton
    RecipeListInteractor providesRecipeListInteractor(RecipeListRepository repository){
        return new RecipeListInteractorImpl(repository);
    }

    @Provides @Singleton
    RecipeListRepository providesRecipeListRepository(EventBus eventBus){
        return new RecipeListRepositoryImpl(eventBus);
    }

    @Provides @Singleton
    RecipesAdapter providesRecipesAdapter(List<Recipe> recipeList, ImageLoader imageLoader, OnItemClickListener onItemClickListener){
        return new RecipesAdapter(recipeList, imageLoader, onItemClickListener);
    }

    @Provides @Singleton
    OnItemClickListener providesOnItemClickListener(){
        return this.clickListener;
    }

    @Provides @Singleton
    List<Recipe> providesEmptyList(){
        return new ArrayList<Recipe>();
    }
}
