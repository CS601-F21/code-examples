package graph;

import graph.visitor.Visitor;

/**
 * Generic graph data structure interface.
 * @author srollins
 *
 */
public interface Graph {
	
	/**
	 * Add a bidirectional edge between nodes 1 and 2.
	 * @param node1
	 * @param node2
	 */
	public void addEdge(int node1, int node2); 
	
	/**
	 * Remove the edge between nodes 1 and 2.
	 * @param node1
	 * @param node2
	 */
	public void removeEdge(int node1, int node2);
	
	/**
	 * Perform a breadth-first search starting at the startNode.
	 * @param startNode
	 */
	public void bfs(int startNode);
	
	/**
	 * Perform a breadth-first search starting at the startNode.
	 * Traverse only to maxLevel.
	 * @param startNode
	 */
	public void bfs(int startNode, int maxLevel);

	/**
	 * Perform a depth first search starting at the startNode.
	 * @param startNode
	 */
	public void dfs(int startNode);

	/**
	 * Use the Visitor pattern to allow a vistor to
	 * visit every node of this graph.
	 * @param v
	 */
	public void accept(Visitor v);

}
