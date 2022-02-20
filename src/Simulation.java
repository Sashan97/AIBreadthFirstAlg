import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Simulation {
    private final NodeRelations relations = new NodeRelations();
    private String target;
    private final ArrayList<String> visited = new ArrayList<>();

    public void addInitialRelations(){
        try (BufferedReader br = new BufferedReader(new FileReader("resources/cities.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] pair = line.split("\\s+");

                if (pair.length != 2) throw new IOException();

                Node firstNode;
                if(getRelations().checkIfAny(pair[0])){
                    firstNode = getRelations().returnNodeFromRelation(pair[0]);
                }
                else firstNode = new Node(pair[0]);

                Node secondNode;
                if(getRelations().checkIfAny(pair[1])){
                    secondNode = getRelations().returnNodeFromRelation(pair[1]);
                }
                else secondNode = new Node(pair[1]);

                getRelations().addRelation(firstNode, secondNode);
                getRelations().addRelation(secondNode, firstNode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Node findRoute(String initialNodeName, String goalNodeName){
        target = goalNodeName;
        PriorityQueue<Node> nodeQueue = new PriorityQueue<>();
        Node current = getRelations().returnNodeFromRelation(initialNodeName);

        if(checkTargetReached(current)) return current;

        nodeQueue.add(current);
        visited.add(current.getTitle());

        while(true){
            current = nodeQueue.remove();
            ArrayList<Relation> currentRelations = getRelations().getRelations(current);
            ArrayList<Node> children = new ArrayList<>();
            for (Relation rel : currentRelations) {
                Node temp = rel.getSecondNode();
                if(!checkVisited(temp)) temp.setPrevious(current);
                children.add(temp);
            }

            for(Node node : children){
                if(!checkVisited(node)){
                    if(checkTargetReached(node)) return node;
                    visited.add(node.getTitle());
                    nodeQueue.add(node);
                }
            }
        }
    }

    private boolean checkTargetReached(Node node){
        return node.getTitle().equals(target);
    }

    private boolean checkVisited(Node node){
        for (String name : visited) {
            if(node.getTitle().equals(name)) return true;
        }
        return false;
    }

    public NodeRelations getRelations() {
        return relations;
    }
}
