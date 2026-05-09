package Percobaan_3;

import Percobaan_1.Hewan;

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
}
