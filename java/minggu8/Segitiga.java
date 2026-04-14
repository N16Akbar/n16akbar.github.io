package minggu8;

class Segitiga extends Bentuk2D implements DapatDiwarnai {
    double alas;
    double tinggi;

    Segitiga(double alas, double tinggi) {
        this.alas = alas;
        this.tinggi = tinggi;
    }

    @Override
    double hitungLuas() {
        return (this.alas * this.tinggi) / 2;
    }

    @Override
    public void warnai() {
        System.out.println("Segitiga ini telah diwarnai.");
    }

}
