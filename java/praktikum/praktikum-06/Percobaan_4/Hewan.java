package Percobaan_4;

abstract class Hewan {
    String nama;
    int jumlahKaki;

    Hewan(String nama, int jumlahKaki) {
        this.nama = nama;
        this.jumlahKaki = jumlahKaki;
    }

    abstract void suara();

    void makan() {
        System.out.println("Hewan bernama " + this.nama + " sedang makan.");
    }

    void detailHewan() {
        System.out.println("Nama                : " + this.nama);
        System.out.println("Jumlah Kaki         : " + this.jumlahKaki);
    }
}
