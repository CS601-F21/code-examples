package example;

import java.util.ArrayList;
import java.util.List;

public class Data {

    private int myIntVariable;
    private List<Integer> myVariable;


    public Data() {
        myIntVariable = 0;
        myVariable = new ArrayList<>();
    }

//    public int getData() {
    public List<Integer> getData() {
        return myVariable;
    }

    public void updateData(int value) {
        myIntVariable += value;
        myVariable.add(value);
    }

    public void clearData() {
        myVariable = new ArrayList<>();
    }

    public void showData() {
        System.out.println("Data.showData::myVariable: " + myVariable);
    }

}
