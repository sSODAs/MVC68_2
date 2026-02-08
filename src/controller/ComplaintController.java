package controller;

import model.*;
import view.*;

import java.util.List;
import java.util.Map;

public class ComplaintController {

    private MainFrame frame;

    public ComplaintController() {
        frame = new MainFrame();
    }

    public void showAllComplaints() {
        List<Complaint> complaints = Database.complaints;

        ComplaintListView view = new ComplaintListView(
                complaints,
                this::showComplaintDetail,
                this::showStallSummary);

        frame.setView(view);
    }

    public void showComplaintDetail(Complaint complaint) {

        Stall stall = Database.findStallById(complaint.getStallId());
        Canteen canteen = Database.findCanteenById(stall.getCanteenId());

        ComplaintDetailView view = new ComplaintDetailView(
                complaint,
                stall,
                canteen,
                () -> showResponseForm(complaint),
                this::showAllComplaints);

        frame.setView(view);
    }

    public void showResponseForm(Complaint complaint) {
        ComplaintResponseView view = new ComplaintResponseView(
                complaint,
                message -> addResponseAndBack(complaint, message),
                () -> showComplaintDetail(complaint));

        frame.setView(view);
    }

    private void addResponseAndBack(Complaint complaint, String message) {
        complaint.addResponse(
                new Response("2026-02-10", message));

        complaint.setStatus("ดำเนินการแล้ว");

        showComplaintDetail(complaint);
    }

    public void showStallSummary() {
        Map<Canteen, Map<Stall, Integer>> summary = ComplaintService.summarizeByCanteen(
                Database.complaints,
                Database.canteens,
                Database.stalls);

        frame.setView(new StallSummaryView(summary, this::showAllComplaints));
    }

}
