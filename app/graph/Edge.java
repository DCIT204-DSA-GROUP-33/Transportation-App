package app.graph;


public class Edge implements Comparable<Edge>, Cloneable{
    private Node source;
    private Node destination;
    private int time;
    private double distance;
    private String landMarks;

    public Edge(Node source, Node destination, int distance){
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.time = -1;
        this.landMarks = "";
    }

    public Edge(Node source, Node destination, double distance,  int time){
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.time = time;
        this.landMarks = "";
    }

    public Edge(Node source, Node destination, double distance,  int time, String landMarks){
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.time = time;
        this.landMarks = landMarks;
    }

    public Node getDestination() {
        return destination;
    }

    public Node getSource() {
        return source;
    }

    public void setSource(Node source){
        this.source = source;
    }

    public void setDestination(Node destination){
        this.destination = destination;
    }

    public double getDistance() {
        return distance;
    }
    
    public long getTime() {
        return time;
    }

    public String getLandmarks(){
        return this.landMarks;
    }

    
    @Override
    public String toString() {
        return source.getName() +  " -> " + destination.getName() + " " + getDistance() + " " + getTime();
    }

    @Override
    public int compareTo(Edge other) {
        if (getDistance() > other.getDistance())
            return 1;
        else if (getDistance()< other.getDistance())
            return -1;
        return 0;
    }

    protected Edge clone() throws CloneNotSupportedException {
        try {
            return (Edge) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
