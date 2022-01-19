package Waiter;

public class Food {
    int cookingTime;
    String name;

    public Food(String name,int cookingTime) {
        this.cookingTime = cookingTime;
        this.name = name;
    }
    public int getCookingTime(){
        return this.cookingTime;
    }
}
