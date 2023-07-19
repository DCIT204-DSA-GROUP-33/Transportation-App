package app.algorithms;

import java.util.ArrayList;
import java.util.HashMap;

import app.graph.Graph;
import app.graph.Edge;
import app.graph.Node;

public class DijkstraTime {
    private static ArrayList<Node> UNVISITED = new ArrayList<>();
    private static HashMap<Node, Double> TIME_MAP = new HashMap<>();
    private static HashMap<Node, Node> PREVIOUS_NODE = new HashMap<>();

    public static ArrayList<Node> findShortestTimePath(Graph graph, Node source, Node destination) {
        if (source == destination) {
            ArrayList<Node> path = new ArrayList<>();
            path.add(source);
            return path;
        }

        // Set the time to reach each node to infinity.
        for (Node node : graph.getNodes()) {
            TIME_MAP.put(node, Double.MAX_VALUE);
            PREVIOUS_NODE.put(node, null);
            UNVISITED.add(node);
        }

        // Set the time to reach the source node to zero.
        TIME_MAP.put(source, 0d);

        Node minNode = findVertexWithMinTime();
        while (UNVISITED.size() > 0 && minNode != null) {
            minNode = findVertexWithMinTime();
            UNVISITED.remove(minNode);

            ArrayList<Edge> edges = graph.getDestinationEdges(minNode);
            for (Edge edge : edges) {
                if (UNVISITED.contains(edge.getDestination())) {
                    double alt = TIME_MAP.get(minNode) + edge.getTime();

                    if (alt < TIME_MAP.get(edge.getDestination())) {
                        TIME_MAP.put(edge.getDestination(), alt);
                        PREVIOUS_NODE.put(edge.getDestination(), minNode);
                    }
                }
            }
        }
        return getShortestPath(source, destination);
    }

    public static String getTime(Node destination) {
        if (TIME_MAP.get(destination) >= Double.MAX_VALUE) return "0 minutes";
        return String.format("%.2f", TIME_MAP.get(destination)) + " minutes";
    }

    private static ArrayList<Node> getShortestPath(Node source, Node destination) {
        ArrayList<Node> path = new ArrayList<>();
        while (PREVIOUS_NODE.get(destination) != null) {
            path.add(0, destination);
            destination = PREVIOUS_NODE.get(destination);
        }
        path.add(0, source);
        return path;
    }

    private static Node findVertexWithMinTime() {
        Node minNode = null;
        double minTime = Double.MAX_VALUE;
        for (HashMap.Entry<Node, Double> entry : TIME_MAP.entrySet()) {
            Node node = entry.getKey();
            double time = entry.getValue();
            if (UNVISITED.contains(node) && time < minTime) {
                minTime = time;
                minNode = node;
            }
        }
        return minNode;
    }
}
