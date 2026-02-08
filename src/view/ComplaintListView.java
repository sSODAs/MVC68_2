package view;

import model.Complaint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.function.Consumer;

public class ComplaintListView extends JPanel {

    public ComplaintListView(
            List<Complaint> complaints,
            Consumer<Complaint> onSelect,
            Runnable onShowSummary) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("รายการร้องเรียน", JLabel.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 18));

        DefaultListModel<String> model = new DefaultListModel<>();
        for (Complaint c : complaints) {
            model.addElement(
                    "[" + c.getStatus() + "] "
                            + c.getDate() + " | "
                            + c.getType());
        }

        JList<String> list = new JList<>(model);

        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int idx = list.getSelectedIndex();
                    if (idx >= 0) {
                        onSelect.accept(complaints.get(idx));
                    }
                }
            }
        });

        JButton summaryBtn = new JButton("ดูสรุปร้านอาหาร");
        summaryBtn.addActionListener(e -> onShowSummary.run());

        add(title, BorderLayout.NORTH);
        add(new JScrollPane(list), BorderLayout.CENTER);
        add(summaryBtn, BorderLayout.SOUTH);
    }
}
