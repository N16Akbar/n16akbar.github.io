package Percobaan_4;

public class Anjing extends Hewan {
    String jenisGolongan;

    public Anjing(String nama, int umur, String jenisGolongan) {
        super(nama, umur);
        this.jenisGolongan = jenisGolongan;
    }

    public void menggonggong() {
        System.out.println("Anjing berjenis " + this.jenisGolongan + " ini sedang menggonggong.");
    }

    public void bermain() {
        System.out.println("Anjing berjenis " + this.jenisGolongan + " ini sedang bermain.");
    }
}
