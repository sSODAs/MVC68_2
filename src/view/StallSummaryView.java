package view;

import model.*;
import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class StallSummaryView extends JPanel {

    public StallSummaryView(
            Map<Canteen, Map<Stall, Integer>> summary,
            Runnable onBack) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("สรุปร้านอาหารแยกตามโรงอาหาร", JLabel.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JTextArea area = new JTextArea();
        area.setEditable(false);

        for (Canteen c : summary.keySet()) {
            area.append("โรงอาหาร: " + c.getName() + "\n");
            for (Map.Entry<Stall, Integer> e : summary.get(c).entrySet()) {
                area.append("  - " + e.getKey().getName()
                        + " : " + e.getValue() + "\n");
            }
            area.append("\n");
        }

        add(new JScrollPane(area), BorderLayout.CENTER);

        JButton back = new JButton("กลับ");
        back.addActionListener(e -> onBack.run());
        add(back, BorderLayout.SOUTH);
    }
}
