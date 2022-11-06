import javax.imageio.ImageIO;
import java.awt.image.Raster;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class NodePopulator {

    public static final TransversableNode[][] TRANSVERSABLE_NODES = new TransversableNode[1920][1080];

    private final Set<int[]> acceptableRGBValues = new HashSet<>();

    private final String imagePath;

    public NodePopulator(String imagePath, int[]... acceptableValues) {
        this.imagePath = imagePath;
        Collections.addAll(acceptableRGBValues, acceptableValues);
    }

    private void fillValues() {
        try {
            Raster raster = ImageIO.read(new URL("file.png")).getData();

            for (int x = 0; x < 1920; x++) {
                for (int y = 0; y < 1080; y++) {

                    int[] rgb = new int[4];
                    raster.getPixel(x, y, rgb);
                    TRANSVERSABLE_NODES[x][y] = isRGBMatch(rgb) ? new TransversableNode(x, y) : null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isRGBMatch(int[] rgb) {
        return acceptableRGBValues.stream().anyMatch(acceptableRGB -> rgb == acceptableRGB);
    }

}
