package model;

import java.util.*;

public class ComplaintService {

    public static Map<Canteen, Map<Stall, Integer>> summarizeByCanteen(
            List<Complaint> complaints,
            List<Canteen> canteens,
            List<Stall> stalls
    ) {

        Map<Integer, Stall> stallMap = new HashMap<>();
        for (Stall s : stalls) {
            stallMap.put(s.getId(), s);
        }

        Map<Integer, Canteen> canteenMap = new HashMap<>();
        for (Canteen c : canteens) {
            canteenMap.put(c.getId(), c);
        }

        // ผลลัพธ์
        Map<Canteen, Map<Stall, Integer>> result = new LinkedHashMap<>();

        // init
        for (Canteen c : canteens) {
            Map<Stall, Integer> stallCount = new LinkedHashMap<>();
            for (Stall s : stalls) {
                if (s.getCanteenId() == c.getId()) {
                    stallCount.put(s, 0);
                }
            }
            result.put(c, stallCount);
        }

        // นับ complaint
        for (Complaint com : complaints) {
            Stall s = stallMap.get(com.getStallId());
            if (s != null) {
                Canteen c = canteenMap.get(s.getCanteenId());
                if (c != null) {
                    Map<Stall, Integer> map = result.get(c);
                    map.put(s, map.get(s) + 1);
                }
            }
        }

        return result;
    }
}
