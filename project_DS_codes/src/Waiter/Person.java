package Waiter;

public class Person {
    String name;
    Food food;
    int timeOfEat;
    int allTime;

    public Person(String name, Food food, int timeOfEat) {
        this.name = name;
        this.food = food;
        this.timeOfEat = timeOfEat;
        allTime=timeOfEat+food.getCookingTime();
    }
}
