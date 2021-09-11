import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleServer {

    final static String EOT = "EOT";
    private static volatile boolean running = true;

    public static void main(String[] args) {

        CS601Logger.setup();
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

        ServerSocket server = null;
        try {
            server = new ServerSocket(1024);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        while(running) {
            //try with resources ensures socket will be closed
            try (
                    //block on accept until a new client connects
                    Socket sock = server.accept();
                    //wrap the socket input stream in a BufferedReader
                    BufferedReader instream = new BufferedReader(new InputStreamReader(sock.getInputStream()))
            ) {

                //initialize the message
                String message = "";
                //read the first line
                String line = instream.readLine();

                //keep reading until end of transmission
                while(line != null && !line.trim().equals(EOT)) {
                    //append to message
                    message += line + "\n";
                    //read next line
                    line = instream.readLine();
                }

                logger.log(Level.INFO, "Request Received: " + message);

                //display client message
                System.out.println("Client says: " + message);

            } catch(IOException ioe) {
                ioe.printStackTrace();
                logger.log(Level.SEVERE, ioe.getMessage());
            }
        }
    }
}