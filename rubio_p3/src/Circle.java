public class Circle extends Shape2D{
    private double radius;
    public Circle(double radius){
        this.radius = radius;
        this.area = Math.PI * (Math.pow(this.radius, 2));
        this.name = "circle";
    }
    @Override
    public String getName(){
        return "circle";
    }
    @Override
    double getArea() {
        return this.area;
    }
    double getRadius(){
        return this.radius;
    }
}
