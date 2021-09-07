package example;

/*
    A class to illustrate the Singleton pattern.
    Also see https://www.baeldung.com/java-singleton
 */
public class DBManager {

    // JDBC connection

    private static DBManager INSTANCE;

    private DBManager() {
        // do some stuff
        // set up my JDBC connection...

    }

    public static DBManager getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DBManager();
        }
        return INSTANCE;
    }


}
