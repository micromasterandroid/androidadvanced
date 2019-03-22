package edu.galileo.android.facebookrecipes.recipelist;

import org.junit.Test;
import org.mockito.Mock;

import edu.galileo.android.facebookrecipes.BaseTest;
import edu.galileo.android.facebookrecipes.entities.Recipe;

import static org.mockito.Mockito.verify;

/**
 * Created by ykro.
 */
public class StoredRecipesInteractorImplTest extends BaseTest {
    @Mock
    private Recipe recipe;
    @Mock
    private RecipeListRepository repository;

    private StoredRecipesInteractorImpl interactor;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        interactor = new StoredRecipesInteractorImpl(repository);
    }

    @Test
    public void testExecuteUpdate_CallsRepository() throws Exception {
        interactor.executeUpdate(recipe);
        verify(repository).updateRecipe(recipe);

    }

    @Test
    public void testExecuteDelete_CallsRepository() throws Exception {
        interactor.executeDelete(recipe);
        verify(repository).removeRecipe(recipe);
    }

}