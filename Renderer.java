import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.TimerTask;

public class Renderer extends TimerTask {

    private final Canvas canvas;
    private Image map = new ImageIcon("Map.png").getImage();
    private int counter = 0;

    private List<TransversableNode> route;

    public Renderer(Canvas canvas) {
        this.canvas = canvas;
        renderImage(map);
    }

    @Override
    public void run() {
        if (counter == 5) {
            renderImage(map);
            counter = 0;
        }
        render();
        counter++;
    }

    private void render() {
        if (route != null && !route.isEmpty()) {
            for (TransversableNode node : route) {
                int i = Integer.parseInt(node.getID().split(",")[0]);
                int j = Integer.parseInt(node.getID().split(",")[1]);

                int offset = 5;
                BufferedImage image = ImageUtil.toBufferedImage(map);
                for (int x = i; x < i + offset; x++) {
                    for (int y = j; y < j + offset; y++) {
                        image.setRGB(x, y, Color.RED.getRGB());
                    }
                }

                map = image;
                renderImage(image);
            }
            route = null;
        }
    }

    public void setRoute(List<TransversableNode> route) {
        this.route = route;
    }

    private void renderImage(Image image) {
        BufferStrategy bs = canvas.getBufferStrategy();
        if(bs == null) {
            canvas.createBufferStrategy(3);
            bs = canvas.getBufferStrategy();
        }

        Graphics g = bs.getDrawGraphics();

        g.drawImage(image, 0, 0, null);

        bs.show();

        g.dispose();

    }
}
