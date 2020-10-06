public class Sphere extends Shape3D{
    private double radius;
    public Sphere(double radius){
        this.name = "sphere";
        this.radius = radius;
        this.area = calculateArea();
        this.volume = calculateVolume();
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
    public double getVolume(){
        return this.volume;
    }
    private double calculateArea(){
        //a = 4pir^2
        return ((double) 4 * Math.PI * Math.pow(this.radius, 2));
    }
    @Override
    protected double calculateVolume(){
        //v = 4/3 pi r^2
        double ratio = (double)4/(double) 3;
        return ratio * Math.PI * Math.pow(this.radius, 3);
    }
}
