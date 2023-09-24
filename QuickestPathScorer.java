public class QuickestPathScorer<T extends TransversableNode> implements ConditionalWeight<T> {


    @Override
    public double cost(TransversableNode pointA, TransversableNode pointB) {
        if (pointA == null || pointB == null) return Integer.MAX_VALUE;

        double cost = Math.sqrt(Math.pow(pointB.getX() - pointA.getX(), 2) + Math.pow(pointB.getY() - pointA.getY(), 2));

//        System.out.println("Point A: " + pointA);
//        System.out.println("Point B: " + pointB);
//        System.out.println("Cost: " + cost);

        return cost;
    }
}
