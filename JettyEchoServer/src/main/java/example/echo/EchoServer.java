package example.echo;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A server to use Jetty to implement an Echo application.
 */
public class EchoServer {

    public static final int PORT = 8080;
    public static final String MSG_KEY = "messages";

    public static void main(String[] args) throws Exception {

        List<String> messages = Collections.synchronizedList(new ArrayList<>());

        Server server = new Server(PORT);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setAttribute(MSG_KEY, messages);
        context.addServlet(EchoServlet.class, "/echo");

        server.setHandler(context);
        server.start();
    }
}
