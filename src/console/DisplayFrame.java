package console;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * DisplayFrame provides the window which contains the displayed image.
 */
public class DisplayFrame extends JFrame {

    private final DisplayPanel PANEL;

    DisplayFrame() {
        /*
         * This adapter ensures that the panel image is properly scaled whenever the user manually adjusts the
         * window size.
         */
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
