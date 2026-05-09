package Percobaan_5;

public class Kucing extends Hewan {
    public Kucing(String nama) {
        super(nama);
    }

    @Override
    public void suara() {
        System.out.println("Kucing " + super.nama + " sedang mengeong.");
    }

    public void berburu() {
        System.out.println("Kucing " + super.nama + " sedang berburu.");
    }

    public void info() {
        System.out.println("Nama: " + super.nama + " | Jenis: Kucing | Suara: Mengeong");
    }
}
