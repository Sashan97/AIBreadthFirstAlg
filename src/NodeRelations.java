import java.util.ArrayList;

/**
 * „NodeRelations“ klasė suteikia prieigą prie ryšių tarp mazgų sąrašo
 * ir teikia santykių valdymo metodus
 *
 * @author Aleksandr Boldyšev
 * @author Tomaš Pieško
 * @author Giedrė Narbutaitė
 *
 * @since 1.0
 */

public class NodeRelations {
    ArrayList<Relation> relations = new ArrayList<>();

    /**
     * Adds a new relation to the list.
     * @param firstNode First node in a pair.
     * @param secondNode Second node in a pair.
     */
    public void addRelation(Node firstNode, Node secondNode){
        Relation relation = new Relation(firstNode, secondNode);
        relations.add(relation);
    }

    /**
     * Returns a list of all relations that the specified node participates in.
     * @param node The node whose relations are to be retrieved.
     * @return The list of node relations.
     */
    public ArrayList<Relation> getRelations(Node node){
        ArrayList<Relation> nodeRelations = new ArrayList<>();

        for (Relation rel: relations) {
            if(rel.getFirstNode().getTitle().equals(node.getTitle())) nodeRelations.add(rel);
        }

        if(!relations.isEmpty()) return nodeRelations;
        else return null;
    }

    /**
     *
     * Returns a list of all relations that the specified node participates in.
     * @param nodeTitle The title of the node whose relations are to be retrieved.
     * @return The list of node relations.
     */
    public ArrayList<Relation> getRelations(String nodeTitle){
        ArrayList<Relation> nodeRelations = new ArrayList<>();

        for (Relation rel: relations) {
            if(rel.getFirstNode().getTitle().equals(nodeTitle)) nodeRelations.add(rel);
        }

        if(!relations.isEmpty()) return nodeRelations;
        else return null;
    }

    /**
     * Checks if the specified node has any relations.
     * @param nodeTitle The title of the node to be checked.
     * @return true, if at least one relation is found; otherwise, false.
     */
    public boolean checkIfAny(String nodeTitle){
        for (Relation rel : relations)
            if(rel.getFirstNode().getTitle().equals(nodeTitle)) return true;
        return false;
    }

    /**
     * Returns a Node object by its title.
     * @param nodeTitle Title of the node to be returned.
     * @return Node object, if at least one relation is found; otherwise, null.
     */
    public Node returnNodeFromRelation(String nodeTitle){
        for (Relation rel : relations)
            if(rel.getFirstNode().getTitle().equals(nodeTitle)) return rel.getFirstNode();
        return null;
    }
}
