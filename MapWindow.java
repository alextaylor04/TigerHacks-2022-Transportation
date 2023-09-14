import javax.swing.*;
import java.awt.*;

public class MapWindow extends Canvas {

    public MapWindow() {
        JFrame frame = new JFrame("Map");

        frame.setMinimumSize(new Dimension(1920, 1080));
        frame.setMaximumSize(new Dimension(1920, 1080));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.add(this);

        frame.setVisible(true);
    }


}
