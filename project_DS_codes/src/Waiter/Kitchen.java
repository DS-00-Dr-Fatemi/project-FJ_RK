package Waiter;

public class Kitchen extends cell{
    char name;

    public Kitchen(char name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.valueOf(name);
    }
}
