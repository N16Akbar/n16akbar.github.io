package Percobaan_1;

abstract class Hewan {
    String nama;

    Hewan(String nama) {
        this.nama = nama;
    }

    abstract void suara();

    void makan() {
        System.out.println("Hewan " + this.nama + "sedang makan.");
    }

}
