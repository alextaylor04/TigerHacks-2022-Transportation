public class TransversableNode implements GraphNode {

    private final int x, y;

    public TransversableNode(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public TransversableNode[] surroundingNodes() {
        TransversableNode[] nodes = new TransversableNode[8];

        int xLowClamp = Math.max(0, x-1);
        int yLowClamp = Math.max(0, y-1);
        int xHighClamp = Math.min(NodePopulator.TRANSVERSABLE_NODES.length - 1, x+1);
        int yHighClamp = Math.min(NodePopulator.TRANSVERSABLE_NODES[0].length - 1, y+1);

        nodes[0] = NodePopulator.TRANSVERSABLE_NODES[xLowClamp][y];
        nodes[1] = NodePopulator.TRANSVERSABLE_NODES[x][yLowClamp];
        nodes[2] = NodePopulator.TRANSVERSABLE_NODES[xLowClamp][yLowClamp];
        nodes[3] = NodePopulator.TRANSVERSABLE_NODES[xHighClamp][y];
        nodes[4] = NodePopulator.TRANSVERSABLE_NODES[x][yHighClamp];
        nodes[5] = NodePopulator.TRANSVERSABLE_NODES[xHighClamp][yHighClamp];
        nodes[6] = NodePopulator.TRANSVERSABLE_NODES[xLowClamp][yHighClamp];
        nodes[7] = NodePopulator.TRANSVERSABLE_NODES[xHighClamp][yLowClamp];

        return nodes;
    }

    @Override
    public String toString() {
        return getID();
    }

    @Override
    public String getID() {
        return x + "," + y;
    }
}
