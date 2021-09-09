package syntax;

public class ThreadTest {

    public static void main(String[] args) {

        InfinitePrinter ip1 = new InfinitePrinter("t1");
        InfinitePrinter ip2 = new InfinitePrinter("t2");

        Thread t1 = new Thread(ip1);
        t1.start();

        Thread t2 = new Thread(ip2);
        t2.start();

    }
}
