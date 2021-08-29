import graph.BasicGraph;
import graph.Graph;
import graph.GraphBuilder;

import java.nio.file.Paths;

public class Test {

    public static void main(String[] args) {

		Graph g = (new GraphBuilder())
		.setFileSuffix("edges")
		.setLocation(Paths.get("data/"))
		.build();

		g.bfs(82, 4);



//
//        Graph g = new BasicGraph();
//        g.addEdge(1, 2);
//        g.addEdge(1, 3);
//
//        g.addEdge(2, 4);
//        g.addEdge(2, 5);
//
//        g.addEdge(3, 1);
//
//        g.addEdge(4, 2);
//        g.addEdge(4, 6);
//
//        g.addEdge(5, 2);
//        g.addEdge(5, 6);
//
//        g.addEdge(6, 4);
//        g.addEdge(6, 5);
//
//        g.bfs(1, 3);
//
//        g.removeEdge(2, 5);
//
//        //g.bfs(1);

    }
}
