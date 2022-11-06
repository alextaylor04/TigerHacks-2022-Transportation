public interface ConditionalWeight<T extends GraphNode> {

    double cost(T pointA, T pointB);

}
