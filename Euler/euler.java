package com.nicolasdossantos;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Graph<Character, Integer> g = new DirectedSparseGraph<>();

        File file = new File("e.txt");
        int a = 0;
        int b = 100;

        try {

            Scanner sc = new Scanner(file);


            while (sc.hasNextLine()) {
                a++;
                b++;

                String i = sc.nextLine();
                char character[] = i.toCharArray();
                int j = 0;


                g.addEdge(a, character[j], character[j + 1]);
                g.addEdge(b, character[j + 1], character[j + 2]);


            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        Layout<Character, Integer> layout = new FRLayout<>(g);

        layout.setSize(new Dimension(300, 300));

        BasicVisualizationServer<Character, Integer> vv = new BasicVisualizationServer<>(layout);

        vv.setPreferredSize(new Dimension(400, 400));

        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());

        JFrame frame = new JFrame("Graph");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(vv);

        frame.pack();

        frame.setVisible(true);

        topologicalSort(g);


    }

    //Topological sort on graph
    public static void topologicalSort(Graph<Character, Integer> g) {
        LinkedList<Character> ll = new LinkedList<>();
        ll.add('0');
        ll.add('1');
        ll.add('2');
        ll.add('3');
        ll.add('6');
        ll.add('7');
        ll.add('8');
        ll.add('9');


        StringBuilder s = new StringBuilder();

        while (g.getVertexCount() != 0) {
            for (int i = 0; i < ll.size(); i++) {
                if (g.inDegree(ll.get(i)) == 0) {
                    s.append(ll.get(i));

                    g.removeVertex(ll.get(i));
                    ll.remove(i);
                }
            }

        }

        System.out.println("The Password is: " + s);
    }

}