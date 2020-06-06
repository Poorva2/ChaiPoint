package main.model;

//import main.services.DisplayService;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public abstract class Recipe {
//    protected List<Ingredient> ingredients;
//
//    public Recipe() {}
//
//    public abstract void prepare(Outlet outlet, DisplayService displayService) throws InterruptedException, IOException;
//
//    public void prepare() {
//        // getRecipe -> List<RecipeSStep
//
//
//
//    }
//    public List<Ingredient> getIngredients() {
//        return ingredients;
//    }
//}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Recipe
{
    public abstract List<RecipeStep> getRecipeSteps();

    public abstract String getName();

    public List<Ingredient> getIngredients() {
        List<RecipeStep> steps = getRecipeSteps();
        Map<IngredientType, Integer> amounts = new HashMap<>();
        for(RecipeStep step : steps) {
            IngredientType ingredientType = step.getIngredientType();
            Integer quantity = step.getQuantity();
            Integer previousQuantity = amounts.get(ingredientType);
            if(previousQuantity == null) {
                previousQuantity = 0;
            }
            amounts.put(ingredientType, previousQuantity + quantity);
        }

        List<Ingredient> requirements = new ArrayList<>();
        for(Map.Entry<IngredientType, Integer> entry : amounts.entrySet()) {
            requirements.add(new Ingredient(entry.getKey(), entry.getValue()));
        }
        return requirements;
    }

    public void prepare(Outlet outlet) throws InterruptedException {
        List<RecipeStep> steps = getRecipeSteps();
        for(RecipeStep step : steps) {
            StepType stepType = step.getStepType();
            IngredientType ingredientType = step.getIngredientType();
            Integer quantity = step.getQuantity();
            if(stepType == StepType.HEAT_AND_ADD) {
                outlet.addHeated(ingredientType, quantity);
            } else {
                outlet.add(ingredientType, quantity);
            }
        }
        outlet.pour();
    }
}
