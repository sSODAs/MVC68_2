package model;

public class Stall {

    private int id;
    private String name;
    private int canteenId;

    public Stall(int id, String name, int canteenId) {
        this.id = id;
        this.name = name;
        this.canteenId = canteenId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCanteenId() {
        return canteenId;
    }
}
