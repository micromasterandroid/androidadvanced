package edu.galileo.android.facebookrecipes.recipemain.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.shadow.api.Shadow;
import org.robolectric.shadows.ShadowActivity;

import edu.galileo.android.facebookrecipes.BaseTest;
import edu.galileo.android.facebookrecipes.R;
import edu.galileo.android.facebookrecipes.entities.Recipe;
import edu.galileo.android.facebookrecipes.libs.base.ImageLoader;
import edu.galileo.android.facebookrecipes.login.ui.LoginActivity;
import edu.galileo.android.facebookrecipes.recipelist.ui.RecipeListActivity;
import edu.galileo.android.facebookrecipes.recipemain.RecipeMainPresenter;
import edu.galileo.android.facebookrecipes.recipemain.ui.RecipeMainActivity;
import edu.galileo.android.facebookrecipes.recipemain.ui.RecipeMainView;
import edu.galileo.android.facebookrecipes.support.ShadowImageView;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by ykro.
 */
@RunWith(RobolectricTestRunner.class)
@Config(shadows = {ShadowImageView.class})
public class RecipeMainActivityTest extends BaseTest {
    @Mock
    private Recipe currentRecipe;
    @Mock
    private ImageLoader imageLoader;
    @Mock
    private RecipeMainPresenter presenter;

    private RecipeMainView view;

    @Mock
    private RecipeMainActivity mockActivity;

    private ShadowActivity shadowAcivity;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        mockActivity = Mockito.spy(Robolectric.buildActivity(RecipeMainActivity.class).create().visible().get());
        view = (RecipeMainView) mockActivity;

        shadowAcivity = shadowOf(mockActivity);
    }

    @Test
    public void testOnActivityCreated_getNextRecipe() throws Exception {
        mockActivity.onDestroy();
        mockActivity.onCreate(null);
        verify(mockActivity).setupPresenter();
    }

    @Test
    public void testOnActivityDestroyed_destroyPresenter() throws Exception {
        mockActivity.onDestroy();
        verify(mockActivity).destroyPresenter();
    }

    @Test
    public void testLogoutMenuClicked_ShouldLaunchLoginActivity() throws Exception {
        shadowAcivity.clickMenuItem(R.id.action_logout);
        Intent intent = shadowAcivity.peekNextStartedActivity();
        assertEquals(new ComponentName(mockActivity, LoginActivity.class), intent.getComponent());
    }

    @Test
    public void testListMenuClicked_ShouldLaunchRecipeListActivity() throws Exception {
        shadowAcivity.clickMenuItem(R.id.action_list);
        Intent intent = shadowAcivity.peekNextStartedActivity();
        assertEquals(new ComponentName(mockActivity, RecipeListActivity.class), intent.getComponent());
    }

    @Test
    public void testKeepButtonClicked_ShouldSaveRecipe() throws Exception {
        mockActivity.onDestroy();
        mockActivity.onCreate(null);
        mockActivity.setRecipe(currentRecipe);

        ImageButton imgKeep = (ImageButton) mockActivity.findViewById(R.id.imgKeep);
        imgKeep.performClick();
        verify(mockActivity).onKeep();
    }

    @Test
    public void testDismissButtonClicked_ShouldDismissRecipe() throws Exception {
        mockActivity.onDestroy();
        mockActivity.onCreate(null);
        ImageButton imgDismiss = (ImageButton) mockActivity.findViewById(R.id.imgDismiss);
        imgDismiss.performClick();
        verify(mockActivity).onDismiss();
    }

    @Test
    public void testOnSwipeToKeep_ShouldSaveRecipe() throws Exception {
        mockActivity.onDestroy();
        mockActivity.onCreate(null);
        mockActivity.setRecipe(currentRecipe);

        ImageView imgRecipe = (ImageView) mockActivity.findViewById(R.id.imgRecipe);
        ShadowImageView shadowImage = (ShadowImageView) Shadow.extract(imgRecipe);
        shadowImage.performSwipe(200, 200, 500, 250, 50);
        verify(mockActivity).onKeep();
    }


    @Test
    public void testOnSwipeToDismiss_ShouldDiscardRecipe() throws Exception {
        mockActivity.onDestroy();
        mockActivity.onCreate(null);
        ImageView imgRecipe = (ImageView) mockActivity.findViewById(R.id.imgRecipe);
        ShadowImageView shadowImage = (ShadowImageView) Shadow.extract(imgRecipe);
        shadowImage.performSwipe(200, 200, -500, 250, 50);
        verify(mockActivity).onDismiss();
    }

    @Test
    public void testShowProgress_progressBarShouldBeVisible() throws Exception {
        view.showProgress();

        ProgressBar progressBar = (ProgressBar) mockActivity.findViewById(R.id.progressBar);
        assertEquals(View.VISIBLE, progressBar.getVisibility());
    }

    @Test
    public void testHideProgress_progressBarShouldBeGone() throws Exception {
        view.hideProgress();

        ProgressBar progressBar = (ProgressBar) mockActivity.findViewById(R.id.progressBar);
        assertEquals(View.GONE, progressBar.getVisibility());
    }

    @Test
    public void testShowUIElements_buttonsShouldBeVisible() throws Exception {
        view.showUIElements();

        ImageButton imgKeep = (ImageButton) mockActivity.findViewById(R.id.imgKeep);
        ImageButton imgDismiss = (ImageButton) mockActivity.findViewById(R.id.imgDismiss);

        assertEquals(View.VISIBLE, imgKeep.getVisibility());
        assertEquals(View.VISIBLE, imgDismiss.getVisibility());
    }

    @Test
    public void testHideUIElements_buttonsShouldBeGone() throws Exception {
        view.hideUIElements();

        ImageButton imgKeep = (ImageButton) mockActivity.findViewById(R.id.imgKeep);
        ImageButton imgDismiss = (ImageButton) mockActivity.findViewById(R.id.imgDismiss);

        assertEquals(View.GONE, imgKeep.getVisibility());
        assertEquals(View.GONE, imgDismiss.getVisibility());
    }

    @Test
    public void testSetRecipe_ImageLoaderShouldBeCalled() throws Exception {
        String url = "http://galileo.edu";
        when(currentRecipe.getImageURL()).thenReturn(url);

        view.setRecipe(currentRecipe);
        ImageView imgRecipe = (ImageView) mockActivity.findViewById(R.id.imgRecipe);
        verify(mockActivity).setRecipe(currentRecipe);

    }

    @Test
    public void testSaveAnimation_AnimationShouldBeStarted() throws Exception {
        view.saveAnimation();

        ImageView imgRecipe = (ImageView) mockActivity.findViewById(R.id.imgRecipe);
        assertNotNull(imgRecipe.getAnimation());
        assertTrue(imgRecipe.getAnimation().hasStarted());
    }

    @Test
    public void testDismissAnimation_AnimationShouldBeStarted() throws Exception {
        view.dismissAnimation();

        ImageView imgRecipe = (ImageView) mockActivity.findViewById(R.id.imgRecipe);
        assertNotNull(imgRecipe.getAnimation());
        assertTrue(imgRecipe.getAnimation().hasStarted());
    }
}

