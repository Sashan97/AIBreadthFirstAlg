import java.util.ArrayList;

public class NodeRelations {
    ArrayList<Relation> relations;

    public void addRelation(Node firstNode, Node secondNode){
        Relation relation = new Relation(firstNode, secondNode);
        relations.add(relation);
    }

    public ArrayList<Relation> getRelations(Node node){
        ArrayList<Relation> nodeRelations = new ArrayList<>();

        for (Relation rel: relations) {
            if(rel.getFirstNode().getTitle().equals(node.getTitle())) nodeRelations.add(rel);
        }

        if(!relations.isEmpty()) return nodeRelations;
        else return null;
    }

    public ArrayList<Relation> getRelations(String nodeTitle){
        ArrayList<Relation> nodeRelations = new ArrayList<>();

        for (Relation rel: relations) {
            if(rel.getFirstNode().getTitle().equals(nodeTitle)) nodeRelations.add(rel);
        }

        if(!relations.isEmpty()) return nodeRelations;
        else return null;
    }
}