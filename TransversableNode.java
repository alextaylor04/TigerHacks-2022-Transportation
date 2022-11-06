public class TransversableNode implements GraphNode {

    private final int x, y;

    public TransversableNode(int x, int y) {
        this.x = x;
        this.y = y;

    }

    @Override
    public String getID() {
        return "(" + x + "," + y + ")";
    }
}
