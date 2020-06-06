package main.services;

import main.input.Command;
import main.input.FillIngredientCommand;
import main.input.MakeRecipeCommand;
import main.input.StopMachineCommand;
import main.model.IngredientType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This class will help in taking input to the CoffeeMachine class. CoffeeMachine class will continously call the poll
 * method and it will return a command for coffee machine to run. The input can be taken from any input stream like
 * System.in or a file. The class also has a display service to display the information and messages
 */
public class InputService {
    BufferedReader reader;
    DisplayService displayService;

    public InputService(InputStream inputStream, DisplayService displayService) {
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
        this.displayService = displayService;
    }

    /**
     * This metthod is called by CoffeeMachine. It will continously get inout from input stream and create a command out
     * of it. It can return three types of commands. First to make a recipe. Second to fill an ingredient. Third to
     * close the machine.
     * @return
     * @throws Exception
     */
    public Command poll() throws Exception {

        displayService.displayMessage("Please select the operation");
        displayService.displayMessage("MakeRecipe : 1, FillIngredient : 2");

        String operation = reader.readLine();

        if(operation.equals("1")) {
            return getMakeRecipeCommand(reader);
        }
        if(operation.equals("2")) {
            return getFillIngredientCommand(reader);
        }
        return new StopMachineCommand();
    }

    Command getMakeRecipeCommand(BufferedReader reader) throws IOException {

        displayService.displayMessage("Please select the recipe of your choice");
        displayService.displayMessage("PLAIN_TEA , PLAIN_COFFEE , HOT_WATER , HOT_MILK , GINGER_TEA , ELAICHI_TEA ");

        String choice = reader.readLine();

        return new MakeRecipeCommand(choice);
    }

    Command getFillIngredientCommand(BufferedReader reader) throws IOException {

        displayService.displayMessage("Please select the ingredient you want to add to the machine and its size");
        displayService.displayMessage("WATER, MILK , TEA_SYRUP , COFFEE_SYRUP , SUGAR_SYRUP , GINGER_SYRUP , ELAICHI_SYRUP");

        String ingredient = reader.readLine();
        int size = Integer.parseInt(reader.readLine());

        IngredientType ingredient_Type_ = IngredientType.valueOf(ingredient);
        return new FillIngredientCommand(ingredient_Type_, size);
    }
}
