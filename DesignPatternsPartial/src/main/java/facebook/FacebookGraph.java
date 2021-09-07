package facebook;
import graph.BasicGraph;
import graph.Graph;
import graph.visitor.Visitor;
import util.Observer;

import java.util.ArrayList;
/**
 * Sample class to demonstrate the Decorator pattern
 * and the Observer pattern.
 * @author srollins
 *
 */
public class FacebookGraph implements Graph {

    //rather than extending BasicGraph, FacebookGraph
    //decorates Graph
    private Graph graph;
    private ArrayList<Observer> observers;

    /**
     * Decorator is used to add additional functionality
     * after the original class has been created.
     * @param graph
     */
//    public FacebookGraph(Graph graph) {
    public FacebookGraph(Graph graph) {
        this.graph = graph;
        this.observers = new ArrayList<>();
    }


    @Override
    public void addEdge(int node1, int node2) {
        this.graph.addEdge(node1, node2);
    }

    @Override
    public void bfs(int startNode) {
        this.graph.bfs(startNode);

    }

    @Override
    public void dfs(int startNode) {
        this.graph.dfs(startNode);
    }


    @Override
    public void accept(Visitor v) {
        this.graph.accept(v);
    }

    @Override
    public void bfs(int startNode, int maxLevel) {
        this.graph.bfs(startNode, maxLevel);

    }

    /**
     * A FB-specific method to find everyone
     * two levels or fewer away from a starting node.
     * @param startNode
     */
    public void findFriendsOfFriends(int startNode) {
        bfs(startNode, 2);
    }

    /**
     * Use the Observer pattern to allow observers to
     * register to be notified of important events.
     * @param o
     */
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    /**
     * A FB-specific method to remove an edge betwen
     * friends.
     * @param node1
     * @param node2
     */
    public void unfriend(int node1, int node2) {
        removeEdge(node1, node2);
    }


    @Override
    public void removeEdge(int node1, int node2) {
        this.graph.removeEdge(node1, node2);
        // remove edge also notifies observers
        // of the state change
        UnfriendEvent edge = new UnfriendEvent(node1, node2);
        for(Observer o: observers) {
            o.update(edge);
        }
    }
}
