package Kitchen;

public class Food {
    Graph<Requisite> graph = new Graph<Requisite>();
    String name;
    int totalTime;
    int numOfRequisites;

    public void addARelationship(Requisite r1, Requisite r2, int lastTime){

        if (graph.setEdgeWithoutCycle(r1, r2, false)){
            System.out.println("the relationship added successfully!");
        }
        else {
            System.out.println("error: this relationship makes a cycle in then graph");
            r1.time = lastTime;
        }
    }

    public int getNumOfRequisites(){
        numOfRequisites = graph.map.size() - 1;
        return numOfRequisites;
    }

    public int getTotalTime(){
        totalTime = 0;
        for (Requisite r: graph.map.keySet()){
            totalTime += r.time;
        }
        return totalTime;
    }

    @Override
    public String toString() {
        this.name = graph.name;
        return this.name;
    }
}
