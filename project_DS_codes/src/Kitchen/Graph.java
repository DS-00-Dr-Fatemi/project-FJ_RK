package Kitchen;
import java.util.*;

public class Graph<T> {
    Map<T, List<T>> map = new HashMap<>();
    Map<T, Boolean> visited = new HashMap<>();
    Map<T, Integer> inDegree = new HashMap<>();
    ArrayList<Edge<T>> edges = new ArrayList<>();
    String name;

    void setVertex(T v){
        if (!map.containsKey(v)){
            map.put(v, new LinkedList<>());
            inDegree.put(v, 0);
        }
    }

    void setEdge(T v, T u, boolean bidirectional){
        if (!map.containsKey(v)){
            setVertex(v);
        }
        if (!map.containsKey(u)){
            setVertex(u);
        }

        map.get(v).add(u);
        inDegree.replace(u, inDegree.get(u), inDegree.get(u) + 1);
        edges.add(new Edge<>(v, u));

        if (bidirectional) {
            map.get(u).add(v);
        }
    }

    boolean setEdgeWithoutCycle(T v, T u, boolean bidirectional){
        boolean vExistsBefore = true;
        if (!map.containsKey(v)){
            setVertex(v);
            vExistsBefore = false;
        }
        boolean uExistsBefore = true;
        if (!map.containsKey(u)){
            setVertex(u);
            uExistsBefore = false;
        }

        map.get(v).add(u);
        inDegree.replace(u, inDegree.get(u), inDegree.get(u) + 1);
        edges.add(new Edge<>(v, u));

        if (bidirectional) {
            map.get(u).add(v);
        }

        //check cycle
        setVisited();
        Map<T, Boolean> dfsVisited = new HashMap<>();
        for (T t : map.keySet()){
            dfsVisited.put(t, false);
        }

        for (T t: map.keySet()){
            if (!visited.get(t)){

                if (checkCycle(t, dfsVisited)){
                    map.get(v).remove(u);
                    if (!vExistsBefore){
                        map.remove(v);
                        inDegree.remove(v);
                        visited.remove(v);
                    }
                    else {
                        inDegree.replace(u, inDegree.get(u), inDegree.get(u) - 1);
                    }
                    if (!uExistsBefore){
                        map.remove(u);
                        inDegree.remove(u);
                        visited.remove(u);
                    }
                    if (bidirectional){
                        map.get(u).remove(v);
                    }

                    edges.remove(edges.size() - 1);

                    return false;
                }

            }
        }
        return true;
    }

    boolean checkCycle(T t, Map<T, Boolean> dfsVisited){
        visited.replace(t, false, true);
        dfsVisited.replace(t, false, true);

        for (T a: map.get(t)){
            if (!visited.get(a)){
                if (checkCycle(a, dfsVisited))
                    return true;
            }
            else if (dfsVisited.get(a)){
                return true;
            }
        }
        dfsVisited.replace(t, true, false);
        return false;
    }

    void BFS(T s, T d){
        if (!map.containsKey(s)){
            System.out.println("No route\n");
            return;
        }
        if (!map.containsKey(d)){
            System.out.println("No route\n");
            return;
        }

        setVisited();
        LinkedList<T> queue = new LinkedList<>();

        visited.replace(s, false, true);
        queue.add(s);

        boolean havePath = false;
        while (!queue.isEmpty()){
            T u = queue.pop();

            for (T v : map.get(u)){
                if (!visited.get(v)){
                    visited.replace(v, false, true);
                    if (v.equals(d)){
                        findPath(s, d, u, map);
                        havePath = true;
                        break;
                    }
                    queue.add(v);
                }
            }
        }
        if (!havePath){
            System.out.println("No route");
        }
        System.out.println();
    }

    void DFS(T v, Stack<T> stack){
        visited.replace(v, false, true);
        for (T a : map.get(v)){
            if (!visited.get(a)){
                DFS(a, stack);
            }
        }
        stack.push(v);
    }

    ArrayList<T> topologicalSort(){ //kahn
        Queue<T> queue = new LinkedList<>();
        ArrayList<T> answer = new ArrayList<>();

        for (T t: map.keySet()){
            if (inDegree.get(t) == 0){
                queue.add(t);
            }
        }
        while (!queue.isEmpty()){
            T u = queue.peek();
            answer.add(u);
            queue.poll();
            for (T v : map.get(u)){
                inDegree.replace(v, inDegree.get(v), inDegree.get(v) - 1);
                if (inDegree.get(v) == 0){
                    queue.add(v);
                }
            }
        }
        return answer;
    }

    Stack<T> topologicalSort2(){ //dfs
        Stack<T> stack = new Stack<>();
        setVisited();
        for (T v: map.keySet()){
            if (!visited.get(v)){
                DFS(v, stack);
            }
        }
        return stack;
    }

    ArrayList<T> topologicalSort3(){
        ArrayList<T> answer = new ArrayList<>(map.keySet());

        int k = 1;
        while (k == 1){
            k = 0;
            for (int j = 0; j < edges.size(); j++) {
                T x = edges.get(j).source;
                T y = edges.get(j).destination;

                if (answer.indexOf(x) > answer.indexOf(y)){
                    T temp = answer.get(answer.indexOf(x));
                    answer.set(answer.indexOf(x), answer.get(answer.indexOf(y)));
                    answer.set(answer.indexOf(y) ,temp);
                    k = 1;
                }
            }
        }

        this.name = answer.get(answer.size() - 1).toString();
        return answer;
    }

    void setVisited() {
        for (T t : map.keySet()){
            visited.put(t, false);
        }
    }

    void findPath(T s, T d, T u, Map<T, List<T>> map){
        ArrayList<T> arr = new ArrayList<>();
        arr.add(d);
        arr.add(u);
        while (!u.equals(s)){
            for (T t : map.get(u)){
                if (visited.get(t) && !arr.contains(t)){
                    arr.add(t);
                    u = t;
                    break;
                }
            }
        }
        for (int i = arr.size() - 1; i > 0; i--){
            System.out.println(arr.get(i) + " " + arr.get(i - 1));
        }
    }

}
class Edge<T>{
    T source;
    T destination;

    public Edge(T source, T destination) {
        this.source = source;
        this.destination = destination;
    }
}