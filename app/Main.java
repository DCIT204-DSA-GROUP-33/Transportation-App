package app;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import app.algorithms.AStar;
import app.algorithms.DFS;
import app.algorithms.DijkstraTime;
import app.algorithms.DijkstraDistance;
import app.algorithms.VogelsApproximationMethod;
import app.graph.Edge;
import app.graph.Graph;
import app.graph.Node;


public class Main{
    public static void main(String[] args) throws CloneNotSupportedException {
        Graph graph = new Graph();

        // Nodes in the graph.
        Node gym = new Node("Gym");
        Node diaspora = new Node("Diaspora");
        Node ish = new Node("ISH");
        Node nightMarket = new Node("Night Market");
        Node sarbahHall = new Node("Sarbah Hall");
        Node commonWealth = new Node("Common Wealth");
        Node greatHall = new Node("Great Hall");        
        Node legonHall = new Node("Legon Hall");
        Node akuafoHall = new Node("Akuafo Hall");
        Node voltaHall = new Node("Volta Hall");
        Node balmeLibrary = new Node("Balme Library");
        Node cbas = new Node("CBAS");
        Node mainGate = new Node("Main Gate");
        Node jqb = new Node("JQ Building");
        Node lawSchool = new Node("Law School");
        Node busSchool = new Node("Business School");
        Node gcb = new Node("GCB");
        Node csdepartment = new Node("CS Department");
        Node polictialScienceDepartment = new Node("Political Science Department");
        Node nb = new Node("NB");
        Node nnb = new Node("NNB");

        graph.addEdge(new Edge(gym, diaspora, 500, 10, "George Benne Round About")); 
        graph.addEdge(new Edge(gym, ish, 415, 8,"George Benne Round About")); 
        graph.addEdge(new Edge(gym, nightMarket, 634, 9,"George Benne Round About")); 
        
        graph.addEdge(new Edge(diaspora, ish, 400, 8, "grassland")); 
        graph.addEdge(new Edge(ish, nightMarket, 214, 3, "banking square, banks, atm")); 

        graph.addEdge(new Edge(nightMarket, commonWealth, 1025, 20, "sarbah field")); 
        graph.addEdge(new Edge(nightMarket, legonHall, 914, 17, "sarbah field")); 
        graph.addEdge(new Edge(nightMarket, sarbahHall, 350, 5)); 

        graph.addEdge(new Edge(commonWealth, greatHall, 515, 9, "greater hall tower")); 
        graph.addEdge(new Edge(commonWealth, voltaHall, 440, 5, "atm")); 
        graph.addEdge(new Edge(commonWealth, legonHall, 460, 5, "atm")); 

        graph.addEdge(new Edge(sarbahHall, legonHall, 630, 12, "athletic oval, sarbah field")); 
        graph.addEdge(new Edge(sarbahHall, akuafoHall, 460, 8, "athletic oval")); 

        graph.addEdge(new Edge(legonHall, akuafoHall, 583, 7, "absa bank")); 
        graph.addEdge(new Edge(legonHall, balmeLibrary, 530, 6, "economic department")); 
        graph.addEdge(new Edge(legonHall, voltaHall, 260, 3, "atm")); 

        graph.addEdge(new Edge(akuafoHall, cbas, 385, 5, "agric department")); 
        graph.addEdge(new Edge(akuafoHall, csdepartment, 780, 13, "chemistry department")); 
        graph.addEdge(new Edge(akuafoHall, balmeLibrary, 580, 7, "absa bank")); 

        graph.addEdge(new Edge(cbas, mainGate, 624, 6)); 
        graph.addEdge(new Edge(cbas, jqb, 610, 9)); 
        graph.addEdge(new Edge(jqb, lawSchool, 466, 5)); 

        graph.addEdge(new Edge(lawSchool, csdepartment, 384, 4, "mathematics department")); 

        graph.addEdge(new Edge(balmeLibrary, lawSchool, 960, 18)); 
        graph.addEdge(new Edge(balmeLibrary, busSchool, 203, 4)); 

        graph.addEdge(new Edge(voltaHall, busSchool, 390, 3)); 
        graph.addEdge(new Edge(voltaHall, balmeLibrary, 415, 5)); 

        graph.addEdge(new Edge(voltaHall, balmeLibrary, 415, 5)); 
        
        graph.addEdge(new Edge(busSchool, gcb, 433, 5, "balme library fountain"));
        graph.addEdge(new Edge(busSchool, nb, 424, 5, "balme library fountain"));
        graph.addEdge(new Edge(busSchool, csdepartment, 389, 4)); 
        
        graph.addEdge(new Edge(polictialScienceDepartment, csdepartment, 386, 4)); 
        graph.addEdge(new Edge(polictialScienceDepartment, nb, 204, 3)); 
        graph.addEdge(new Edge(nb, nnb, 330, 4)); 
        graph.addEdge(new Edge(nnb, gcb, 160, 3));

        // User interface
        final int WINDOW_WIDTH = 800;
        final int WINDOW_HEIGHT = 600;

        String[] places = new String[graph.getNodeSize()];
        int index = 0;
        for(Node node: graph.getNodes()){
            places[index] = node.getName();
            index++;
        }

        JFrame frame = new JFrame();
        frame.setTitle("UG Transportation App");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  

        // Source
        JComboBox<String> sourceSelectionBox = new JComboBox<String>(places);
        JLabel sourceLabel = new JLabel("Source:");
        sourceLabel.setForeground(Color.BLUE);
        sourceLabel.setBounds(10,10,WINDOW_WIDTH/2-100,20);
        sourceSelectionBox.setBounds(10,30,WINDOW_WIDTH/2-100,20);
        frame.add(sourceSelectionBox);
        frame.add(sourceLabel);

        // Destination
        JComboBox<String> destinationSelectionBox = new JComboBox<String>(places);
        destinationSelectionBox.setSelectedIndex(2);
        JLabel destinationLabel = new JLabel("Destination:");
        destinationLabel.setForeground(Color.BLUE);
        destinationLabel.setBounds(WINDOW_WIDTH/2 + 50, 10, WINDOW_WIDTH/2-100,20);
        destinationSelectionBox.setBounds(WINDOW_WIDTH/2+ 50, 30, WINDOW_WIDTH/2-100,20);
        frame.add(destinationSelectionBox);
        frame.add(destinationLabel);

        // Find Shortest Button
        JButton btnFindShortestPath = new JButton("Shortest Route");
        btnFindShortestPath.setBounds(WINDOW_WIDTH/2 - 390 ,60,300,30);
        frame.add(btnFindShortestPath);

        // Find Fastest Button
        JButton btnFindFasterPath = new JButton("Fastest Route");
        btnFindFasterPath.setBounds(WINDOW_WIDTH/2 + 50, 60, 300, 30);
        frame.add(btnFindFasterPath);


        // Find label
        JLabel shortestPathLabel = new JLabel("Best Routes: ");
        shortestPathLabel.setBounds(10,110,WINDOW_WIDTH/2-100,20);
        shortestPathLabel.setForeground(Color.BLUE);
        frame.add(shortestPathLabel);
      
        // Shortest Route Result
        JLabel shortestPathResultLabl = new JLabel();
        shortestPathResultLabl.setBounds(10,130,WINDOW_WIDTH - 10,20);
        frame.add(shortestPathResultLabl);

        // Shortest Distance Result
        JLabel shortestDistanceResultLabel = new JLabel();
        shortestDistanceResultLabel.setBounds(10,150,WINDOW_WIDTH - 10,20);
        frame.add(shortestDistanceResultLabel);

        // Landmarks
        JLabel landMarkResultLabel = new JLabel("Land Marks:");
        landMarkResultLabel.setBounds(10,170,WINDOW_WIDTH - 10,20);
        frame.add(landMarkResultLabel);

        JSeparator sep = new JSeparator();  
        sep.setBounds(5,195,WINDOW_WIDTH - 5,10);
        frame.add(sep);  

        JLabel altPathLabel = new JLabel("Alternative Routes");
        altPathLabel.setForeground(Color.BLUE);
        altPathLabel.setBounds(10,220,WINDOW_WIDTH - 10,20);
        frame.add(altPathLabel);


        JTextArea area = new JTextArea();  
        JScrollPane pane = new JScrollPane();
        pane.getViewport ().setView(area);
        pane.setBounds(10,240,WINDOW_WIDTH - 20,150);
        frame.add(pane);  

        JSeparator sep2 = new JSeparator();  
        sep2.setBounds(5,400,WINDOW_WIDTH - 5,10);
        frame.add(sep2); 

        // Landmarks
        JLabel landmarkLabel = new JLabel("Are you lost? Enter a landmark to recalibrate");
        landmarkLabel.setForeground(Color.BLUE);
        landmarkLabel.setBounds(10,410,WINDOW_WIDTH - 10,20);
        frame.add(landmarkLabel);  

        JTextField landmarkInput = new JTextField();
        landmarkInput.setBounds(10, 430, WINDOW_WIDTH/ 2,30);
        frame.add(landmarkInput);  

        // Find Landmark
        JButton btnFindLandmark = new JButton("Find Landmark");
        btnFindLandmark.setBounds( WINDOW_WIDTH / 2 + 20, 430, 150, 30);
        frame.add(btnFindLandmark);


        btnFindShortestPath.addActionListener(event->{
            String sourceName = Objects.requireNonNull(sourceSelectionBox.getSelectedItem()).toString();
            String destName = Objects.requireNonNull(destinationSelectionBox.getSelectedItem()).toString();

            Node sourceNode = graph.getNodeByName(sourceName);
            Node destNode = graph.getNodeByName(destName);
            ArrayList<Node> shortestPath = DijkstraDistance.findShortestPath(graph, sourceNode, destNode);

            shortestPathResultLabl.setText("Shortest Route: " + shortestPath.toString());
            shortestDistanceResultLabel.setText("Total Distance/Duration: " + DijkstraDistance.getDistance(destNode)+", "+ DijkstraTime.getTime(destNode));
            landMarkResultLabel.setText("Landmarks : " + graph.getLandmarks(shortestPath));

            ArrayList<ArrayList<Node>> allPaths = DFS.findAllPaths(graph, sourceNode, destNode);

            StringBuilder builder = new StringBuilder();
            for (ArrayList<Node> nodes :allPaths.subList(allPaths.size() - 6, allPaths.size()-1) ){
                String distance = String.format("%.3f",  graph.calculateDistance(nodes)/1000f) + "km";
                builder.append(nodes.toString()).append(", ").append(distance).append("\n");
            }
            area.setText(builder.toString());
        });

        btnFindFasterPath.addActionListener(event->{
            String sourceName = Objects.requireNonNull(sourceSelectionBox.getSelectedItem()).toString();
            String destName = Objects.requireNonNull(destinationSelectionBox.getSelectedItem()).toString();

            Node sourceNode = graph.getNodeByName(sourceName);
            Node destNode = graph.getNodeByName(destName);
            DijkstraTime.findShortestTimePath(graph, sourceNode, destNode);
            ArrayList<Node> fastestPath = DijkstraTime.findShortestTimePath(graph, sourceNode, destNode);

            assert fastestPath != null;
            shortestPathResultLabl.setText("Fastest Route: " + fastestPath.toString());
            shortestDistanceResultLabel.setText("Total Distance/ Duration: " + VogelsApproximationMethod.getTotalCost(graph, sourceNode, destNode)+", "+DijkstraTime.getTime(destNode));
            landMarkResultLabel.setText("Landmarks : "+graph.getLandmarks(fastestPath));

            ArrayList<ArrayList<Node>> allPaths = DFS.findAllPaths(graph, sourceNode, destNode);

            StringBuilder builder = new StringBuilder();
            for (ArrayList<Node> nodes :allPaths.subList(allPaths.size() - 6, allPaths.size()-1) ){
                String time = String.format("%.3f",  graph.calculateDuration(nodes)) + "minutes";
                builder.append(nodes.toString()).append(", ").append(time).append("\n");
            }
            area.setText(builder.toString());
        });

        btnFindLandmark.addActionListener(event->{
            String landmark = landmarkInput.getText();
            List<Edge> edges = graph.findEdgesWithLandmark(landmark);

            StringBuilder builder = new StringBuilder();
            for (Edge edge : edges){
                builder.append(edge.getSource().getName()).append(" --> ").append(edge.getDestination().getName()).append("\n");
            }
            area.setText(builder.toString());
        });

        frame.setLayout(null);
        frame.setVisible(true);

    }
}