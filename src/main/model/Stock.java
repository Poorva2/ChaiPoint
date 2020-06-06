package main.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to keep track of all the resources in the coffee machine.
 */
public class Stock {
    static Map<IngredientType, Integer> available = new HashMap<>();

    /**
     * Checks if the list of ingredients is in stock.
     * @param ingredients
     * @return
     */
    public static boolean isAvailable(List<Ingredient> ingredients) {
        for(Ingredient ingredient : ingredients) {
            if(!available.containsKey(ingredient.getIngredientType())) {
                return false;
            }

            int currentSize = available.get(ingredient.getIngredientType());
            if(currentSize < ingredient.getSize()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Reduces the size of ingredients in the stock
     * @param ingredients
     */
    public static void reduceSize(List<Ingredient> ingredients) {
        for(Ingredient ingredient : ingredients) {
            reduceIngredient(ingredient.getIngredientType(), ingredient.getSize());
        }
    }

    public static void reduceIngredient(IngredientType ingredientType, int size) {
        int currentSize = available.get(ingredientType);
        available.put(ingredientType, currentSize-size);
    }

    public static void addIngredient(IngredientType ingredientType, int size) {
        System.out.println("Adding " + ingredientType.name() + " of size " + size);
        if(!available.containsKey(ingredientType)) {
            available.put(ingredientType, size);
            return;
        }

        int currentSize = available.get(ingredientType);
        available.put(ingredientType, size+currentSize);
    }

    public static void clearStock() {
        available.clear();
    }

    public static void writeStockToFile(File file) throws IOException {
        StringBuilder currentStock = new StringBuilder();

        for(Map.Entry<IngredientType, Integer> entry : available.entrySet()) {
            String line = "";
            line += entry.getKey().name();
            line += ":";
            line += entry.getValue().toString();
            currentStock.append(line);
            currentStock.append("\n");
        }

        currentStock.append("\n");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(currentStock.toString().getBytes());
    }
}
