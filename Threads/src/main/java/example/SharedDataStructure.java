package example;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SharedDataStructure {

    private HashMap<Integer, String> values;

    public SharedDataStructure() {
        this.values = new HashMap<>();
    }

    //write method
    public void addValue(int number, String string) {
        this.values.put(number, string);
    }

    //read method
    public int size() {
        return this.values.size();
    }
}