class Main {
    public static void main(String[] args) {
        Bentuk2D persegi = new Persegi(41.69);
        Bentuk2D segitiga = new Segitiga(6, 7);

        persegi.tampilkanInfo();
        System.out.println("Hasil perhitungan luas dari persegi tersebut adalah: " + persegi.hitungLuas());
        Persegi downcastPersegi = (Persegi) persegi;
        downcastPersegi.warnai();

        segitiga.tampilkanInfo();
        System.out.println("Hasil perhitungan luas dari segitiga tersebut adalah: " + segitiga.hitungLuas());
        Segitiga downcastSegitiga = (Segitiga) segitiga;
        downcastSegitiga.warnai();
    }

}
