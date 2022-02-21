/**
 * The Node class provides the necessary infrastructure for the search algorithm to work,
 * such as the title of the node, as well as a reference to the previous node (the node,
 * whose subsidiary element is a particular node)
 *
 * @author Aleksandr Boldyšev
 *
 * @since 1.0
 */

public class Node {
    private final String title;
    private Node previous;

    public String getTitle() {
        return title;
    }

    public Node(String title) {
        this.title = title;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

}
