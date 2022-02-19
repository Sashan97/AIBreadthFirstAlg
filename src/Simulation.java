import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Simulation {
    private NodeRelations relations = new NodeRelations();
    private String startPoint;
    private String target;
    private ArrayList<Node> visited;

    public void addInitialRelations(){
        try (BufferedReader br = new BufferedReader(new FileReader("resources/cities.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] pair = line.split("\\s+");

                if (pair.length != 2) throw new IOException();

                Node firstNode;
                if(relations.checkIfAny(pair[0])){
                    firstNode = relations.returnNodeFromRelation(pair[0]);
                }
                else firstNode = new Node(pair[0]);

                Node secondNode;
                if(relations.checkIfAny(pair[1])){
                    secondNode = relations.returnNodeFromRelation(pair[1]);
                }
                else secondNode = new Node(pair[1]);

                relations.addRelation(firstNode, secondNode);
                relations.addRelation(secondNode, firstNode);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void findRoute(){
        PriorityQueue<Node> nodeQueue = new PriorityQueue<>();



    }

    private boolean checkTargetReached(Node node){
        if(node.getTitle().equals(target)) return true;
        else return false;
    }
}
