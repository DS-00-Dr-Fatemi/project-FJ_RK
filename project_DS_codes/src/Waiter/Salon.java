package Waiter;
import java.util.*;

public class Salon {
    ArrayList<Food> menuFoods = new ArrayList<>();
    Queue<Food> orderedFoods = new PriorityQueue<>(new FoodComparator());

    Queue<Person> people = new LinkedList<>();
    Queue<Person> costumer = new PriorityQueue<>(new PersonComparator());

    ArrayList<Table> tables = new ArrayList<>();
    Graph1<cell> graph = new Graph1<cell>();
    Map<Person, Table> chideman_salon = new HashMap <>();
    cell[][] nodeFinder;

    void mapToGraph(char[][] map,int n,int m){
        nodeFinder = new cell[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != '#'){
                    if (map[i][j] == '$'){
                        Kitchen kitchen = new Kitchen(map[i][j]);
                        graph.addVertex(kitchen);
                        nodeFinder[i][j] = kitchen;
                    }
                    else if (map[i][j] == '+'){
                        cell cell = new cell();
                        graph.addVertex(cell);
                        nodeFinder[i][j] = cell;
                    }
                    else {
                        Table table = new Table(map[i][j], false);
                        tables.add(table);
                        graph.addVertex(table);
                        nodeFinder[i][j] = table;
                    }
                }
            }
        }

        findNeighbors();
//        System.out.println(graph.toString());
    }

    void findNeighbors(){
        for (int i = 0; i < nodeFinder.length; i++) {
            for (int j = 0; j < nodeFinder[0].length; j++) {
                if (nodeFinder[i][j] != null){
                    for ( int r = -1; r <= 1; r++ )
                    {
                        for ( int c = -1; c <= 1; c++ )
                        {
                            try {
                                if (nodeFinder[i + r][j + c] != null)
                                    graph.addEdge(nodeFinder[i][j], nodeFinder[i + r][j + c],true);
                            } catch (ArrayIndexOutOfBoundsException ignored) {

                            }
                        }
                    }
                }
            }
        }
    }

    static void swap(ArrayList<Table> arr, int i, int j) {
        Table temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }
    static int partition(ArrayList<Table> arr, int low, int high) {
        Table pivot = arr.get(high);

        int i = (low - 1);

        for(int j = low; j <= high - 1; j++)
        {

            if (arr.get(j).name < pivot.name)
            {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }
    public void quickSort(ArrayList<Table> arr, int low, int high) {
        if (low < high)
        {

            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public void enteringPeople(){
        for (Table t: tables){
            if (!t.isFull && !people.isEmpty()){
                Person p = people.peek();
                assert p != null;
                System.out.println("+enter -> Costumer: " + p.name + " ordered: " + p.food.name
                        + " and sat at table: " + t.name);
                chideman_salon.put(p, t);
                costumer.add(people.poll());
            }
        }
//        int size = people.size();
//        int s = 0;
//        for (int i = 0; i < size; i++) {
//            if (!tables.get(s).isFull) {
//                chideman_salon.put(tables.get(s), people.peek());
//                assert people.peek() != null;
//                System.out.println("Customer: "+people.peek().name+" Ordered: "+people.peek().food.name+" and sat down at table: "+tables.get(s).name);
//                assert people.peek() != null;
//                tables.get(s).setTimeOfFull(people.peek().allTime,people.peek().name);
//                people.remove();
//                s++;
//            }
//        }
    }

    public void exitingPeople(){
        while (!costumer.isEmpty()){
            Person c = costumer.peek();
            System.out.println("-exit -> Costumer: " + c.name + " ate his/her food " +
                    "and left table " + chideman_salon.get(c));
            int index = tables.indexOf(chideman_salon.get(costumer.poll()));
            tables.get(index).emptyTable();
            chideman_salon.remove(c);

            if (!people.isEmpty()){
                assert people.peek() != null;
                System.out.println("+enter -> Costumer: " + people.peek().name + " ordered: " + people.peek().food.name
                        + " and sat at table: " + tables.get(index).name);
                chideman_salon.put(people.peek(), tables.get(index));
                costumer.add(people.poll());
            }
            System.out.println();
        }

    }

}
class FoodComparator implements Comparator<Food>{

    @Override
    public int compare(Food f1, Food f2) {
        return Integer.compare(f1.cookingTime, f2.cookingTime);
    }
}
class PersonComparator implements Comparator<Person>{

    @Override
    public int compare(Person p1, Person p2) {
        return Integer.compare(p1.allTime, p2.allTime);
    }
}