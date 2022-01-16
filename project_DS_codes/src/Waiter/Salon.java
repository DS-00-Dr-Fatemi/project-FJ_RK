package Waiter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Salon {
    ArrayList<Table> tables = new ArrayList<>();
    Graph1<cell> graph = new Graph1<cell>();
    Map<Table,Person> chideman_peson = new HashMap<>();
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
        System.out.println(graph.toString());
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
}
