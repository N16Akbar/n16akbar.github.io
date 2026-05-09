package Percobaan_5;

public class Burung extends Hewan {
    public Burung(String nama) {
        super(nama);
    }

    @Override
    public void suara() {
        System.out.println("Burung " + super.nama + " sedang berkicau. Kicau mania");
    }

    public void terbang() {
        System.out.println("Burung " + super.nama + " sedang terbang. Whoosh");
    }

    public void info() {
        System.out.println("Nama: " + super.nama + " | Jenis: Burung | Suara: Berkicau");
    }
}
