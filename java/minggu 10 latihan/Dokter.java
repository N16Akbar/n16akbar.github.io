class Dokter {
    String nama;

    Dokter(String nama) {
        this.nama = nama;
    }

    void periksaPasien(Pasien p) {
        System.out.println("Dokter " + this.nama + " sedang memeriksa pasien " + p.getNama());
    }
}
