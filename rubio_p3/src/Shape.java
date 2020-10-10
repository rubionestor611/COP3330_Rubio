abstract class Shape {
    String name;
    double area;

    abstract String getName();
    abstract double getArea();
    abstract double calculateArea();

}
/*
    shape is abstract class
    Shape.getName() is an abstract method
    Shape.getArea() is an abstract method
    Shape2D is an abstract class
    Shape3D is an abstract class
    Shape3D.getVolume() is an abstract method
    Project is correctly stored on GitHub
    each test case passes (1 point per passing case)
 */
