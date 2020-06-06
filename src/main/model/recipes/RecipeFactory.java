package main.model.recipes;

import main.model.Recipe;
import main.model.RecipeType;

public class RecipeFactory {
    public static Recipe getRecipe(RecipeType recipeType) {
        switch (recipeType) {

            case HOT_MILK: return new HotMilk();
            case PLAIN_COFFEE: return new PlainCoffee();
            case PLAIN_TEA: return new PlainTea();
            case GINGER_TEA: return new GingerTea();
            case ELAICHI_TEA: return new ElaichiTea();

            default: return new HotWater();
        }
    }
}
