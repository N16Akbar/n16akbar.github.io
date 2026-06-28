package Percobaan_1;

class Kucing extends Hewan {
    Kucing(String nama) {
        super(nama);
    }

    @Override
    void suara() {
        System.out.println("Kucing " + this.nama + " sedang mengeong.");
    }
}
