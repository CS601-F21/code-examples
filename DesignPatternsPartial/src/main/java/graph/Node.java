package graph;

import java.util.ArrayList;

/**
 * A Node of a graph.
 * @author srollins
 *
 */
public class Node {
	
	private int nodeId;
	private ArrayList<Integer> edges;

	/**
	 * Construct a new graph node.
	 * @param nodeId
	 */
	public Node(int nodeId) {
		this.nodeId = nodeId;
		this.edges = new ArrayList<Integer>();
	}

	/**
	 * Add an edge from this node to another node.
	 * @param node
	 * @return
	 */
	boolean addEdge(int node) {
		if(!edges.contains(node)) {
			edges.add(node);
			return true;
		}
		return false;
	}

	/**
	 * Return the int ID of this node.
	 * @return
	 */
	public int getNodeId() {
		return nodeId;
	}

	/**
	 * Return the list of edges of this node.
	 * @return
	 */
	public ArrayList<Integer> getEdges() {
		return edges;
	}
	
	
}