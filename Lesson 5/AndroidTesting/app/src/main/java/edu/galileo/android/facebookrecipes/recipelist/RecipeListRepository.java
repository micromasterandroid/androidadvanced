package edu.galileo.android.facebookrecipes.recipelist;

import edu.galileo.android.facebookrecipes.entities.Recipe;

/**
 * Created by ykro.
 */
public interface RecipeListRepository {
    void getSavedRecipes();
    void updateRecipe(Recipe recipe);
    void removeRecipe(Recipe recipe);
}
