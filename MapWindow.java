import javax.swing.*;
import java.awt.*;

public class MapWindow extends Canvas {

    public MapWindow() {
        JFrame frame = new JFrame("Map");

        frame.setMinimumSize(new Dimension(1800, 1017));
        frame.setMaximumSize(new Dimension(1800, 1017));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.add(this);

        frame.setVisible(true);
    }


}
