package latihan2;
class Circle extends Shape {
    int radius;

    @Override
    public void area() {
        System.out.println("Luas lingkaran: " + (3.14 * (radius * radius)) + "cm^2");
    }
}
