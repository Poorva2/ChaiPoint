package main.model.recipes;

import main.model.*;

import java.util.Arrays;
import java.util.List;

public class PlainCoffee extends Recipe {

    int coffeeInMl=1;
    int milkInMl=10;
    int waterInMl=10;
    int sugarInMl=1;

    @Override
    public List<RecipeStep> getRecipeSteps() {
        return Arrays.asList(new RecipeStep(StepType.HEAT_AND_ADD, IngredientType.WATER, waterInMl),
                new RecipeStep(StepType.HEAT_AND_ADD, IngredientType.MILK, milkInMl),
                new RecipeStep(StepType.ADD, IngredientType.SUGAR_SYRUP, sugarInMl),
                new RecipeStep(StepType.ADD, IngredientType.COFFEE_SYRUP, coffeeInMl));
    }

    @Override
    public String getName() {
        return RecipeType.PLAIN_COFFEE.name();
    }
}
