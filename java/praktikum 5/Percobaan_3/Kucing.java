package Percobaan_3;

import Percobaan_1.Hewan;

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
}
