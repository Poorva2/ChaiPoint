package main.model;

public class Ingredient {
    IngredientType ingredientType;
    int size;

    public Ingredient(IngredientType ingredientType, int size) {
        this.ingredientType = ingredientType;
        this.size = size;
    }

    public IngredientType getIngredientType() {
        return ingredientType;
    }

    public int getSize() {
        return size;
    }
}
