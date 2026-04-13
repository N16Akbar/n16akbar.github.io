package latihan3;

class Mobil extends Kendaraan {
    String drivetrain;

    void spec(String brand, int speed, String drivetrain) {
        System.out.println("Brand: " + brand);
        System.out.println("Top Speed: " + speed);
        System.out.println("Drivetrain: " + drivetrain);
    }
}
