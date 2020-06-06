package main.model;

public class Outlet
{
    int laneNumber;

    public Outlet(int laneNumber) {
        this.laneNumber = laneNumber;
    }

    public int getLaneNumber() {
        return laneNumber;
    }

    public void pour() throws InterruptedException {
        System.out.println("Pouring");
//        TimeUnit.SECONDS.sleep(1);
    }

    public void add(IngredientType ingredientType, Integer quantity) throws InterruptedException {
        System.out.println("Adding " + quantity + " ml of " + ingredientType.toString());
//        TimeUnit.SECONDS.sleep(1);
    }

    public void  addHeated(IngredientType ingredientType, Integer quantity) throws InterruptedException {
        System.out.println("Heating " + quantity + " ml of " + ingredientType.toString());
        System.out.println("Added " + quantity + " ml of " + ingredientType.toString());
//        TimeUnit.SECONDS.sleep(1);
    }
}
