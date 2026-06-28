package Percobaan_4;

class Kucing extends Hewan {
    Kucing(String nama, int jumlahKaki) {
        super(nama, jumlahKaki);
    }

    @Override
    void suara() {
        System.out.println("Kucing bernama " + this.nama + " mengeong.");
    }

    @Override
    void detailHewan() {
        super.detailHewan();
        System.out.println("Jenis               : Kucing");
        System.out.println("Kemampuan Terbang   : Tidak ada");
        System.out.println("Cara Bersuara       : Mengeong");
        System.out.println();
    }
}
