import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int n = scanner.nextInt(), weight;
        System.out.println("Enter graph matrix:");
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                weight = scanner.nextInt();
                if (weight != 0) {
                    edges.add(new Edge(i, j, weight));
                }
            }
        }
        Graph graph = new Graph(n, edges);
        FxClass.drawMinSpanningTree(args, graph.getSize(), graph.getMinSpanningTree());
    }
}
