package test.model;

import main.model.Outlet;
import main.model.Recipe;
import main.model.recipes.ElaichiTea;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class RecipeTest {

    @Mock
    Outlet outlet;

    @Before
    public void before() {
        outlet = Mockito.mock(Outlet.class);
    }

    @Test
    public void test_elaichi_tea() throws InterruptedException{
        Recipe elaichiTea = new ElaichiTea();
        elaichiTea.prepare(outlet);
        
        Mockito.verify(outlet, Mockito.times(1)).pour();
        Mockito.verify(outlet, Mockito.times(3)).add(Mockito.any(), Mockito.any());
        Mockito.verify(outlet, Mockito.times(2)).addHeated(Mockito.any(), Mockito.any());
    }
}