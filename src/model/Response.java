package model;

public class Response {
    private String date;
    private String message;

    public Response(String date, String message) {
        this.date = date;
        this.message = message;
    }

    public String getDate() { return date; }
    public String getMessage() { return message; }
}
