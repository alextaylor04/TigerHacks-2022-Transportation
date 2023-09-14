import java.util.*;

public class Main {


    
    public static void main(String[] args) {

        MapWindow window = new MapWindow();

//        Thread thread = new Thread();
//        thread.start();

        Renderer renderer = new Renderer(window);

        Timer timer = new Timer("Timer");
        timer.schedule(renderer, 0, 1000L);

        route(renderer);

    }

    private static void route(Renderer renderer) {
        new NodePopulator("Map.png", new int[]{153,153,153,255});

        Map<String, Set<String>> roadConnections = new HashMap<>();

        Set<TransversableNode> allNodes = new HashSet<>();

        TransversableNode from = NodePopulator.TRANSVERSABLE_NODES[81][61], to = NodePopulator.TRANSVERSABLE_NODES[110][65];

        Random r = new Random();

        for (int x = 0; x < 805; x++) {
            for (int y = 0; y < 455; y++) {

                Set<String> nodeConnections = new HashSet<>();

                TransversableNode node = NodePopulator.TRANSVERSABLE_NODES[x][y];

                if (node == null) continue;

                for (TransversableNode surroundingNode : node.surroundingNodes()) {
                    if (surroundingNode == null) continue;
                    nodeConnections.add(surroundingNode.getX() + "," + surroundingNode.getY());
                }
                roadConnections.put(x + "," + y, nodeConnections);

                allNodes.add(node);
            }
        }

        QuickestPathScorer<TransversableNode> quickestPathScorer = new QuickestPathScorer<>();


        RouteFinder<TransversableNode> finder = new RouteFinder<>(new Graph<>(allNodes, roadConnections), quickestPathScorer, quickestPathScorer);


        List<TransversableNode> nodes = finder.findRoute(from, to);
        renderer.setRoute(nodes);

    }



}
