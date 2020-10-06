public class Triangle extends Shape2D{
    private double base;
    private double height;
    public Triangle(double b, double h){
        this.base = b;
        this.height = h;
        this.name = "triangle";
        this.area = calculateArea();
    }
    private double calculateArea(){
        return ((base * height) / 2);
    }
    public double getBase(){
        return this.base;
    }
    public double getHeight(){
        return this.height;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getArea() {
        return this.area;
    }
}
