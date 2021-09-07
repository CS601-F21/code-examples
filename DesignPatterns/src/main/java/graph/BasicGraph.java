package graph;

import graph.visitor.Visitor;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Concrete implementation of the Graph interface.
 * @author srollins
 *
 */
public class BasicGraph implements Graph {

	private HashMap<Integer, Node> nodes;

	/**
	 * Construct a basic graph.
	 */
	public BasicGraph() {
		this.nodes = new HashMap<>();
	}

	/**
	 * Add a bidirectional edge between nodes 1 and 2.
	 * @param node1
	 * @param node2®
	 */

	public void addEdge(int node1, int node2) {
		initialize(node1);
		initialize(node2);

		nodes.get(node1).addEdge(node2);
		nodes.get(node2).addEdge(node1);

	}

	/**
	 * Helper method to insert a new node if it 
	 * does not already exist.
	 * @param node
	 */
	private void initialize(int node) {
		if(!nodes.containsKey(node)) {
			nodes.put(node, new Node(node));
		}
	}	
	
	/**
	 * Perform a breadth-first search starting at the startNode.
	 * @param startNode
	 */
	public void bfs(int startNode) {
		bfs(startNode, -1);
	}

	/**
	 * Perform a breadth-first search starting at the startNode.
	 * Traverse only to maxLevel.
	 * @param startNode
	 */
	public void bfs(int startNode, int maxLevel) {

		if(!nodes.containsKey(startNode)) {
			//TODO: throw an exception instead of returning.
			return;
		}

		HashMap<Integer, Boolean> visited = new HashMap<>();
		ArrayList<NodeLevel> queue = new ArrayList<>();
		
		int level = 1;
		queue.add(new NodeLevel(nodes.get(startNode), level));		
		
		while(!queue.isEmpty()) {
			NodeLevel n = queue.remove(0);
			if(!visited.containsKey(n.node.getNodeId())) {
				for(int i = 0; i < n.level; i++) {
					System.out.print("    ");
				}
				System.out.println(n.node.getNodeId());
				visited.put(n.node.getNodeId(), true);

				if(maxLevel == -1 || level <= maxLevel) {
					for(int child: n.node.getEdges()) {
						queue.add(new NodeLevel(nodes.get(child), level+1));	
					}				
					level++;
				}
			}
		}		

	}

	/**
	 * Perform a depth first search starting at the startNode.
	 * @param startNode
	 */
	public void dfs(int startNode) {
		if(!nodes.containsKey(startNode)) {
			//TODO: throw an exception instead of returning.
			return;
		}
		dfs(startNode, new HashMap<Integer, Boolean>());
	}

	/**
	 * Recursive helper method to visit all nodes depth first.
	 * @param startNode
	 * @param visited
	 */
	private void dfs(int startNode, HashMap<Integer, Boolean> visited) {
		if(!visited.containsKey(startNode)) {
			System.out.println(startNode);
			visited.put(startNode, true);
			Node node = nodes.get(startNode);
			for(int child: node.getEdges()) {
				dfs(child, visited);
			}
		}
	}

	/**
	 * Remove the edge between nodes 1 and 2.
	 * @param node1
	 * @param node2
	 */
	public void removeEdge(int node1, int node2) {
		remove(node1, node2);
		remove(node2, node1);
	}
	
	/**
	 * Helper method to remove edge in one direction.
	 * @param node1
	 * @param node2
	 */
	private void remove(int node1, int node2) {
		if(nodes.containsKey(node1)) {
			ArrayList<Integer> edges = nodes.get(node1).getEdges();
			int index = edges.indexOf(node2);
			if(index >= 0) {
				edges.remove(index);
			}
		}
	}

	/**
	 * Use the Visitor pattern to allow a vistor to
	 * visit every node of this graph.
	 * @param v
	 */
	public void accept(Visitor v) {
		for(Node node : nodes.values()) {
			node.accept(v);
		}
	}

	/**
	 * Inner class to maintain a tuple of a node and its level 
	 * for use during traversal.
	 * @author srollins
	 *
	 */
	class NodeLevel {
		Node node;
		int level;

		NodeLevel(Node node, int level) {
			this.node = node;
			this.level = level;			
		}
	}

}
