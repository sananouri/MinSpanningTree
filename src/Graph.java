import java.util.ArrayList;
import java.util.Collections;

public class Graph {
    private int size;
    private ArrayList<Edge> edges;
    private ArrayList<Edge> minSpanningTree = null;

    public Graph(int size, ArrayList<Edge> edges) {
        this.size = size;
        this.edges = edges;
    }

    public int getSize() {
        return size;
    }

    public ArrayList<Edge> getMinSpanningTree() {
        if (minSpanningTree == null) {
            findMinSpanningTree();
        }
        return minSpanningTree;
    }

    private void findMinSpanningTree() {
        ArrayList<Edge> sortedEdges = edges;
        Collections.sort(sortedEdges);
        ArrayList<ArrayList<Integer>> sets = new ArrayList<>();
        ArrayList<Integer> set;
        for (int i = 1; i <= size; i++) {
            set = new ArrayList<>();
            set.add(i);
            sets.add(set);
        }
        minSpanningTree = new ArrayList<>();
        int firstSetIndex, secondSetIndex;
        while (minSpanningTree.size() < size - 1) {
            firstSetIndex = getSetIndex(sets, sortedEdges.get(0).getStartVertex());
            secondSetIndex = getSetIndex(sets, sortedEdges.get(0).getEndVertex());
            if (firstSetIndex != secondSetIndex) {
                minSpanningTree.add(sortedEdges.get(0));
                sets.get(secondSetIndex).addAll(sets.get(firstSetIndex));
                sets.remove(firstSetIndex);
            }
            sortedEdges.remove(0);
        }
    }

    private int getSetIndex(ArrayList<ArrayList<Integer>> sets, int vertex) {
        for (int i = 0; i < sets.size(); i++) {
            if (sets.get(i).contains(vertex)) {
                return i;
            }
        }
        return 0;
    }
}
