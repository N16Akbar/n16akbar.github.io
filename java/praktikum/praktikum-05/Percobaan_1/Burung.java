package Percobaan_1;

public class Burung extends Hewan {
    public Burung(String nama) {
        super(nama);
    }

    @Override
    public void suara() {
        System.out.println("Burung " + super.nama + " sedang berkicau. Kicau mania");
    }
}
