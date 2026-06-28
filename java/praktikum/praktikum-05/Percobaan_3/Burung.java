package Percobaan_3;

import Percobaan_1.Hewan;

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
}
