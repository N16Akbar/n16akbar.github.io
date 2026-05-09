package Percobaan_3;

class Kucing extends Hewan {
    Kucing(String nama) {
        super(nama);
    }

    @Override
    void suara() {
        System.out.println("Kucing " + this.nama + " mengeong.");
    }
}
