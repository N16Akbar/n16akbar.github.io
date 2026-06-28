package Percobaan_4;

class Anjing extends Hewan {
    Anjing(String nama, int jumlahKaki) {
        super(nama, jumlahKaki);
    }

    @Override
    void suara() {
        System.out.println("Anjing bernama " + this.nama + " sedang menggonggong.");
    }

    @Override
    void detailHewan() {
        super.detailHewan();
        System.out.println("Jenis               : Anjing");
        System.out.println("Kemampuan Terbang   : Tidak ada");
        System.out.println("Cara Bersuara       : Menggonggong");
        System.out.println();
    }
}
