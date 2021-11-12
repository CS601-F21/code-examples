import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.http.HttpStatus;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Example to demonstrate using JUnit and Mockito
 * unit test a Servlet doGet method.
 */
public class MessageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // GET ?message="value" should add "value" to the data structure
        // stored as attribute data in the ServletContext
        String message = req.getParameter("message");

        if(message == null || message.isEmpty()) {
            // if the parameter was not present in the request
            // return a 400
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
        } else {
            // the parameter was present, so insert it into the
            // data structure and return a 200
            ((List<String>)req.getServletContext().getAttribute("data")).add(message);
            resp.setStatus(HttpStatus.OK_200);
        }
    }
}
