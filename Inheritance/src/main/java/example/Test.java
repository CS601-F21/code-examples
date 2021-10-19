package example;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        Data d = new Data();
        d.showData(); // 0
        d.updateData(10);
        d.showData();
        List<Integer> value = d.getData(); // value should be 10
        value.add(20);
        d.showData();
    }
}
