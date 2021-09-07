package facebook;

import util.Observer;

/**
 * A simple class to demonstrate the Observer pattern.
 * Listens for a notification that an edge removal has happened.
 * @author srollins
 *
 */
public class UnfriendObserver implements Observer {

    public void update(Object o) {
        UnfriendEvent e = (UnfriendEvent)o;
        System.out.println(e.getNode1() + " and " + e.getNode2() + " are no longer friends!");
    }

}
