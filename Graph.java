import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph<T extends GraphNode> {

    private final Set<T> roads; //Set of all roads
    private final Map<String, Set<String>> roadConnections; //Connections from one road (or node), to another

    public Graph(Set<T> roads, Map<String, Set<String>> roadConnections) {
        this.roads = roads;
        this.roadConnections = roadConnections;
    }

    public T getNode(String id) {
        return roads.stream()
                .filter(road -> road.getID().equals(id))
                .findFirst().get();
    }

    public Set<T> getConnections(T road) {
        return roadConnections.get(road.getID()).stream()
                .map(this::getNode)
                .collect(Collectors.toSet());
    }
}
