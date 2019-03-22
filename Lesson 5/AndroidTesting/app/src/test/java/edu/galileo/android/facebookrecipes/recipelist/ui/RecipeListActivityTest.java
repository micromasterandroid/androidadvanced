package edu.galileo.android.facebookrecipes.recipelist.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.facebook.FacebookActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.shadow.api.Shadow;
import org.robolectric.shadows.ShadowActivity;

import java.util.List;

import edu.galileo.android.facebookrecipes.BaseTest;
import edu.galileo.android.facebookrecipes.BuildConfig;
import edu.galileo.android.facebookrecipes.R;
import edu.galileo.android.facebookrecipes.entities.Recipe;
import edu.galileo.android.facebookrecipes.libs.base.ImageLoader;
import edu.galileo.android.facebookrecipes.login.ui.LoginActivity;
import edu.galileo.android.facebookrecipes.recipelist.RecipeListPresenter;
import edu.galileo.android.facebookrecipes.recipelist.ui.adapters.OnItemClickListener;
import edu.galileo.android.facebookrecipes.recipelist.ui.adapters.RecipesAdapter;
import edu.galileo.android.facebookrecipes.recipemain.ui.RecipeMainActivity;
import edu.galileo.android.facebookrecipes.support.ShadowRecyclerView;
import edu.galileo.android.facebookrecipes.support.ShadowRecyclerViewAdapter;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by ykro.
 */
@RunWith(RobolectricTestRunner.class)
@Config(shadows = {ShadowRecyclerView.class, ShadowRecyclerViewAdapter.class})
public class RecipeListActivityTest extends BaseTest {
    @Mock
    private Recipe recipe;
    @Mock
    private List<Recipe> recipeList;
    @Mock
    private ImageLoader imageLoader;

    private RecipesAdapter adapter;
    private RecipeListPresenter presenter;
    @Mock
    private RecipeListActivity mockActivity;

    private RecipeListView view;
    private OnItemClickListener onItemClickListener;

    private ShadowActivity shadowActivity;
    private ShadowRecyclerViewAdapter shadowAdapter;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        //TODO fix this one, like the main activity
        //controller = ActivityController.of(Robolectric.getShadowsAdapter(), recipeListActivity).create().visible();

        mockActivity = Mockito.spy(Robolectric.buildActivity(RecipeListActivity.class).create().get());

        view = (RecipeListView)mockActivity;
        onItemClickListener = (OnItemClickListener)mockActivity;

        shadowActivity = shadowOf(mockActivity);
    }

    @Test
    public void testOnCreate_ShouldCallPresenter() throws Exception {
        mockActivity.onDestroy();
        mockActivity.onCreate(null);
        verify(mockActivity).createPresenter();
    }       

    @Test
    public void testOnDestroy_ShouldCallPresenter() throws Exception {
        mockActivity.onDestroy();
        verify(mockActivity).destroyPresenter();
    }

    @Test
    public void testLogoutMenuClicked_ShouldLaunchLoginActivity() throws Exception {
        shadowActivity.clickMenuItem(R.id.action_logout);
        Intent intent = shadowActivity.peekNextStartedActivity();
        assertEquals(new ComponentName(mockActivity, LoginActivity.class), intent.getComponent());
    }

    @Test
    public void testMainMenuClicked_ShouldLaunchRecipeMainActivity() throws Exception {
        shadowActivity.clickMenuItem(R.id.action_main);
        Intent intent = shadowActivity.peekNextStartedActivity();
        assertEquals(new ComponentName(mockActivity, RecipeMainActivity.class), intent.getComponent());
    }

    @Test
    public void testSetRecipes_ShouldSetInAdapter() throws Exception {
        view.setRecipes(recipeList);
        verify(mockActivity).setRecipes(recipeList);
    }

    @Test
    public void testRecipeUpdated_ShouldUpdateAdapter() throws Exception {
        view.recipeUpdated();
        verify(mockActivity).recipeUpdated();
    }

    @Test
    public void testRecipeDeleted_ShouldUpdateAdapter() throws Exception {
        view.recipeDeleted(recipe);
        verify(mockActivity).recipeDeleted(recipe);
    }

    @Test
    public void testOnRecyclerViewScroll_ShouldChangeScrollPosition() throws Exception {
        int scrollPosition = 1;

        RecyclerView recyclerView = (RecyclerView)mockActivity.findViewById(R.id.recyclerView);
        ShadowRecyclerView shadowRecyclerView = (ShadowRecyclerView) Shadow.extract(recyclerView);

        recyclerView.smoothScrollToPosition(scrollPosition);
        assertEquals(scrollPosition, shadowRecyclerView.getSmoothScrollPosition());
    }

    @Test
    public void testOnToolbarClicked_RecyclerViewShouldScrollToTop() throws Exception {
        int scrollPosition = 1;
        int topScrollPosition = 0;
        Toolbar toolbar = (Toolbar)mockActivity.findViewById(R.id.toolbar);
        RecyclerView recyclerView = (RecyclerView)mockActivity.findViewById(R.id.recyclerView);
        ShadowRecyclerView shadowRecyclerView = (ShadowRecyclerView) Shadow.extract(recyclerView);
        recyclerView.smoothScrollToPosition(scrollPosition);

        toolbar.performClick();

        assertEquals(topScrollPosition, shadowRecyclerView.getSmoothScrollPosition());
    }

    @Test
    public void testRecyclerViewItemClicked_ShouldStartViewActivity() throws Exception {
        int positionToClick = 0;
        setUpShadowAdapter(positionToClick);

        shadowAdapter.itemVisible(positionToClick);
        shadowAdapter.performItemClick(positionToClick);

        Intent intent = shadowActivity.peekNextStartedActivity();
        assertEquals(Intent.ACTION_VIEW, intent.getAction());
        assertEquals(recipeList.get(positionToClick).getSourceURL(), intent.getDataString());
    }

    @Test
    public void testRecyclerViewFavoriteClicked_ShouldCallPresenter() throws Exception {
        int positionToClick = 0;
        setUpShadowAdapter(positionToClick);

        shadowAdapter.itemVisible(positionToClick);
        shadowAdapter.performItemClickOverViewInHolder(positionToClick, R.id.imgFav);

        verify(mockActivity).onFavClick(recipe);
    }

    @Test
    public void testRecyclerViewRemoveClicked_ShouldCallPresenter() throws Exception {
        int positionToClick = 0;
        setUpShadowAdapter(positionToClick);

        shadowAdapter.itemVisible(positionToClick);
        shadowAdapter.performItemClickOverViewInHolder(positionToClick, R.id.imgDelete);

        verify(mockActivity).onDeleteClick(recipe);
    }

    @Test
    public void testRecyclerViewFBShareClicked_ShouldStartFBActivity() throws Exception {
        int positionToClick = 0;
        setUpShadowAdapter(positionToClick);

        shadowAdapter.itemVisible(positionToClick);
        shadowAdapter.performItemClickOverViewInHolder(positionToClick, R.id.fbShare);

        Intent intent = shadowActivity.peekNextStartedActivity();
        assertEquals(new ComponentName(RuntimeEnvironment.application, FacebookActivity.class), intent.getComponent());
    }

    @Test
    public void testRecyclerViewFBSendClicked_ShouldStartFBActivity() throws Exception {
        int positionToClick = 0;
        setUpShadowAdapter(positionToClick);

        shadowAdapter.itemVisible(positionToClick);
        shadowAdapter.performItemClickOverViewInHolder(positionToClick, R.id.fbSend);

        Intent intent = shadowActivity.peekNextStartedActivity();
        assertEquals(new ComponentName(RuntimeEnvironment.application, FacebookActivity.class), intent.getComponent());
    }

    private void setUpShadowAdapter(int positionToClick){
        when(recipe.getSourceURL()).thenReturn("http://galileo.edu");
        when(recipeList.get(positionToClick)).thenReturn(recipe);

        RecyclerView recyclerView = (RecyclerView)mockActivity.findViewById(R.id.recyclerView);
        RecipesAdapter adapterPopulated = new RecipesAdapter(recipeList, imageLoader, onItemClickListener);
        recyclerView.setAdapter(adapterPopulated);
        shadowAdapter = (ShadowRecyclerViewAdapter) Shadow.extract(recyclerView.getAdapter());
    }
}
