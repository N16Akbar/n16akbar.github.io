package Percobaan_3;

class Burung extends Hewan implements Terbang {
    Burung(String nama) {
        super(nama);
    }

    public void terbang() {
        System.out.println("Burung " + this.nama + " terbang menggunakan sayapnya.");
    }

    @Override
    void suara() {
        System.out.println("Burung " + this.nama + " berkicau.");
    }
}
