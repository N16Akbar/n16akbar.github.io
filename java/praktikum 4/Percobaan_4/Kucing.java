package Percobaan_4;

class Kucing extends Hewan {
    String ras;

    Kucing(String nama, int umur, String ras) {
        super(nama, umur);
        this.ras = ras;
    }

    void mengeong() {
        System.out.println("Kucing beras " + this.ras + "ini sedang mengeong.");
    }

    void berburu() {
        System.out.println("Kucing beras " + this.ras + " sedang berburu.");
    }
}
