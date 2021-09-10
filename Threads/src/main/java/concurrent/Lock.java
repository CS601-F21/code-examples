package concurrent;

public class Lock {

    private boolean isHeld;

    public Lock() {
        isHeld = false;
    }

    public synchronized void lock() {
        //if lock is held
        while (isHeld) {
            try {
                //block/go to "sleep" until lock is unlocked
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        isHeld = true;
    }

    public synchronized void unlock() {
        if (!isHeld) {
            //ack...shouldn't happen! throw exception
        }
        isHeld = false;
        this.notifyAll();
    }
}