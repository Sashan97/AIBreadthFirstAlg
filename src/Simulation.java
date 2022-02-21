import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * The Simulation class describes the logic of the search algorithm, as well as
 * provides the preparation and service methods that are used during the search process
 *
 * @author Aleksandr Boldyšev
 * @author Tomaš Pieško
 * @author Giedrė Narbutaitė
 *
 * @since 1.0
 */

public class Simulation {
    private final NodeRelations relations = new NodeRelations();
    private String target;
    private final ArrayList<String> visited = new ArrayList<>();

    /**
     * Obtains the list of relations between nodes from a text file and adds them to an ArrayList.
     */
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

                // Adding relations to both directions
                relations.addRelation(firstNode, secondNode);
                relations.addRelation(secondNode, firstNode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Finds the route to the target node using Breadth First search algorithm.
     * @param initialNodeName The title of the initial node, representing the beginning of the route.
     * @param goalNodeName The title of the goal node, representing the target of the route.
     * @return Target node, if the route is found successfully; otherwise, null.
     */
    public Node findRoute(String initialNodeName, String goalNodeName){
        target = goalNodeName;
        LinkedList<Node> nodeQueue = new LinkedList<>();    // LinkedList is used as a queue
        Node current = new Node(initialNodeName);           // Set current to initial node
        if(checkTargetReached(current)) return current;     // If initial node is route target, then return

        nodeQueue.add(current);                             // Add initial to queue
        visited.add(current.getTitle());                    // Mark initial as visited

        while(!nodeQueue.isEmpty()){
            current = nodeQueue.remove();                   // Remove node from queue
            ArrayList<Relation> currentRelations = relations.getRelations(current);     // Get node relations
            ArrayList<Node> children = new ArrayList<>();
            for (Relation rel : currentRelations) {                     // Loop to convert relations to child array
                Node temp = rel.getSecondNode();
                if(checkUnvisited(temp)) temp.setPrevious(current);     // If child is unvisited set its previous attribute to current
                children.add(temp);
            }

            for(Node node : children){                                  // Loop to add child nodes to queue
                if(checkUnvisited(node)){                               // If child is unvisited...
                    if(checkTargetReached(node)) return node;           // ... and child is not route target...
                    visited.add(node.getTitle());                       // ... then mark it as visited...
                    nodeQueue.add(node);                                // ... and add to queue
                }
            }
        }
        return null;    // if queue is empty and target is still not found, return null
    }

    /**
     * Checks if the provided node is a target node.
     * @param node Node to be checked.
     * @return True, if the provided node is a target node; otherwise, false.
     */
    private boolean checkTargetReached(Node node){
        return node.getTitle().equals(target);
    }

    /**
     * Checks if the node was visited before.
     * @param node Node to be checked.
     * @return True, if the provided node is marked as visited; otherwise, false.
     */
    private boolean checkUnvisited(Node node){
        for (String name : visited) {
            if(node.getTitle().equals(name)) return false;
        }
        return true;
    }

    /**
     * Gets the object, containing the list of relations between nodes.
     * @return NodeRelations object, used in current simulation.
     */
    public NodeRelations getRelations() {
        return relations;
    }
}
