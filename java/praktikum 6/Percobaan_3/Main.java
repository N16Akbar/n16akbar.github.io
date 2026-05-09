package Percobaan_3;

class Main {
    public static void main(String[] args) {
        Kucing kucing = new Kucing("Anggora");
        kucing.suara();

        Burung burung = new Burung("Kicau Mania");
        burung.suara();
        burung.terbang();
    }
}
