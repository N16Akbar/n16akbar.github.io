package Percobaan_5;

public class Anjing extends Hewan {
    public Anjing(String nama) {
        super(nama);
    }

    @Override
    public void suara() {
        System.out.println("Anjing " + super.nama + " sedang menggonggong.");
    }

    public void bermain() {
        System.out.println("Anjing " + super.nama + " sedang bermain.");
    }

    public void info() {
        System.out.println("Nama: " + super.nama + " | Jenis: Anjing | Suara: Menggonggong");
    }
}
