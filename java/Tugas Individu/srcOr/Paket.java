class Paket {
    final int tanggalLahirMahasiswa = 9; // Tanggal lahir saya

    private String nama;
    private int durasiJam;
    private int harga;

    Paket() {
        nama = "Basic";
        durasiJam = 4;
        harga = 45000;
    }

    Paket(String nama, int durasiJam, int harga, int tanggalLahirMahasiswa) {
        this.nama = nama;
        this.durasiJam = durasiJam;
        if (tanggalLahirMahasiswa == 9) {
            this.harga = harga - ((harga * 15) / 100);
        } else {
            this.harga = harga;
        }
    }

    String getNama() {
        return nama;
    }

    int getDurasi() {
        return durasiJam;
    }

    int getHarga() {
        return harga;
    }

    void setPaket(String nama, int durasiJam, int harga, int tanggalLahirMahasiswa) {
        this.nama = nama;
        this.durasiJam = durasiJam;
        if (tanggalLahirMahasiswa == 9) {
            this.harga = harga - ((harga * 9) / 100);
        } else {
            this.harga = harga;
        }
    }

    void tampilPaket() {
        System.out.println("Nama            : " + getNama());
        System.out.println("Harga           : " + getHarga() + " / bulan");
        System.out.println("Durasi Jam      : " + getDurasi() + " jam");
    }
}