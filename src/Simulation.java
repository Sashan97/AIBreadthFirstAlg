import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Modeliavimo klasėje aprašoma paieškos algoritmo logika, taip pat
 * pateikia paruošimo ir aptarnavimo būdus, kurie naudojami paieškos procese
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
     * Gauna ryšių tarp mazgų sąrašą iš tekstinio failo ir prideda juos prie ArrayList.
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
     * Suranda maršrutą į tikslinį mazgą naudodamas paieškos algoritmą Breadth First.
     * @param initialNodeName Pradinio mazgo pavadinimas, nurodantis maršruto pradžią.
     * @param goalNodeName Tikslo mazgo pavadinimas, nurodantis maršruto tikslą.
     * @return Tikslinis mazgas, jei maršrutas rastas sėkmingai; kitu atveju null.
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
     * Patikrina, ar pateiktas mazgas yra tikslo mazgas.
     * @param node Mazgas, kurį reikia patikrinti.
     * @return Tiesa, jei pateiktas mazgas yra tikslo mazgas; kitu atveju klaidinga.
     */
    private boolean checkTargetReached(Node node){
        return node.getTitle().equals(target);
    }

    /**
     * Patikrina, ar mazgas buvo aplankytas anksčiau.
     * @param node Mazgas, kurį reikia patikrinti.
     * @return Tiesa, jei pateiktas mazgas pažymėtas kaip aplankytas; kitu atveju klaidinga.
     */
    private boolean checkUnvisited(Node node){
        for (String name : visited) {
            if(node.getTitle().equals(name)) return false;
        }
        return true;
    }

    /**
     * Gauna objektą, kuriame yra ryšių tarp mazgų sąrašas.
     * @return NodeRelations object, naudojamas dabartiniam modeliavimui (used in current simulation).
     */
    public NodeRelations getRelations() {
        return relations;
    }
}
