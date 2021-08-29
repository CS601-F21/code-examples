package graph.visitor;

import graph.Node;

/**
 * Interface to demonstrate the Visitor pattern.
 * @author srollins
 *
 */
public interface Visitor {

	/**
	 * Visit a Node.
	 * @param n
	 */
	public void visit(Node n);
	
}
