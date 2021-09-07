package graph.visitor.impl;

import graph.Node;
import graph.visitor.Visitor;

/**
 * A small example class to demonstrate the Visitor pattern.
 * Logic only processes nodes with an even ID (in comparison to the GenericGraphVisitor).
 * @author srollins
 *
 */
public class EvenNodeVisitor implements Visitor {

	@Override
	public void visit(Node n) {
		int nodeId = n.getNodeId();
		if(nodeId %2 == 0) {
			System.out.println(n.getNodeId());
		}
	}

	
}
