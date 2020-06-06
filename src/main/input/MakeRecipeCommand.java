package main.input;

import main.model.RecipeType;

public class MakeRecipeCommand implements Command{

    String recipeName;

    public MakeRecipeCommand(String  recipeName) {
        this.recipeName = recipeName;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.MAKE_RECIPE;
    }

    public RecipeType getRecipeType() {
        return RecipeType.valueOf(recipeName);
    }

    public String getRecipeName() {
        return recipeName;
    }

}
