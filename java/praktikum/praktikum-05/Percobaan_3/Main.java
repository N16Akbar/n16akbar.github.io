package Percobaan_3;

import Percobaan_1.Hewan;

class Main {
    public static void main(String[] args) {
        Hewan kucing1 = new Kucing("Anggora");
        Hewan kucing2 = new Kucing("Persia");
        Hewan anjing1 = new Anjing("Bulldog");
        Hewan anjing2 = new Anjing("Bulldozer");
        Hewan burung1 = new Burung("Kicau Mania");

        kucing1.suara();
        kucing2.suara();
        anjing1.suara();
        anjing2.suara();
        burung1.suara();

        Kucing kucing1d = (Kucing) kucing1;
        kucing1d.berburu();

        Kucing kucing2d = (Kucing) kucing2;
        kucing2d.berburu();

        Anjing anjing1d = (Anjing) anjing1;
        anjing1d.bermain();

        Anjing anjing2d = (Anjing) anjing2;
        anjing2d.bermain();

        Burung burung1d = (Burung) burung1;
        burung1d.terbang();
    }
}
