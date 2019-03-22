package edu.galileo.android.facebookrecipes.recipemain;

import java.util.Random;

import edu.galileo.android.facebookrecipes.BuildConfig;
import edu.galileo.android.facebookrecipes.api.RecipeSearchResponse;
import edu.galileo.android.facebookrecipes.api.RecipeService;
import edu.galileo.android.facebookrecipes.entities.Recipe;
import edu.galileo.android.facebookrecipes.libs.base.EventBus;
import edu.galileo.android.facebookrecipes.recipemain.events.RecipeMainEvent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ykro.
 */
public class RecipeMainRepositoryImpl implements RecipeMainRepository {
    private int recipePage;
    private EventBus eventBus;
    private RecipeService service;

    public RecipeMainRepositoryImpl(EventBus eventBus, RecipeService service) {
        this.eventBus = eventBus;
        this.service = service;
    }

    @Override
    public void getNextRecipe() {
        Call<RecipeSearchResponse> call = service.search(BuildConfig.FOOD_API_KEY, RECENT_SORT, COUNT, recipePage);
        Callback<RecipeSearchResponse> callback = new Callback<RecipeSearchResponse>() {
            @Override
            public void onResponse(Call<RecipeSearchResponse> call, Response<RecipeSearchResponse> response) {
                if (response.isSuccessful()) {
                    RecipeSearchResponse recipeSearchResponse = response.body();
                    if (recipeSearchResponse.getCount() == 0){
                        setRecipePage(new Random().nextInt(RECIPE_RANGE));
                        getNextRecipe();
                    } else {
                        Recipe recipe = recipeSearchResponse.getFirstRecipe();
                        if (recipe != null) {
                            post(recipe);
                        } else {
                            post(response.message());
                        }
                    }
                } else {
                    post(response.message());
                }
            }

            @Override
            public void onFailure(Call<RecipeSearchResponse> call, Throwable t) {
                post(t.getLocalizedMessage());
            }
        };

        call.enqueue(callback);
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        recipe.save();
        post();
    }

    @Override
    public void setRecipePage(int recipePage) {
        this.recipePage = recipePage;
    }

    private void post(String error, int type, Recipe recipe){
        RecipeMainEvent event = new RecipeMainEvent();
        event.setType(type);
        event.setError(error);
        event.setRecipe(recipe);
        eventBus.post(event);
    }

    private void post(Recipe recipe){
        post(null, RecipeMainEvent.NEXT_EVENT, recipe);
    }

    private void post(String error){
        post(error, RecipeMainEvent.NEXT_EVENT, null);
    }

    private void post(){
        post(null, RecipeMainEvent.SAVE_EVENT, null);
    }
}
