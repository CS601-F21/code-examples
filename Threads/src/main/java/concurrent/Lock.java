package concurrent;

public class Lock {

    private boolean isHeld;

    // list a readers
    // id of writer Thread

    // acquireRead
    //      while(writer != null) {
    //          wait
    //      }
    //      add the current thread to the list of readers

    // releaseRead
    //      if the current thread is not in the list of readers
    //          throw exception
    //      remove the current thread from the list of readers
    //      if readers is empty
    //          notify any waiters

    // acquireWrite
    //      while(writer != null || !readers.isEmpty()) {
    //          wait
    //      }
    //      add myself as the writer

    public Lock() {
        isHeld = false;
    }

    public synchronized void lock() {
        //if lock is held
        while (isHeld) {
            try {
                //block/go to "sleep" until lock is unlocked
                this.wait(); // t2 is here; t3 is here
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        isHeld = true;
    }

    public synchronized void unlock() {
        /*
            if the calling thread does not have a lock
                throw an exception
         */


        if (!isHeld) {
            //ack...shouldn't happen! throw exception
        }
        isHeld = false;
        this.notifyAll();
    }
}