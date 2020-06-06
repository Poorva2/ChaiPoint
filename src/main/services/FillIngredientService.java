package main.services;

import main.model.IngredientType;
import main.model.Stock;

import java.io.IOException;

public class FillIngredientService implements Runnable {
    DisplayService displayService;
    IngredientType ingredientType;
    int size;

    public FillIngredientService(IngredientType ingredientType, int size, DisplayService displayService) {
        this.ingredientType = ingredientType;
        this.size = size;
        this.displayService = displayService;
    }

    @Override
    public void run() {
        Stock.addIngredient(ingredientType, size);
        try {
            displayService.displaySuccess("Add Ingredient : Added " + size + " ml of " + ingredientType.name());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
