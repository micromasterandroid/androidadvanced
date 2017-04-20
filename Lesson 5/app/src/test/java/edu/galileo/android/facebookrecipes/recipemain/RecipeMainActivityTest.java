package edu.galileo.android.facebookrecipes.recipemain;

import android.content.ComponentName;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.internal.ShadowExtractor;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.util.ActivityController;

import edu.galileo.android.facebookrecipes.BaseTest;
import edu.galileo.android.facebookrecipes.BuildConfig;
import edu.galileo.android.facebookrecipes.R;
import edu.galileo.android.facebookrecipes.entities.Recipe;
import edu.galileo.android.facebookrecipes.libs.base.ImageLoader;
import edu.galileo.android.facebookrecipes.login.ui.LoginActivity;
import edu.galileo.android.facebookrecipes.recipelist.ui.RecipeListActivity;
import edu.galileo.android.facebookrecipes.recipemain.ui.RecipeMainActivity;
import edu.galileo.android.facebookrecipes.recipemain.ui.RecipeMainView;
import edu.galileo.android.facebookrecipes.support.ShadowImageView;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by ykro.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21,
        shadows = {ShadowImageView.class})
public class RecipeMainActivityTest extends BaseTest {
    @Mock
    private Recipe currentRecipe;
    @Mock
    private ImageLoader imageLoader;
    @Mock
    private RecipeMainPresenter presenter;

    private RecipeMainView view;
    private RecipeMainActivity activity;
    private ShadowActivity shadowAcivity;
    private ActivityController<RecipeMainActivity> controller;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RecipeMainActivity recipeMainActivity = new RecipeMainActivity() {
            public ImageLoader getImageLoader() {
                return imageLoader;
            }

            public RecipeMainPresenter getPresenter() {
                return presenter;
            }
        };

        controller = ActivityController.of(Robolectric.getShadowsAdapter(), recipeMainActivity).create().visible();
        activity = controller.get();
        view = (RecipeMainView) activity;
        shadowAcivity = shadowOf(activity);
    }

    @Test
    public void testOnActivityCreated_getNextRecipe() throws Exception {
        verify(presenter).onCreate();
        verify(presenter).getNextRecipe();
    }

    @Test
    public void testOnActivityDestroyed_destroyPresenter() throws Exception {
        controller.destroy();
        verify(presenter).onDestroy();
    }

    @Test
    public void testLogoutMenuClicked_ShouldLaunchLoginActivity() throws Exception {
        shadowAcivity.clickMenuItem(R.id.action_logout);
        Intent intent = shadowAcivity.peekNextStartedActivity();
        assertEquals(new ComponentName(activity, LoginActivity.class), intent.getComponent());
    }

    @Test
    public void testListMenuClicked_ShouldLaunchRecipeListActivity() throws Exception {
        shadowAcivity.clickMenuItem(R.id.action_list);
        Intent intent = shadowAcivity.peekNextStartedActivity();
        assertEquals(new ComponentName(activity, RecipeListActivity.class), intent.getComponent());
    }

    @Test
    public void testKeepButtonClicked_ShouldSaveRecipe() throws Exception {
        activity.setRecipe(currentRecipe);

        ImageButton imgKeep = (ImageButton) activity.findViewById(R.id.imgKeep);
        imgKeep.performClick();
        verify(presenter).saveRecipe(currentRecipe);
    }

    @Test
    public void testDismissButtonClicked_ShouldDismissRecipe() throws Exception {
        ImageButton imgDismiss = (ImageButton) activity.findViewById(R.id.imgDismiss);
        imgDismiss.performClick();
        verify(presenter).dismissRecipe();
    }

    @Test
    public void testOnSwipeToKeep_ShouldSaveRecipe() throws Exception {
        activity.setRecipe(currentRecipe);

        ImageView imgRecipe = (ImageView) activity.findViewById(R.id.imgRecipe);
        ShadowImageView shadowImage = (ShadowImageView) ShadowExtractor.extract(imgRecipe);
        shadowImage.performSwipe(200, 200, 500, 250, 50);
        verify(presenter).saveRecipe(currentRecipe);
    }


    @Test
    public void testOnSwipeToDismiss_ShouldDiscardRecipe() throws Exception {
        ImageView imgRecipe = (ImageView) activity.findViewById(R.id.imgRecipe);
        ShadowImageView shadowImage = (ShadowImageView) ShadowExtractor.extract(imgRecipe);
        shadowImage.performSwipe(200, 200, -500, 250, 50);
        verify(presenter).dismissRecipe();
    }

    @Test
    public void testShowProgress_progressBarShouldBeVisible() throws Exception {
        view.showProgress();

        ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.progressBar);
        assertEquals(View.VISIBLE, progressBar.getVisibility());
    }

    @Test
    public void testHideProgress_progressBarShouldBeGone() throws Exception {
        view.hideProgress();

        ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.progressBar);
        assertEquals(View.GONE, progressBar.getVisibility());
    }

    @Test
    public void testShowUIElements_buttonsShouldBeVisible() throws Exception {
        view.showUIElements();

        ImageButton imgKeep = (ImageButton) activity.findViewById(R.id.imgKeep);
        ImageButton imgDismiss = (ImageButton) activity.findViewById(R.id.imgDismiss);

        assertEquals(View.VISIBLE, imgKeep.getVisibility());
        assertEquals(View.VISIBLE, imgDismiss.getVisibility());
    }

    @Test
    public void testHideUIElements_buttonsShouldBeGone() throws Exception {
        view.hideUIElements();

        ImageButton imgKeep = (ImageButton) activity.findViewById(R.id.imgKeep);
        ImageButton imgDismiss = (ImageButton) activity.findViewById(R.id.imgDismiss);

        assertEquals(View.GONE, imgKeep.getVisibility());
        assertEquals(View.GONE, imgDismiss.getVisibility());
    }

    @Test
    public void testSetRecipe_ImageLoaderShouldBeCalled() throws Exception {
        String url = "http://galileo.edu";
        when(currentRecipe.getImageURL()).thenReturn(url);

        view.setRecipe(currentRecipe);
        ImageView imgRecipe = (ImageView) activity.findViewById(R.id.imgRecipe);
        verify(imageLoader).load(imgRecipe, currentRecipe.getImageURL());
    }

    @Test
    public void testSaveAnimation_AnimationShouldBeStarted() throws Exception {
        view.saveAnimation();

        ImageView imgRecipe = (ImageView) activity.findViewById(R.id.imgRecipe);
        assertNotNull(imgRecipe.getAnimation());
        assertTrue(imgRecipe.getAnimation().hasStarted());
    }

    @Test
    public void testDismissAnimation_AnimationShouldBeStarted() throws Exception {
        view.dismissAnimation();

        ImageView imgRecipe = (ImageView) activity.findViewById(R.id.imgRecipe);
        assertNotNull(imgRecipe.getAnimation());
        assertTrue(imgRecipe.getAnimation().hasStarted());
    }
}

