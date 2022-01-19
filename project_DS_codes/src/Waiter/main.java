package Waiter;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //entering foods
        System.out.println("----Today's Foods----");
        Salon salon = new Salon();
        System.out.println("number of Foods: ");
        int nOfFoods = sc.nextInt();
        sc.nextLine();
        System.out.println("foods: ");
        for (int i = 0; i < nOfFoods; i++) {
            String name = sc.next();
            String time = sc.next();
            Food food = new Food(name, Integer.parseInt(time));
            salon.menuFoods.add(food);
        }
        System.out.println("--map--");
        //entering map of salon
        System.out.println("width: ");
        int n = sc.nextInt();
        System.out.println("height: ");
        int m = sc.nextInt();
        sc.nextLine();
        char[][] array = new char[n][m];

        for (int i = 0; i < n; i++) {
            String inputLine = sc.nextLine();
            char[] line = new char[inputLine.length()];

            for (int j = 0; j < m; j++) {
                line[j] = inputLine.charAt(j);
            }
            array[i] = line;
        }
        salon.mapToGraph(array, n, m);
        salon.quickSort(salon.tables, 0, salon.tables.size() - 1);

        //entering costumers
        System.out.println("------Opening the Restaurant------");
        System.out.println("name and time of eating: ");
        String str = sc.nextLine();
        while (!str.equals("end")){

            String[] input = str.split(" ");

            int k = 1;
            System.out.println("**FOODS**");
            for (Food f: salon.menuFoods){
                System.out.println(k + "." + f.name + " * time of cook: " + f.cookingTime);
                k++;
            }
            System.out.println("enter number of food you want: ");
            int foodNum = sc.nextInt();
            sc.nextLine();

            salon.orderedFoods.add(salon.menuFoods.get(foodNum - 1));
            Person person = new Person(input[0], salon.menuFoods.get(foodNum - 1), Integer.parseInt(input[1]));
            salon.people.add(person);
            System.out.println("name and time of eating or end? ");
            str = sc.nextLine();
        }
        salon.enteringPeople();
        System.out.println();
        salon.exitingPeople();
        System.out.println("------Closing the Restaurant------");
    }
}
/*
#+#+$++++#
+#++#+###+
a+###++#++
+#++#+b+#+
#+++#+++##
++#+##++++
##+#+##+++
++++#+#+##
++#++##+#+
d####+++#+
*/
