import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.GraphDecorator;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import javafx.util.Pair;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //Graph<Integer, String> graph = new SparseGraph<Integer, String>();
        Graph<Integer, Edge> g = new DirectedSparseGraph<>();

        Edge e01 = new Edge(10);
        Edge e04 = new Edge(100);
        Edge e03 = new Edge(30);
        Edge e12 = new Edge(50);
        Edge e24 = new Edge(10);
        Edge e32 = new Edge(20);
        Edge e34 = new Edge(60);

        g.addEdge(e01, 0, 1);
        g.addEdge(e04, 0, 4);
        g.addEdge(e03, 0, 3);
        g.addEdge(e12, 1, 2);
        g.addEdge(e24, 2, 4);
        g.addEdge(e32, 3, 2);
        g.addEdge(e34, 3, 4);


        Layout<Integer, Edge> layout = new FRLayout<>(g);

        layout.setSize(new Dimension(300, 300));

        BasicVisualizationServer<Integer, Edge> vv = new BasicVisualizationServer<Integer, Edge>(layout);

        vv.setPreferredSize(new Dimension(400, 400));

        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());

        JFrame frame = new JFrame("Graph");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(vv);

        frame.pack();

        frame.setVisible(true);


        System.out.println(dijkstra(g, 0));


    }

    public static <V, E> String breadthFirstSearch(Graph<V, E> g) {

        StringBuilder b = new StringBuilder();

        Queue<V> queue = new LinkedList<V>();
        Set<V> visited = new HashSet();
        Set<V> found = new HashSet();

        V start = (V) g.getVertices().toArray()[0];
        queue.add(start);
        found.add(start);

        while (!queue.isEmpty()) {

            V vertex = queue.remove();
            for (V neighbor : g.getNeighbors(vertex)) {
                if (!found.contains(neighbor) && !visited.contains(neighbor)) {

                    found.add(neighbor);
                    queue.add(neighbor);
                }
            }

            b.append(vertex.toString() + " ");
            visited.add(vertex);
        }

        return b.toString();
    }


    public static <V, E> List<Integer> depthFirstSearch(Graph<V, E> g) {

        StringBuilder b = new StringBuilder();

        Stack<V> stack = new Stack<V>();
        Set<V> visited = new HashSet();
        Set<V> found = new HashSet();

        List<Integer> vert = new LinkedList<>();

        V start = (V) g.getVertices().toArray()[0];
        stack.push(start);
        found.add(start);

        while (!stack.isEmpty()) {

            V vertex = stack.pop();
            for (V neighbor : g.getNeighbors(vertex)) {
                if (!found.contains(neighbor) && !visited.contains(neighbor)) {

                    found.add(neighbor);
                    stack.push(neighbor);
                }
            }

            b.append(vertex.toString() + " ");
            vert.add((int) vertex);

            visited.add(vertex);

        }

        return vert;
    }


    public static <V, E> Map<V, Double> dijkstra(Graph<V, Edge> graph, V start) {

        V current = null;
        Graph<V, Edge> g = new GraphDecorator<>(graph);
        Set<V> unvisited = new HashSet<>(g.getVertices());
        Set<V> shortestFound = new HashSet<>();
        Map<V, Pair<Double, V>> map = new HashMap<>(); //Map of vertex to weight and predecessor


        for (V v : unvisited) {
            map.put(v, new Pair<>(Double.MAX_VALUE, start));
        }
        unvisited.remove(start);
        shortestFound.add(start);
        map.remove(start);

        for (V n : g.getNeighbors(start)) {
            map.put(n, new Pair<>(g.findEdge(start, n).getWeight(), start));
        }

        while (!unvisited.isEmpty()) {
            double min = Double.MAX_VALUE;
            for (V n : unvisited) {
                if (map.get(n).getKey() < min) {
                    current = n;
                    min = map.get(n).getKey();
                }
            }

            for (V n : g.getNeighbors(current)) {
                if (unvisited.contains(n)) {
                    double weightCache = g.findEdge(current, n).getWeight() + map.get(current).getKey();
                    if (weightCache < map.get(n).getKey()) {
                        map.put(n, new Pair<>(weightCache, current));
                    }
                }
            }

            unvisited.remove(current);
            shortestFound.add(current);
        }

        Map<V, Double> cache = new HashMap<>();
        for (Map.Entry<V, Pair<Double, V>> k : map.entrySet()) {
            cache.put(k.getKey(), k.getValue().getKey());
        }

        return cache;
    }
}

