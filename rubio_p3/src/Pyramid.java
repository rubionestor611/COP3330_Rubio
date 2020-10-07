public class Pyramid extends Shape3D{
    private double length;
    private double width;
    private double height;
    public Pyramid(double l, double w, double h){
        this.length = l;
        this.width = w;
        this.height = h;
        this.name = "pyramid";
        this.volume = calculateVolume();
        this.area = calculateArea();
    }

    protected double calculateArea(){
        double half_width = width/2;
        double half_length = length/2;
        double solution = length * width;
        solution += length * Math.sqrt(Math.pow(half_width, 2) + Math.pow(height, 2));
        solution += width * Math.sqrt(Math.pow(half_length, 2) + Math.pow(height, 2));
        return solution;
    }

    @Override
    public double getVolume() {
        return this.volume;
    }

    @Override
    protected double calculateVolume(){
        return((length * width * height) /(double) 3);
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