package graph.visitor.impl;

import graph.Node;
import graph.visitor.Visitor;

/**
 * A small example class to demonstrate the Visitor pattern.
 * Logic processes all nodes (in comparison to the EvenNodeVisitor).
 * @author srollins
 *
 */
public class GenericGraphVisitor implements Visitor {

	@Override
	public void visit(Node n) {
		System.out.println(n.getNodeId());		
	}

}
