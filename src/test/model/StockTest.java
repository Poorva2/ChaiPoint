package test.model;

import main.model.Ingredient;
import main.model.IngredientType;
import main.model.Stock;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

public class StockTest {

    @Test
    public void test_addIngredient() {
        Stock.addIngredient(IngredientType.MILK, 10);
        Ingredient milk = new Ingredient(IngredientType.MILK, 5);
        Ingredient water = new Ingredient(IngredientType.WATER, 5);

        Assert.assertTrue(Stock.isAvailable(Collections.singletonList(milk)));
        Assert.assertFalse(Stock.isAvailable(Collections.singletonList(water)));
    }

    @Test
    public void test_reduceSize() {
        Stock.clearStock();
        Stock.addIngredient(IngredientType.MILK, 10);
        Stock.reduceIngredient(IngredientType.MILK, 5);

        Ingredient milk = new Ingredient(IngredientType.MILK, 10);

        Assert.assertFalse(Stock.isAvailable(Collections.singletonList(milk)));
    }

    @Test
    public void test_reduceIngredients() {
        Stock.clearStock();
        Stock.addIngredient(IngredientType.MILK, 10);
        Stock.reduceSize(Collections.singletonList(new Ingredient(IngredientType.MILK, 5)));

        Ingredient milk = new Ingredient(IngredientType.MILK, 10);

        Assert.assertFalse(Stock.isAvailable(Collections.singletonList(milk)));
    }
}