package Percobaan_2;

import Percobaan_1.Anjing;
import Percobaan_1.Burung;
import Percobaan_1.Hewan;
import Percobaan_1.Kucing;

class Main {
    public static void main(String[] args) {
        Hewan[] daftarHewan = {
                new Kucing("Anggora"),
                new Kucing("Persia"),
                new Anjing("Bulldog"),
                new Anjing("Bulldozer"),
                new Burung("Kicau Mania")
        };

        for (Hewan a : daftarHewan) {
            a.suara();
        }
    }
}
