package example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SharedDataStructure {

    private HashMap<Integer, String> values;
    private ReentrantReadWriteLock lock;

    public SharedDataStructure() {
        this.values = new HashMap<>();
        this.lock = new ReentrantReadWriteLock();
    }

    // write
    public void addValue(int number, String string) {
        // acquire a write lock
        this.lock.writeLock().lock();

        try {
            this.values.put(number, string);
            //

        } finally {
            // release a write lock
            this.lock.writeLock().unlock();
        }
    }

    // read
    public int size() {

        // acquire a read lock
        this.lock.readLock().lock();

        try {
            return this.values.size();
        } finally {
            // release a read lock
            this.lock.readLock().unlock();
        }

    }

    // write/write -> conflict? yes
    // write/read -> conflict? yes
    // read/read -> conflict? no


}