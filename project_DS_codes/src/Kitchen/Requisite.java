package Kitchen;

public class Requisite {
    String name;
    int time;

    public Requisite(String name, int time) {
        this.name = name;
        this.time = time;
    }

    @Override
    public String toString() {
        return name;
    }
}