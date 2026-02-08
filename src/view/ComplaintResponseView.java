package view;

import model.Complaint;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class ComplaintResponseView extends JPanel {

        public ComplaintResponseView(
                        Complaint complaint,
                        Consumer<String> onSubmit,
                        Runnable onCancel) {
                setLayout(new BorderLayout());

                JLabel title = new JLabel(
                                "ตอบกลับการร้องเรียน #" + complaint.getId(),
                                JLabel.CENTER);

                JTextArea input = new JTextArea();

                JButton submit = new JButton("บันทึก");
                JButton cancel = new JButton("ยกเลิก");

                submit.addActionListener(e -> onSubmit.accept(input.getText()));

                cancel.addActionListener(e -> onCancel.run());

                JPanel btnPanel = new JPanel();
                btnPanel.add(submit);
                btnPanel.add(cancel);

                add(title, BorderLayout.NORTH);
                add(new JScrollPane(input), BorderLayout.CENTER);
                add(btnPanel, BorderLayout.SOUTH);
        }
}
