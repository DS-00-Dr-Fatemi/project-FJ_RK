package Waiter;

public class Table extends cell{
    char name;
    boolean isFull;//TODO por ya khali
    int timeOfFull = 0;

    public Table(char name, boolean isFull) {
        this.name = name;
        this.isFull = isFull;
    }
    public void setTimeOfFull(int a,String name){
        this.timeOfFull=a;
        for (int i = 0; i < a; i++) {
            this.timeOfFull--;
        }
        this.isFull=false;
        System.out.println("Customer :"+name+" ate his food and left table :"+this.name);
    }

    public void emptyTable() {
        this.isFull= false;
    }
}
