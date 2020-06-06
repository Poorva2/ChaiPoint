package main.model.recipes;

import main.model.*;

import java.util.Arrays;
import java.util.List;

public class HotWater extends Recipe {

    int waterInMl=10;

    @Override
    public List<RecipeStep> getRecipeSteps() {
        return Arrays.asList(new RecipeStep(StepType.HEAT_AND_ADD, IngredientType.WATER, waterInMl));
    }

    @Override
    public String getName() {
        return RecipeType.HOT_WATER.name();
    }
}
