package Waiter;

public class Table extends cell{
    char name;
    boolean isFull;//TODO por ya khali

    public Table(char name, boolean isFull) {
        this.name = name;
        this.isFull = isFull;
    }

    @Override
    public String toString() {
        return String.valueOf(name);
    }
}
