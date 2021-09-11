import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CS601Logger {

    //http://www.vogella.com/tutorials/Logging/article.html
    public static void setup() {

        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.setLevel(Level.INFO);

        FileHandler fileout;
        try {
            fileout = new FileHandler("out.log");
            fileout.setFormatter(new SimpleFormatter());
            logger.addHandler(fileout);
        } catch (SecurityException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}