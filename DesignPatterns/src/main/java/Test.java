import facebook.FacebookGraph;
import facebook.UnfriendEvent;
import facebook.UnfriendObserver;
import graph.BasicGraph;
import graph.Graph;
import graph.GraphBuilder;
import graph.visitor.Visitor;
import graph.visitor.impl.EvenNodeVisitor;

import java.nio.file.Paths;

public class Test {

    public static void main(String[] args) {

    	// use the builder to generate a generic graph
		Graph g = (new GraphBuilder())
				.setFileSuffix("edges")
				.setLocation(Paths.get("data/"))
				.build();

		// decorate the existing graph as a FacebookGraph
		FacebookGraph fbgraph = new FacebookGraph(g);

		// demonstrate use of the visitor
		Visitor evenVisitor = new EvenNodeVisitor();
		fbgraph.accept(evenVisitor);

		UnfriendObserver unfriendObserver = new UnfriendObserver();
		fbgraph.addObserver(unfriendObserver);

    }
}
