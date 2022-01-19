package Kitchen;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Kitchen kitchen = new Kitchen();

        exit:
        while (sc.hasNext()){
            String newFood = sc.nextLine();
            if (newFood.equals("end")){
                break exit;
            }
            Food food = new Food();
            while (true){
                String str = sc.nextLine();
                if (str.equals("End of instructions")){
                    break;
                }
                String[] words = str.split(" ");

                Requisite requisite = new Requisite(words[0], 0);
                food.graph.setVertex(requisite);

                for (int j = 1; j < words.length; j = j + 2) {

                    boolean existRequisite = false;
                    Requisite requisite2 = null;

                    for (Requisite re :food.graph.map.keySet()){
                        if (words[j].equals(re.name)){
                            re.time = Integer.parseInt(words[j + 1]);
                            existRequisite = true;
                            requisite2 = re;
                            break;
                        }
                    }
                    if (!existRequisite){
                        requisite2 = new Requisite(words[j], Integer.parseInt(words[j + 1]));
                    }

                    food.graph.setEdge(requisite2, requisite, false);
                }
            }
            food.name = food.graph.edges.get(food.graph.edges.size() - 1).destination.name;

            kitchen.addFood(food);
            ArrayList<Requisite> result = food.graph.topologicalSort3();
            for (Requisite r : result) {
                System.out.print(r.name + " ");
            }
            System.out.println("\nnum of requisites: " + food.getNumOfRequisites()
                    + " - total time of this food:" + food.getTotalTime());

            System.out.println();
        }

        ExitMenu:
        while (true){
            System.out.println("--------------MENU--------------" +
                    "\n1.DELETE A FOOD " +
                    "\n2.PRINT THE FOOD WITH MAX REQUISITES" +
                    "\n3.PRINT FOOD WITH LESS TIME AND MOST TIME TO COOK" +
                    "\n4.PRINT SORTED INSTRUCTIONS" +
                    "\n5.ADD A NEW RELATIONSHIP" +
                    "\n0.EXIT");
            System.out.println("\n\nenter a number: ");

            int menu = sc.nextInt();

            switch (menu){
                //DELETE A FOOD
                case 1: {
                    if (kitchen.foods.size() == 0){
                        System.out.println("There is no food!");
                    }
                    else {
                        int k = 1;
                        for (Food f: kitchen.foods){
                            System.out.println(k + "." + f.name);
                            k++;
                        }
                        System.out.println("choose a food (enter number):");
                        int foodNum = sc.nextInt();
                        Food food = kitchen.removeFood(foodNum - 1);
                        System.out.println(food.name + " is deleted!");
                    }
                    break;
                }
                //FOOD WITH MAX REQUISITES
                case 2: {
                    if (kitchen.foods.size() == 0){
                        System.out.println("There is no food!");
                    }
                    else {
                        System.out.println(kitchen.getFoodWithMaxRequisites().name +
                                " -> num of requisites: " + kitchen.getFoodWithMaxRequisites().getNumOfRequisites());
                    }
                    break;
                }
                //FOOD WITH LESS TIME AND MOST TIME TO COOK
                case 3: {
                    if (kitchen.foods.size() == 0){
                        System.out.println("There is no food!");
                    }
                    else {
                        System.out.println("food with less time to cook: " + kitchen.getFoodWithLessTime().name +
                                " -> its total time: " + kitchen.getFoodWithLessTime().getTotalTime() +
                                "\nfood with most time to cook: " + kitchen.getFoodWithMostTime().name +
                                " -> its total time: " + kitchen.getFoodWithMostTime().getTotalTime());
                    }

                    System.out.println();
                    break;
                }
                //SORTED INSTRUCTIONS
                case 4: {
                    if (kitchen.foods.size() == 0){
                        System.out.println("There is no food!");
                    }
                    else {
                        int k = 1;
                        for (Food f: kitchen.foods){
                            System.out.println(k + "." + f.name);
                            k++;
                        }
                        System.out.println("choose a food (enter number):");
                        int foodNum = sc.nextInt();

                        Food food = kitchen.foods.get(foodNum - 1);
                        ArrayList<Requisite> instructions = food.graph.topologicalSort3();
                        for (Requisite r : instructions) {
                            System.out.print(r.name + " ");
                        }
                    }

                    System.out.println();
                    break;
                }
                //ADD A NEW RELATIONSHIP
                case 5: {
                    if (kitchen.foods.size() == 0){
                        System.out.println("There is no food!");
                    }
                    else {
                        int k = 1;
                        for (Food f: kitchen.foods){
                            System.out.println(k + "." + f.name);
                            k++;
                        }
                        System.out.println("choose a food (enter number):");
                        int foodNum = sc.nextInt();

                        System.out.println("enter a new relationship: ");
                        sc.nextLine();
                        String str = sc.nextLine();
                        String[] words = str.split(" ");

                        boolean existRequisite = false;
                        Requisite requisite = null;

                        for (Requisite re : kitchen.foods.get(foodNum - 1).graph.map.keySet()){
                            if (words[0].equals(re.name)){
                                existRequisite = true;
                                requisite = re;
                                break;
                            }
                        }
                        if (!existRequisite){
                            requisite = new Requisite(words[0], 0);
                            kitchen.foods.get(foodNum - 1).graph.setVertex(requisite);
                        }

                        for (int j = 1; j < words.length; j = j + 2) {

                            existRequisite = false;
                            Requisite requisite2 = null;

                            int lastTime = 0;
                            for (Requisite re : kitchen.foods.get(foodNum - 1).graph.map.keySet()){
                                if (words[j].equals(re.name)){
                                    lastTime = re.time;
                                    re.time = Integer.parseInt(words[j + 1]);
                                    existRequisite = true;
                                    requisite2 = re;
                                    break;
                                }
                            }
                            if (!existRequisite){
                                requisite2 = new Requisite(words[j], Integer.parseInt(words[j + 1]));
                            }

                            kitchen.foods.get(foodNum - 1).addARelationship(requisite2, requisite, lastTime);
                        }
                    }

                    break;
                }
                //EXIT
                case 0: {
                    break ExitMenu;
                }
                default: {
                    System.out.println("you entered invalid number!");
                }
            }
        }

        System.out.println(kitchen.foods.size());
        for (Food f: kitchen.foods){
            System.out.println(f.name + " " + f.totalTime);
        }
    }
}
/*
New food:
a
b
c
d b 14 c 2
e b 14 c 2
f b 14 c 2
h d 5 f 12
g b 14 c 2 f 12 h 10
End of instructions
New food:
f
c
a f 6
b a 5 c 4
End of instructions
end
*/