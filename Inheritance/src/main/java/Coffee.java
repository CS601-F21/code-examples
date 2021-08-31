public class Coffee extends Beverage {

    protected boolean isDecaf;

    public Coffee(int ounces, boolean isDecaf) {
        super(ounces);
        this.isDecaf = isDecaf;
    }

    public String getServingGlass() {
        return "Cup";
    }

    public boolean isDecaf() {
        return this.isDecaf;
    }

    public void drink() {
        System.out.println("Wow, that's hot!");
    }
}