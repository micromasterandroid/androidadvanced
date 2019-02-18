package edu.galileo.android.facebookrecipes.entities;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import edu.galileo.android.facebookrecipes.db.RecipesDatabase;

/**
 * Created by ykro.
 */
@Table(database = RecipesDatabase.class)
public class Recipe extends BaseModel {
    @SerializedName("recipe_id")
    @PrimaryKey private String recipeId;

    @Column private String title;

    @SerializedName("image_url")
    @Column private String imageURL;

    @SerializedName("source_url")
    @Column private String sourceURL;

    @Column private boolean favorite;

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getSourceURL() {
        return sourceURL;
    }

    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equal = false;
        if (obj instanceof Recipe) {
            Recipe recipe = (Recipe)obj;
            equal = this.recipeId.equals(recipe.getRecipeId());
        }
        return equal;
    }
}
