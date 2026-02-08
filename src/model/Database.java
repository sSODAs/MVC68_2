package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Database {

    public static List<Canteen> canteens;
    public static List<Stall> stalls;
    public static List<Complaint> complaints;

    static {
        canteens = new ArrayList<>();
        stalls = new ArrayList<>();
        complaints = new ArrayList<>();

        loadCanteens();
        loadStalls();
        loadComplaints();
    }

    private static void loadCanteens() {
        for (Map<String, String> o : parseSimpleObjects("data/canteens.json")) {
            canteens.add(new Canteen(
                    Integer.parseInt(o.get("id")),
                    o.get("name"),
                    o.get("location")));
        }
    }

    private static void loadStalls() {
        for (Map<String, String> o : parseSimpleObjects("data/stalls.json")) {
            stalls.add(new Stall(
                    Integer.parseInt(o.get("id")),
                    o.get("name"),
                    Integer.parseInt(o.get("canteenId"))));
        }
    }

    private static void loadComplaints() {
        for (Map<String, String> o : parseSimpleObjects("data/complaints.json")) {
            complaints.add(new Complaint(
                    Integer.parseInt(o.get("id")),
                    Integer.parseInt(o.get("stallId")),
                    o.get("date"),
                    o.get("type"),
                    o.get("detail"),
                    o.get("status")));
        }
    }

    private static List<Map<String, String>> parseSimpleObjects(String path) {
        List<Map<String, String>> list = new ArrayList<>();
        try {
            String json = Files.readString(Paths.get(path)).trim();
            json = json.substring(1, json.length() - 1);
            String[] objects = json.split("\\},\\s*\\{");

            for (String obj : objects) {
                obj = obj.replace("{", "").replace("}", "");
                Map<String, String> map = new HashMap<>();

                for (String f : obj.split(",")) {
                    String[] pair = f.split(":", 2);
                    map.put(
                            pair[0].replace("\"", "").trim(),
                            pair[1].replace("\"", "").trim());
                }
                list.add(map);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Stall findStallById(int id) {
        for (Stall s : stalls) {
            if (s.getId() == id)
                return s;
        }
        return null;
    }

    public static Canteen findCanteenById(int id) {
        for (Canteen c : canteens) {
            if (c.getId() == id)
                return c;
        }
        return null;
    }

}
