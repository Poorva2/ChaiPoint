package main.model;

public class RecipeStep
{
    private StepType stepType;
    private IngredientType ingredientType;
    private Integer quantity;

    public RecipeStep(StepType stepType, IngredientType ingredientType, Integer quantity) {
        this.stepType = stepType;
        this.ingredientType = ingredientType;
        this.quantity = quantity;
    }

    public StepType getStepType() {
        return stepType;
    }

    public IngredientType getIngredientType() {
        return ingredientType;
    }

    public Integer getQuantity() {
        return quantity;
    }
}