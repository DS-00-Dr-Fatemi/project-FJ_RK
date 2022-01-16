package Waiter;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        Salon salon = new Salon();
//        char[][] A = {{'#','+','#','+','$','+','+','+','+','#'},
//                        {'+','#','+','+','#','+','#','#','#','+'},
//                        {'a','+','#','#','#','+','+','#','+','+'},
//                        {'+','#','+','+','#','+','b','+','#','+'}};
//        salon.mapToGraph(A, A.length, A[0].length);//TODO test maptograph
        int n = sc.nextInt();
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

        Salon salon = new Salon();
        salon.mapToGraph(array, n, m);
    }
}
