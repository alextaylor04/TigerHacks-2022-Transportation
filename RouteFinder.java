import java.util.*;

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
        //Starts queue and maps out all nodes (roads)
        Queue<RouteNode> openSet = new PriorityQueue<>();
        Map<T, RouteNode<T>> allNodes = new HashMap<>();

        //"Open set" of nodes (roads), which will consider the next step and all roads visited so far (also what we know about it i.e. the weight/cost of performing that move)
        RouteNode<T> start = new RouteNode<>(from, null, 0d, targetScorer.cost(from, to));
        openSet.add(start);
        allNodes.put(from, start);

        //Iteration Process
        while(!openSet.isEmpty()) {
            RouteNode<T> next = openSet.poll();
            if (next.getCurrent().equals(to)) {
                List<T> route = new ArrayList<>();

                RouteNode<T> current = next;
                while(current != null) {
                    route.add(0, current.getCurrent());
                    current = allNodes.get(current.getPrevious());
                }
                return route;

            }
            for (T connection : graph.getConnections(next.getCurrent())) {
                RouteNode<T> node = allNodes.getOrDefault(connection, new RouteNode<>(connection));
                allNodes.put(connection, node);

                double computeNewScore = node.getRouteScore() + nextNodeScorer.cost(next.getCurrent(), connection);

                if (computeNewScore < node.getRouteScore()) {
                    node.setPrevious(next.getCurrent());
                    node.setRouteScore(computeNewScore);
                    node.setEstimatedScore(computeNewScore + targetScorer.cost(connection, to));
                    openSet.add(node);
                }
            }


        }
        return null;

    }

}
