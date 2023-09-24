import javax.imageio.ImageIO;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.*;

public class NodePopulator {

    public static final TransversableNode[][] TRANSVERSABLE_NODES = new TransversableNode[1920][1080];

    private final Set<int[]> acceptableRGBValues = new HashSet<>();

    private final String imagePath;

    public NodePopulator(String imagePath, int[]... acceptableValues) {
        this.imagePath = imagePath;
        Collections.addAll(acceptableRGBValues, acceptableValues);

        fillValues();
    }

    private void fillValues() {
        try {
            Raster raster = ImageIO.read(Path.of(imagePath).toUri().toURL()).getData();

            for (int x = 0; x < 805; x++) {
                for (int y = 0; y < 455; y++) {

                    int[] rgb = raster.getPixel(x, y, new int[4]);
                    if (isRGBMatch(rgb)) {
                        TRANSVERSABLE_NODES[x][y] = new TransversableNode(x, y);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isRGBMatch(int[] rgb) {
        return acceptableRGBValues.stream().anyMatch(acceptableRGB -> Arrays.equals(acceptableRGB, rgb));
    }

}
