class Mahasiswa {
    String nama;
    int nim;
    String prodi;

    Mahasiswa(String x, int y, String z) {
        this.nama = x;
        this.nim = y;
        this.prodi = z;
    }

    void tampilkanData() {
        System.out.println("Nama            : " + nama);
        System.out.println("NIM             : " + nim);
        System.out.println("Program Studi   : " + prodi);
    }
}
