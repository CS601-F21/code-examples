package examples.web.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple server that echos all messages posted.
 */
public class EchoServer {

    private volatile boolean running;
    private int port;
    private List<String> messages;

    private static final Logger LOGGER = LogManager.getLogger(EchoServer.class);

    /**
     * Constructor requires the port on which to listen.
     * @param port
     */
    public EchoServer(int port) {
        this.port = port;
        this.running = true;
        this.messages = new ArrayList<>();
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
            LOGGER.info("Echo Server listening on port " + port);

            while(running) {
                LOGGER.info("Waiting for new client connection...");

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
                    String path = requestLineParts[1];
                    String version = requestLineParts[2];

                    LOGGER.debug("Http Method: " + method);
                    LOGGER.debug("Path: " + path);
                    LOGGER.debug("Http Version: " + version);

                    // wouldn't it be nice to move this to a separate method :)
                    int contentLength = 0;
                    List<String> headers = new ArrayList<>();
                    String header;
                    while(!(header = instream.readLine()).isEmpty()) {
                        headers.add(header);

                        // assumes that the content-length header is correctly formatted
                        if(header.startsWith(HttpConstants.CONTENT_LENGTH)) {
                            String[] contentLengthParts  = header.split("\\s");
                            contentLength = Integer.parseInt(contentLengthParts[1]);
                        }
                    }

                    // only allow GET and POST requests
                    if(!method.equals(HttpConstants.GET) && !method.equals(HttpConstants.POST)) {

                        ServerUtils.send405(writer);

                    // method is GET
                    } else if(!path.startsWith(EchoServerConstants.ECHO)) {

                        ServerUtils.send404(writer);

                    } else if(method.equals(HttpConstants.GET)) {

                        ServerUtils.send200(writer);
                        writer.println(EchoServerConstants.GET_ECHO_PAGE);

                    // method is POST
                    } else if(method.equals(HttpConstants.POST)) {

                        // read message body
                        char[] bodyArr = new char[contentLength];
                        instream.read(bodyArr, 0, bodyArr.length);
                        String body = new String(bodyArr);
                        LOGGER.info("Message body: " + body);

                        // assumes body is in the expected format
                        String bodyValue = URLDecoder.decode(body.substring(body.indexOf("=")+1, body.length()), StandardCharsets.UTF_8.toString());
                        LOGGER.info("Message body value: " + bodyValue);

                        messages.add(bodyValue);

                        ServerUtils.send200(writer);
                        writer.println(EchoServerConstants.PAGE_HEADER);
                        writer.println("<h3>Messages</h3>\n");
                        writer.println("<ul>\n");
                        for(String message: messages) {
                            writer.println("<li>" + message + "</li>");
                        }
                        writer.println("</ul>\n");
                        writer.println(EchoServerConstants.PAGE_FOOTER);


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
        (new EchoServer(1024)).startup();
    }

}
