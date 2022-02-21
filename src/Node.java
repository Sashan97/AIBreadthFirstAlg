/**
 * „Node“ klasė suteikia reikiamą infrastruktūrą, kad paieškos algoritmas veiktų,
 * pvz., mazgo pavadinimas, taip pat nuoroda į ankstesnį mazgą
 * (mazgą, kurio vaikinis elementas yra tam tikras mazgas)
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
