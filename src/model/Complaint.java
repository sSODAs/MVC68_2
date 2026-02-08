package model;

import java.util.ArrayList;
import java.util.List;

public class Complaint {

    private int id;
    private int stallId;
    private String date;
    private String type;
    private String detail;
    private String status;

    private List<Response> responses = new ArrayList<>();

    public Complaint(
            int id,
            int stallId,
            String date,
            String type,
            String detail,
            String status
    ) {
        this.id = id;
        this.stallId = stallId;
        this.date = date;
        this.type = type;
        this.detail = detail;
        setStatus(status); // ⚠️ ใช้ setter เพื่อ sync logic
    }

    public int getId() {
        return id;
    }

    public int getStallId() {
        return stallId;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getDetail() {
        return detail;
    }

    public String getStatus() {
        return status;
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void addResponse(Response response) {
        responses.add(response);
    }

    public void setStatus(String status) {
        this.status = status;

        if (status.equals("ดำเนินการแล้ว") && responses.isEmpty()) {
            responses.add(
                new Response(
                    "2026-02-10",
                    "รับเรื่องและดำเนินการเรียบร้อยแล้ว"
                )
            );
        }
    }
}
