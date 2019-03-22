package edu.galileo.android.facebookrecipes.recipemain;

import org.junit.Test;
import org.mockito.Mock;

import edu.galileo.android.facebookrecipes.BaseTest;
import edu.galileo.android.facebookrecipes.entities.Recipe;
import edu.galileo.android.facebookrecipes.libs.base.EventBus;
import edu.galileo.android.facebookrecipes.recipemain.events.RecipeMainEvent;
import edu.galileo.android.facebookrecipes.recipemain.ui.RecipeMainView;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ykro.
 */
public class RecipeMainPresenterImplTest extends BaseTest {
    @Mock
    private EventBus eventBus;
    @Mock
    private RecipeMainView view;
    @Mock
    private SaveRecipeInteractor saveInteractor;
    @Mock
    private GetNextRecipeInteractor getNextInteractor;
    @Mock
    Recipe recipe;
    @Mock
    RecipeMainEvent event;

    private RecipeMainPresenterImpl presenter;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        presenter = new RecipeMainPresenterImpl(eventBus, view, saveInteractor, getNextInteractor);
    }

    @Test
    public void testOnCreate_subscribedToEventBus() throws Exception {
        presenter.onCreate();
        verify(eventBus).register(presenter);
    }

    @Test
    public void testOnDestroy_UnsubscribedToEventBus() throws Exception {
        presenter.onDestroy();
        verify(eventBus).unregister(presenter);
        assertNull(presenter.getView());
    }

    @Test
    public void testSaveRecipe_executeSaveInteractor() throws Exception {
        presenter.saveRecipe(recipe);

        assertNotNull(presenter.getView());
        verify(view).saveAnimation();
        verify(view).hideUIElements();
        verify(view).showProgress();
        verify(saveInteractor).execute(recipe);
    }

    @Test
    public void testDismissRecipe_executeGetNextRecipeInteractor() throws Exception {
        presenter.dismissRecipe();

        assertNotNull(presenter.getView());
        verify(view).dismissAnimation();
    }

    @Test
    public void testGetNextRecipe_executeGetNextRecipeInteractor() throws Exception {
        presenter.getNextRecipe();

        assertNotNull(presenter.getView());
        verify(view).hideUIElements();
        verify(view).showProgress();
        verify(getNextInteractor).execute();

    }

    @Test
    public void testOnEvent_hasError() throws Exception {
        String errorMsg = "error";

        when(event.getError()).thenReturn(errorMsg);
        presenter.onEventMainThread(event);

        assertNotNull(presenter.getView());
        verify(view).hideProgress();
        verify(view).onGetRecipeError(event.getError());
    }

    @Test
    public void testOnNextEvent_setRecipeOnView() throws Exception {
        when(event.getType()).thenReturn(RecipeMainEvent.NEXT_EVENT);
        when(event.getRecipe()).thenReturn(recipe);

        presenter.onEventMainThread(event);

        assertNotNull(presenter.getView());
        verify(view).setRecipe(event.getRecipe());
    }


    @Test
    public void testOnSaveEvent_notifyViewAndCallGetNextRecipe() throws Exception {
        when(event.getType()).thenReturn(RecipeMainEvent.SAVE_EVENT);

        presenter.onEventMainThread(event);

        assertNotNull(presenter.getView());
        verify(view).onRecipeSaved();
        verify(getNextInteractor).execute();
    }

    @Test
    public void testImageReady_updateUI() throws Exception {
        presenter.imageReady();

        assertNotNull(presenter.getView());
        verify(view).hideProgress();
        verify(view).showUIElements();
    }

    @Test
    public void testImageError_updateUI() throws Exception {
        String error = "error";
        presenter.imageError(error);

        assertNotNull(presenter.getView());
        verify(view).onGetRecipeError(error);
    }

    @Test
    public void testGetView_returnsView() throws Exception {
        assertEquals(view, presenter.getView());
    }
}
