package concurrent;

import java.util.LinkedList;

public class WorkQueue
{
    private final int nThreads;

    private final PoolWorker[] threads;
    private final LinkedList queue;

    private volatile boolean running;

    public WorkQueue(int nThreads)
    {
        this.nThreads = nThreads;
        queue = new LinkedList();
        threads = new PoolWorker[nThreads];
        running = true;

        for (int i=0; i<nThreads; i++) {
            threads[i] = new PoolWorker();
            threads[i].start();
        }
    }

    public void execute(Runnable r) {
        if(running) {
            synchronized (queue) {
                queue.addLast(r);
                queue.notify();
            }
        } else {
            // throw an exception or print error
        }
    }

    public void shutdown() {
        running = false;
    }

    private class PoolWorker extends Thread {

        public void run() {
            Runnable r;

            while (running) {
                synchronized(queue) {
                    while (queue.isEmpty()) {
                        try
                        {
                            queue.wait();
                        }
                        catch (InterruptedException ignored)
                        {
                        }
                    }

                    r = (Runnable) queue.removeFirst();
                }

                // If we don't catch RuntimeException,
                // the pool could leak threads
                try {
                    r.run();
                }
                catch (RuntimeException e) {
                    // You might want to log something here
                }
            }
        }
    }
}