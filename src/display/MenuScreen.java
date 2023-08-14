package display;

import javax.swing.*;
import java.awt.*;

public class MenuScreen extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawString("This is Menu Screen" , 100 , 100);
    }
}
