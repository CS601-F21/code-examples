package examples.web.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A simple server to accept HTTP requests and serve
 * static files.
 */
public class FileServer {

    private volatile boolean running;
    private int port;

    private static final Logger LOGGER = LogManager.getLogger(FileServer.class);

    /**
     * Constructor requires the port on which to listen.
     * @param port
     */
    public FileServer(int port) {
        this.port = port;
        this.running = true;
    }

    /**
     * Start listening for new client connections.
     * NOTE: This is for demonstration purposes only!
     * Your solution will use multiple threads to process requests.
     * It should also use good design principles (e.g., appropriate
     * class and method decomposition) to read the request,
     * validate the request, dispatch the request to an appropriate
     * handler, and respond to the request.
     *
     * It is strongly, STRONGLY advised that you do not copy
     * and paste this code as-is into your solution.
     */
    public void startup() {
        // start
        try (ServerSocket serve = new ServerSocket(port)) {
            LOGGER.info("File Server listening on port " + port);

            while(running) {

                // accept a new client connection
                Socket socket = serve.accept();
                LOGGER.info("New connection from " + socket.getInetAddress());

                // process request
                try (
                        BufferedReader instream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter writer = new PrintWriter(socket.getOutputStream())
                )
                {

                    //read request line: https://www.w3.org/Protocols/rfc2616/rfc2616-sec5.html
                    String requestLine = instream.readLine();
                    LOGGER.info("Request: " + requestLine);

                    String[] requestLineParts = requestLine.split("\\s");
                    // TODO: validate request line
                    // TODO: confirm it contains three substrings!
                    String method = requestLineParts[0];
                    LOGGER.debug("Http Method: " + method);
                    String path = requestLineParts[1];
                    LOGGER.debug("Path: " + path);
                    String version = requestLineParts[2];
                    LOGGER.debug("Http Version: " + version);

                    // only allow GET requests
                    if(!method.equals(HttpConstants.GET)) {
                        ServerUtils.send405(writer);

                    // only serve files from a single path
                    } else if(path.startsWith(HttpConstants.FILE_PATH)) {

                        // assume that the path is releative to the current directory
                        try (BufferedReader file = Files.newBufferedReader(Paths.get("."  + path)))
                        {
                            // file was found...send 200 OK status line
                            ServerUtils.send200(writer);

                            // write file contents
                            String line;
                            while((line = file.readLine()) != null) {
                                writer.println(line);
                            }

                        } catch(IOException ioe) {
                            //oops, file was not found, send 404
                            LOGGER.info("File not found -- " + ioe.getMessage());
                            ServerUtils.send404(writer);
                        }
                    // the path provided was not found on this server
                    } else {
                        ServerUtils.send404(writer);
                    }

                } catch(IOException ioe) {
                    ioe.printStackTrace();
                }


            }
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public static void main(String[] args) {
        (new FileServer(1024)).startup();
    }

}
