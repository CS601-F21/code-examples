package example;

public class SharedDataExample {

    public static void main(String[] args) {
        SharedDataStructure sds = new SharedDataStructure();
        SharedDataStructure sds2 = new SharedDataStructure();

        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 100; i++) {
                sds.addValue(i, "Value: " + i);
            }
        });

        Thread t2 = new Thread(() -> {
            for(int i = 100; i < 200; i++) {
                sds.addValue(i, "Value: " + i);
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sds.size());

    }

}
