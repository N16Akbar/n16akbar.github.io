package Percobaan_1;

class Anjing extends Hewan {
    Anjing(String nama) {
        super(nama);
    }

    @Override
    void suara() {
        System.out.println("Anjing " + this.nama + "sedang menggonggong.");
    }
}
