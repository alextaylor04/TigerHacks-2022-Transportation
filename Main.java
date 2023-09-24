import java.util.*;

public class Main {


    
    public static void main(String[] args) {

        MapWindow window = new MapWindow();

        Renderer renderer = new Renderer(window);

        Timer timer = new Timer("Timer");
        timer.schedule(renderer, 0, 1000L);

        new NodePopulator("Map.png", 15, new int[]{153,153,153});

        route(renderer);

    }

    private static void route(Renderer renderer) {

        QuickestPathScorer<TransversableNode> quickestPathScorer = new QuickestPathScorer<>();

        TransversableNode from = null, to = null;

        int width = NodePopulator.TRANSVERSABLE_NODES.length;
        int height = NodePopulator.TRANSVERSABLE_NODES[0].length;

        Random r = new Random();

        int maxDistance = 500;
        while (from == null || to == null || quickestPathScorer.cost(from, to) > maxDistance) {
            int x = r.nextInt(width);
            int y = r.nextInt(height);
            TransversableNode node = NodePopulator.TRANSVERSABLE_NODES[x][y];
            if (from == null) {
                from = node;
                continue;
            }
            to = node;
        }

        System.out.println("From: (" + from.getX() + "," + from.getY() + ")");
        System.out.println("To: (" + to.getX() + "," + to.getY() + ")");

        RouteFinder<TransversableNode> finder = new RouteFinder<>(transversableNodeGraph(width, height), quickestPathScorer, quickestPathScorer);


        List<TransversableNode> nodes = finder.findRoute(from, to);
        renderer.setRoute(nodes);

    }

    private static Graph<TransversableNode> transversableNodeGraph(int width, int height) {
        Map<String, Set<String>> roadConnections = new HashMap<>();

        Set<TransversableNode> allNodes = new HashSet<>();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                Set<String> nodeConnections = new HashSet<>();

                TransversableNode node = NodePopulator.TRANSVERSABLE_NODES[x][y];

                if (node == null) continue;

                for (TransversableNode surroundingNode : node.surroundingNodes()) {
                    if (surroundingNode == null) continue;
                    nodeConnections.add(surroundingNode.getID());
                }
                roadConnections.put(node.getID(), nodeConnections);

                allNodes.add(node);
            }
        }

        System.out.println(allNodes.size());
        return new Graph<>(allNodes, roadConnections);
    }



}
