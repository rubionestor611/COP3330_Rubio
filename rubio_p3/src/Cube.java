public class Cube extends Shape3D {
    private double side_length;
    public Cube(double side){
        this.side_length = side;
        this.name = "cube";
        this.area = calculateArea();
        this.volume = calculateVolume();
    }
    @Override
    public double getVolume() {
        return this.volume;
    }

    @Override
    protected double calculateVolume() {
        return Math.pow(side_length, 3);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getArea() {
        return this.area;
    }

    @Override
    protected double calculateArea(){
        return (6 * Math.pow(side_length, 2));
    }
}
