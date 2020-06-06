package main.input;

import main.model.IngredientType;

public class FillIngredientCommand implements Command{
    IngredientType ingredientType;
    int size;

    public FillIngredientCommand(IngredientType ingredientType, int size) {
        this.ingredientType = ingredientType;
        this.size = size;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.FILL_INGREDIENT;
    }


    public IngredientType getIngredientType() {
        return ingredientType;
    }

    public int getSize() {
        return size;
    }
}
