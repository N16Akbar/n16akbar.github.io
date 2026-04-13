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
            this.harga = harga - ((harga * 15) / 100);
        } else {
            this.harga = harga;
        }
    }

    void tampilPaket() {
        System.out.println("Nama Paket      : " + getNama());
        System.out.println("Harga Paket     : Rp " + getHarga() + " / bulan (Setelah pengecekan diskon)");
        System.out.println("Durasi Jam      : " + getDurasi() + " jam");
    }
}