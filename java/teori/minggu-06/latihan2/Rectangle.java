package latihan2;
class Rectangle extends Shape {
    int width, height;

    @Override
    public void area() {
        System.out.println("Luas persegi panjang: " + (width * height) + "cm^2");
    }
}
