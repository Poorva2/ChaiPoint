package main.services;

import main.model.Outlet;
import main.model.Recipe;
import main.model.Stock;

import java.io.IOException;

public class MakeRecipeService implements Runnable {

    Recipe recipe;
    OutletService outletService;
    DisplayService displayService;

    public MakeRecipeService(Recipe recipe, OutletService outletService, DisplayService displayService) {
        this.recipe = recipe;
        this.outletService = outletService;
        this.displayService = displayService;
    }

    @Override
    public void run() {
        Stock.reduceSize(recipe.getIngredients());
        try {
            Outlet freeOutlet = outletService.getFreeOutlet();
            recipe.prepare(freeOutlet);
            displayService.displaySuccess(recipe.getName() + " is ready on lane : " + freeOutlet.getLaneNumber());
            outletService.addFreeOutlet(freeOutlet);
        } catch (InterruptedException | IOException e) {
            try {
                displayService.displayError("Could not create. Please retry");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
