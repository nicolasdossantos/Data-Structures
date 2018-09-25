package com.company;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Graph<Integer, String> graph = new SparseGraph<Integer, String>();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);
        graph.addVertex(7);
        graph.addVertex(8);

        graph.addEdge("a", 1, 2);
        graph.addEdge("b", 1, 3);
        graph.addEdge("c", 3, 4);
        graph.addEdge("d", 4, 2);
        graph.addEdge("e", 5, 2);
        graph.addEdge("f", 3, 7);
        graph.addEdge("g", 5, 4);
        graph.addEdge("h", 6, 7);
        graph.addEdge("i", 8, 7);


        Layout<Integer, String> layout = new FRLayout<>(graph);

        layout.setSize(new Dimension(300, 300));

        BasicVisualizationServer<Integer, String> vv = new BasicVisualizationServer<Integer, String>(layout);

        vv.setPreferredSize(new Dimension(400, 400));

        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());

        JFrame frame = new JFrame("Graph");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(vv);

        frame.pack();

        frame.setVisible(true);

        System.out.println( breadthFirstSearch(graph));
        System.out.println(depthFirstSearch(graph));


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


    public static <V, E> String depthFirstSearch(Graph<V, E> g) {

        StringBuilder b = new StringBuilder();

        Stack<V> stack = new Stack<V>();
        Set<V> visited = new HashSet();
        Set<V> found = new HashSet();

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
            visited.add(vertex);
        }

        return b.toString();
    }
}
