/**
 * Santykių klasė reiškia mazgų, tarp kurių galimas tiesioginis perėjimas, porą.
 *
 * @author Aleksandr Boldyšev
 *
 * @since 1.0
 */

public class Relation {
    private Node firstNode;
    private Node secondNode;

    public Relation(Node firstNode, Node secondNode) {
        this.setFirstNode(firstNode);
        this.setSecondNode(secondNode);
    }

    public Node getSecondNode() {
        return secondNode;
    }

    public void setSecondNode(Node secondNode) {
        this.secondNode = secondNode;
    }

    public Node getFirstNode() {
        return firstNode;
    }

    public void setFirstNode(Node firstNode) {
        this.firstNode = firstNode;
    }
}
