package main.model.recipes;

import main.model.*;

import java.util.Arrays;
import java.util.List;

public class HotMilk extends Recipe {

    int milkInMl=10;

    @Override
    public List<RecipeStep> getRecipeSteps() {
        return Arrays.asList(new RecipeStep(StepType.HEAT_AND_ADD, IngredientType.MILK, milkInMl));
    }

    @Override
    public String getName() {
        return RecipeType.HOT_MILK.name();
    }
}
