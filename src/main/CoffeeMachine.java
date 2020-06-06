package main;

import main.input.Command;
import main.input.CommandType;
import main.input.FillIngredientCommand;
import main.input.MakeRecipeCommand;
import main.model.IngredientType;
import main.model.Recipe;
import main.model.RecipeType;
import main.model.Stock;
import main.model.recipes.RecipeFactory;
import main.services.*;

import java.io.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * A CoffeeMachine has an inputService which gives it inputs, an outletService which helps it decide which outlet out
 * of the N is to be used for pouring the ready reecipe and the displayService which is used to display messages.
 */

public class CoffeeMachine {

    private static String previousStockPath = "src/resources/stock.txt";
    InputService inputService;
    OutletService outletService;
    DisplayService displayService;

    public CoffeeMachine(InputService inputService, OutletService outletService, DisplayService displayService) {
        this.inputService = inputService;
        this.outletService = outletService;
        this.displayService = displayService;
    }

//    public static void main(String[] args) throws InterruptedException, IOException {
//        OutletService outletService = new OutletService(3);
//        DisplayService displayService = new DisplayService(System.out);
//        InputService inputService = new InputService(System.in, displayService);
//
//        CoffeeMachine coffeeMachine = new CoffeeMachine(inputService, outletService, displayService);
//        coffeeMachine.runMachine();
//    }

    /**
     * This method continuously polls the inputService.poll method which returns a command for it to run. It will
     * create n threads and assign task to a free thread. If the command is to make a recipe, it will call the makeRecipe
     * to decide whether or not we have the ingredients in stock tto make this, if yes, it will assign a threadd and
     * start execution.
     * @throws InterruptedException
     */
    public void runMachine() throws InterruptedException, IOException {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(outletService.getOutletCount());
        generateStock();
        while(true) {

            Command command;
            try {
                command = inputService.poll();
            } catch (Exception e) {
                continue;
            }

            if(command.getCommandType() == CommandType.FILL_INGREDIENT) {
                fillIngredient(command, threadPoolExecutor);
            }

            if(command.getCommandType() == CommandType.MAKE_RECIPE) {
                makeRecipe(command, threadPoolExecutor);
            }

            if(command.getCommandType() == CommandType.STOP_MACHINE) {
                break;
            }
        }
        Stock.writeStockToFile(new File(previousStockPath));
        threadPoolExecutor.shutdown();
        threadPoolExecutor.awaitTermination(1, TimeUnit.MINUTES);
    }

    public void fillIngredient(Command command, ThreadPoolExecutor threadPoolExecutor) {
        FillIngredientCommand fillIngredientCommand = (FillIngredientCommand) command;
        FillIngredientService fillIngredientService = new FillIngredientService(fillIngredientCommand.getIngredientType(), fillIngredientCommand.getSize(), displayService);

        threadPoolExecutor.execute(fillIngredientService);
    }

    public void makeRecipe(Command command, ThreadPoolExecutor threadPoolExecutor) throws IOException {
        MakeRecipeCommand makeRecipeCommand = (MakeRecipeCommand)command;
        RecipeType recipeType = makeRecipeCommand.getRecipeType();

        Recipe recipe = RecipeFactory.getRecipe(recipeType);
        boolean isAvailable = Stock.isAvailable(recipe.getIngredients());

        if(!isAvailable) {
            displayService.displayError("Refill the empty ingredients in coffee machine");
            return;
        }

        MakeRecipeService makeRecipeService = new MakeRecipeService(recipe, outletService, displayService);
        threadPoolExecutor.execute(makeRecipeService);
    }

    /**
     * generate stock. If the machine has written a prevous stock, read from it.
     * @throws IOException
     */
    public static void generateStock() throws IOException {
        File file = new File(previousStockPath);
        if(file.exists()) {
            readPreviousStock(file);
            return;
        }

        Stock.addIngredient(IngredientType.MILK , 1000);
        Stock.addIngredient(IngredientType.WATER , 10000);
        Stock.addIngredient(IngredientType.COFFEE_SYRUP , 100);
        Stock.addIngredient(IngredientType.TEA_SYRUP , 100);
        Stock.addIngredient(IngredientType.SUGAR_SYRUP , 100);
        Stock.addIngredient(IngredientType.ELAICHI_SYRUP , 100);
        Stock.addIngredient(IngredientType.GINGER_SYRUP , 100);
    }

    private static void readPreviousStock(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        while(true) {
            String line = bufferedReader.readLine();
            System.out.println("Output line : " + line);
            if(line.equals("")) {
                break;
            }
            String[] split = line.split(":");
            Stock.addIngredient(IngredientType.valueOf(split[0]), Integer.parseInt(split[1]));
        }
        file.delete();
    }
}
