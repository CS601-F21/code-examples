package graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A class that constructs a new Graph using the Builder pattern.
 * @author srollins
 *
 */
public class GraphBuilder {

	private Graph graph;
	private Path location;
	private String fileSuffix;
	
	/**
	 * Constructor
	 */
	public GraphBuilder() {
		this.graph = new BasicGraph();
	}

	// three data members

	/**
	 * Set the location of the directory of the source files 
	 * that will be used to build this graph.
	 * @param location
	 * @return
	 */
	public GraphBuilder setLocation(Path location) {
		this.location = location;
		return this;
	}
	
	/**
	 * Only process files with the suffix specified.
	 * @param fileSuffix
	 * @return
	 */
	public GraphBuilder setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
		return this;
	}
	
	/**
	 * Build and return the Graph object.
	 * @return
	 */
	public Graph build() {
						
		//TODO: Handle case of unset location or file suffix.
		File directory = new File(location.toUri());
		File[] edgeFiles = directory.listFiles(new GraphFileFilter());
		for(File edgeFile: edgeFiles) {
			System.out.println(edgeFile.getAbsolutePath());
			processFile(edgeFile, graph);
		}
				
		return graph;
	
	}
	
	/**
	 * Private method to process the file.
	 * @param edgeFile
	 * @param graph
	 */
	private void processFile(File edgeFile, Graph graph) {
		try (
				BufferedReader reader = Files.newBufferedReader(edgeFile.toPath())
				) {
			String line;
			while((line = reader.readLine()) != null) {
				processLine(line, graph);
			}
		} catch(IOException ioe) {
			
		}
		
	}
	
	/**
	 * Private method to process a single line.
	 * @param line
	 * @param graph
	 */
	private void processLine(String line, Graph graph) {
		String[] nodes = line.split("\\s+");
		if(nodes.length != 2) {
			System.out.println("GraphBuilder.processLine::Invalid format, too many tokens -- skipping..." + line);
			return;
		}
		try {
			int node1 = Integer.parseInt(nodes[0]);
			int node2 = Integer.parseInt(nodes[1]);
			graph.addEdge(node1, node2);
		} catch(NumberFormatException nfe) {
			System.out.println("GraphBuilder.processLine::Invalid format, token not an int -- skipping..." + line);
		}		
	}
	
	/**
	 * Inner class used to filter out files not ending with specified suffix.
	 * @author srollins
	 *
	 */
	class GraphFileFilter implements FileFilter {
		public boolean accept(File pathname) {
			return (pathname.isFile() && pathname.getName().endsWith(fileSuffix));			
		}
	}
	
}
	