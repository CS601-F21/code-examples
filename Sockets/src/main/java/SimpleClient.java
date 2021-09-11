import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleClient {

    final static int PORT = 1024;

    public static void main(String[] args) {

        //try with resources ensures socket will be closed
        try (
                //open a socket to host: localhost port: PORT
                Socket s = new Socket("localhost", PORT);
                //wrap the socket output stream in a PrintWriter that will autoFlush
                PrintWriter out = new PrintWriter(s.getOutputStream(), true)
        ) {

            //print a message
            out.println("My message!");
            //print the end of transmission token
            out.println("EOT");
            System.out.println("Communication complete");

        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}