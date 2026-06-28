package latihan3;

public class Test {
    public static void main(String[] args) {
        String brand;
        int speed;

        Mobil vehicle = new Mobil();
        brand = "Nissan";
        speed = 328;
        String drivetrain = "RWD";

        vehicle.spec(brand, speed, drivetrain);

        System.out.println("Menguji klakson...");
        vehicle.honk();

        Motor motorcycle = new Motor();
        brand = "Suzuki";
        speed = 220;

        motorcycle.spec(brand, speed);

        System.out.println("Menguji klakson...");
        vehicle.honk();
    }
}
