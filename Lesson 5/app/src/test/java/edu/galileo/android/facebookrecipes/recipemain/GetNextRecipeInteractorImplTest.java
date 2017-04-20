package edu.galileo.android.facebookrecipes.recipemain;

import org.junit.Test;
import org.mockito.Mock;

import edu.galileo.android.facebookrecipes.BaseTest;

import static org.mockito.Mockito.verify;

/**
 * Created by ykro.
 */
public class GetNextRecipeInteractorImplTest extends BaseTest {
    @Mock
    private RecipeMainRepository repository;
    private GetNextRecipeInteractorImpl interactor;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        interactor = new GetNextRecipeInteractorImpl(repository);
    }

    @Test
    public void testExecute_callRepository() throws Exception {
        interactor.execute();
        verify(repository).getNextRecipe();
    }
}
