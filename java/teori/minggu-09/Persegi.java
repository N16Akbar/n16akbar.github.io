class Persegi extends Bentuk2D implements DapatDiwarnai {
    double sisi;

    Persegi(double sisi) {
        this.sisi = sisi;
    }

    @Override
    double hitungLuas() {
        return this.sisi * this.sisi;
    }

    @Override
    public void warnai() {
        System.out.println("Persegi ini telah diwarnai.");
    }
}