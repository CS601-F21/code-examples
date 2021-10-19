package example;

public class Car {

    private static int vinGenerator = 0;

    private String make;
    private int vin;

    public Car(String make) {
        this.make = make;
        this.vin = vinGenerator++;
    }

    // Car c = new Car();
    // c.method();
    // Car.staticMethod();

    // Collections.sort()
    // Math.min(...)
    // Math.abs(value)
    //

}
