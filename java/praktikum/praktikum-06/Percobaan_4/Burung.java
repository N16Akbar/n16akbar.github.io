package Percobaan_4;

class Burung extends Hewan implements Terbang {
    Burung(String nama, int jumlahKaki) {
        super(nama, jumlahKaki);
    }

    public void terbang() {
        System.out.println("Burung bernama " + this.nama + " terbang menggunakan sayapnya.");
    }

    @Override
    void suara() {
        System.out.println("Burung bernama " + this.nama + " berkicau.");
    }

    @Override
    void detailHewan() {
        super.detailHewan();
        System.out.println("Jenis               : Burung");
        System.out.println("Kemampuan Terbang   : Ada");
        System.out.println("Cara Bersuara       : Berkicau");
        System.out.println();
    }
}
