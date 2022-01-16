package Kitchen;

import java.util.ArrayList;

public class Kitchen {
    ArrayList<Food> foods = new ArrayList<>();

    public void addFood(Food food){
        foods.add(food);
    }

    public Food removeFood(int foodNum){
        Food food = foods.get(foodNum);
        foods.remove(foodNum);
        return food;
    }

    public Food getFoodWithMaxRequisites(){
        int max = 0;
        Food food = null;
        for (Food f : foods){
            if (f.getNumOfRequisites() > max){
                max = f.getNumOfRequisites();
                food = f;
            }
        }
        return food;
    }

    public Food getFoodWithLessTime(){
        Food food = foods.get(0);
        int min = foods.get(0).getTotalTime();

        for (Food f : foods){
            if (f.getTotalTime() < min){
                min = f.getTotalTime();
                food = f;
            }
        }

        return food;
    }

    public Food getFoodWithMostTime(){
        Food food = foods.get(0);
        int max = foods.get(0).getTotalTime();

        for (Food f : foods){
            if (f.getTotalTime() > max){
                max = f.getTotalTime();
                food = f;
            }
        }

        return food;
    }
}
