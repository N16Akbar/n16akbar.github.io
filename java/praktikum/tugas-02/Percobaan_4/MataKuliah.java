class MataKuliah {
    String namaMk;
    int sks;

    MataKuliah(String x, int y) {
        this.namaMk = x;
        this.sks = y;
    }

    void tampilkanData() {
        System.out.println("Nama: " + namaMk);
        System.out.println("SKS : " + sks);
    }
}
