public class Node implements Comparable {
    private String title;
    private Node previous;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
