import java.util.*;
import java.util.stream.Collectors;

public class RouteFinder<T extends GraphNode> {
    private final Graph<T> graph;
    private final ConditionalWeight<T> nextNodeScorer;
    private final ConditionalWeight<T> targetScorer;

    public RouteFinder(Graph<T> graph, ConditionalWeight<T> nextNodeScorer, ConditionalWeight<T> targetScorer) {
        this.graph = graph;
        this.nextNodeScorer = nextNodeScorer;
        this.targetScorer = targetScorer;
    }

    public List<T> findRoute(T from, T to) {
        Queue<RouteNode> openSet = new PriorityQueue<>();
        Map<T, RouteNode<T>> allNodes = new HashMap<>();

        RouteNode<T> start = new RouteNode<>(from, null, 0d, targetScorer.cost(from, to));
        openSet.add(start);
        allNodes.put(from, start);

        while (!openSet.isEmpty()) {


            RouteNode<T> next = openSet.poll();
            if (next.getCurrent().getID().equals(to.getID())) {
                List<T> route = new ArrayList<>();
                RouteNode<T> current = next;
                do {
                    route.add(0, current.getCurrent());
                    current = allNodes.get(current.getPrevious());
                } while (current != null);
                System.out.println("Found route!");
                return route;
            }

            graph.getConnections(next.getCurrent()).forEach(connection -> {
                RouteNode<T> nextNode = allNodes.getOrDefault(connection, new RouteNode<>(connection));
                allNodes.put(connection, nextNode);

                double newScore = next.getRouteScore() + nextNodeScorer.cost(next.getCurrent(), connection);
                if (newScore < nextNode.getRouteScore()) {
                    nextNode.setPrevious(next.getCurrent());
                    nextNode.setRouteScore(newScore);
                    nextNode.setEstimatedScore(newScore + targetScorer.cost(connection, to));
                    openSet.add(nextNode);
                    //No longer needed: System.out.println("Found a better route to node: " + nextNode);
                }
            });
        }

        throw new IllegalStateException("No route found");
    }


}
