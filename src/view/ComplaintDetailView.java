package view;

import model.*;

import javax.swing.*;
import java.awt.*;

public class ComplaintDetailView extends JPanel {

    public ComplaintDetailView(
            Complaint complaint,
            Stall stall,
            Canteen canteen,
            Runnable onReply,
            Runnable onBack) {
        setLayout(new BorderLayout());

        JTextArea area = new JTextArea();
        area.setEditable(false);

        area.append("วันที่: " + complaint.getDate() + "\n");
        area.append("โรงอาหาร: " + canteen.getName() + "\n");
        area.append("ร้าน: " + stall.getName() + "\n");
        area.append("ประเภท: " + complaint.getType() + "\n");
        area.append("สถานะ: " + complaint.getStatus() + "\n\n");

        area.append("รายละเอียด:\n");
        area.append(complaint.getDetail() + "\n\n");

        area.append("การตอบกลับ:\n");

        if (complaint.getResponses().isEmpty()) {
            area.append("ยังไม่มีการตอบกลับ\n");
        } else {
            for (Response r : complaint.getResponses()) {
                area.append("- [" + r.getDate() + "] " + r.getMessage() + "\n");
            }
        }

        add(new JScrollPane(area), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton replyBtn = new JButton("ตอบกลับ");
        JButton backBtn = new JButton("กลับ");

        replyBtn.addActionListener(e -> onReply.run());
        backBtn.addActionListener(e -> onBack.run());

        btnPanel.add(replyBtn);
        btnPanel.add(backBtn);

        add(btnPanel, BorderLayout.SOUTH);
    }
}
