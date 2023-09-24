import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class NodePopulator {

    public static final TransversableNode[][] TRANSVERSABLE_NODES = new TransversableNode[1800][1017];

    private final Set<Integer> acceptableRGBValues = new HashSet<>();

    private final String imagePath;

    public NodePopulator(String imagePath, int error, int[] acceptableValues) {
        this.imagePath = imagePath;
        int r0 = acceptableValues[0];
        int g0 = acceptableValues[1];
        int b0 = acceptableValues[2];

        for (int r = r0; r < r0 + error; r++) {
            for (int g = g0; g < g0 + error; g++) {
                for (int b = b0; b < b0 + error; b++) {
                    acceptableRGBValues.add(new Color(r,g,b).getRGB());
                }
            }
        }

        fillValues();
    }


    private void fillValues() {
        try {
            Image image = new ImageIcon(Path.of(imagePath).toUri().toURL()).getImage();
            BufferedImage bImage = ImageUtil.toBufferedImage(image);

            for (int x = 0; x < TRANSVERSABLE_NODES.length; x++) {
                for (int y = 0; y < TRANSVERSABLE_NODES[0].length; y++) {

                    int rgb = bImage.getRGB(x, y);
                    if (acceptableRGBValues.contains(rgb)) {
                        TRANSVERSABLE_NODES[x][y] = new TransversableNode(x, y);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
