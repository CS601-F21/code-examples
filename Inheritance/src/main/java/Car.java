public class Car {

    private static int vinGenerator = 0;

    private String make;
    private int vin;

    public Car(String make) {
        this.make = make;
        this.vin = vinGenerator++;
    }

    public String getMake() {
        return make;
    }

    public static int getVinGenerator() {
        return vinGenerator;
    }

}
