package syntax;

public class Adder {

    int value = 0;

    public static void main(String[] args) {

        Adder a = new Adder();

        Thread t1 = new Thread() {
            public void run() {
                for(int i = 0; i < 30000; i++) {
                    a.value++;
                }
                System.out.println("thread 1 complete");
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                for(int i = 0; i < 30000; i++) {
                    a.value++;
                }
                System.out.println("thread 2 complete");
            }
        };

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(a.value);

    }

}