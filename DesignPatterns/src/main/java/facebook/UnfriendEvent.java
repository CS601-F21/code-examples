package facebook;

/**
 * Event generated when an edge is removed.
 * @author srollins
 *
 */
public class UnfriendEvent {

    private int node1;
    private int node2;

    public UnfriendEvent(int node1, int node2) {
        super();
        this.node1 = node1;
        this.node2 = node2;
    }

    public int getNode1() {
        return node1;
    }

    public int getNode2() {
        return node2;
    }
}
