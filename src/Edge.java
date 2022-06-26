public class Edge implements Comparable<Edge> {
    private int startVertex;
    private int endVertex;
    private int weight;

    public Edge(int from, int to, int weight) {
        startVertex = from;
        endVertex = to;
        this.weight = weight;
    }

    public int getStartVertex() {
        return startVertex;
    }

    public int getEndVertex() {
        return endVertex;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge o) {
        return weight - o.weight;
    }
}
