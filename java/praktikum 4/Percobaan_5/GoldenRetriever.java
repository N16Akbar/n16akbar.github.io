package Percobaan_5;

import Percobaan_4.Anjing;

class GoldenRetriever extends Anjing {
    GoldenRetriever(String nama, int umur, String jenisGolongan) {
        super(nama, umur, jenisGolongan);
    }

    void berenang() {
        System.out.println("Anjing ini sedang berenang.");
    }

    void mengambil() {
        System.out.println("Anjing ini sedang mengambil sesuatu.");
    }
}
