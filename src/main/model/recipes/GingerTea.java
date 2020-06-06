package main.model.recipes;

import main.model.*;

import java.util.Arrays;
import java.util.List;

public class GingerTea extends Recipe {

    int teaInMl=1;
    int milkInMl=10;
    int waterInMl=10;
    int sugarInMl=1;
    int gingerInMl=1;

    @Override
    public List<RecipeStep> getRecipeSteps() {
        return Arrays.asList(new RecipeStep(StepType.HEAT_AND_ADD, IngredientType.WATER, waterInMl),
                new RecipeStep(StepType.HEAT_AND_ADD, IngredientType.MILK, milkInMl),
                new RecipeStep(StepType.ADD, IngredientType.SUGAR_SYRUP, sugarInMl),
                new RecipeStep(StepType.ADD, IngredientType.GINGER_SYRUP, gingerInMl),
                new RecipeStep(StepType.ADD, IngredientType.TEA_SYRUP, teaInMl));
    }

    @Override
    public String getName() {
        return RecipeType.GINGER_TEA.name();
    }
}
