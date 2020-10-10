public class Square extends Shape2D{
    private double side_length;
    public Square(double side_length){
        this.side_length = side_length;
        this.name = "square";
        this.area = calculateArea();
    }
    protected double calculateArea(){
        return this.side_length * this.side_length;
    }

    @Override
    public double getArea(){
        return this.area;
    }

    @Override
    public String getName(){
        return this.name;
    }

    public double getSideLength(){
        return this.side_length;
    }
}
