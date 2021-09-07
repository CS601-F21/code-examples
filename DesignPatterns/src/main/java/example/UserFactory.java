package example;

public class UserFactory {

    private static final String DEBUG = "DEBUG";

    public static User getUser() {
        String userType = "";
        // TODO initialize userType

        User user = null;
        if(userType == DEBUG) {
            user = new DebugUserImpl();
        } else {
            user = new UserImpl();
        }
        return user;
    }

}
