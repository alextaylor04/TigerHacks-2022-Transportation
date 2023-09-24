import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class Renderer extends TimerTask {

    private final Canvas canvas;
    private final Image map = new ImageIcon("Map.png").getImage();

    private List<TransversableNode> route;

    public Renderer(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void run() {
        render();
    }

    private void render() {
        BufferStrategy bs = canvas.getBufferStrategy();
        if(bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        for (int x = 0; x < 200; x++) {
            for (int y = 0; y < 200; y++) {
                BufferedImage image = ImageUtil.toBufferedImage(map);
                image.setRGB(x, y, Color.BLACK.getRGB());
            }
        }

        if (route != null && !route.isEmpty()) {
            for (TransversableNode node : route) {
                int x = Integer.parseInt(node.getID().split(",")[0]);
                int y = Integer.parseInt(node.getID().split(",")[1]);



            }
        } else {

        }
        g.drawImage(map, 0, 0, null);
        g.dispose();
        bs.show();
    }

    public void setRoute(List<TransversableNode> route) {
        this.route = route;
    }
}
