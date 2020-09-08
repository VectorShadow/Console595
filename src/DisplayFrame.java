import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class DisplayFrame extends JFrame {

    private final DisplayPanel PANEL;

    DisplayFrame() {
        addComponentListener(
                new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        Dimension resizedDimension = getContentPane().getSize();
                        PANEL.setPreferredSize(resizedDimension);
                        PANEL.setSize(resizedDimension);
                        setContentPane(PANEL);
                    }
                }
        );
        PANEL = new DisplayPanel();
        setContentPane(PANEL);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
    }

    DisplayPanel getPanel() {
        return PANEL;
    }
}
