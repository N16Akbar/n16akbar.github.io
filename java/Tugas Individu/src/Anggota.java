class Anggota {
    private String nama;
    private int tanggalLahir;
    private int tinggiBadan;
    private Paket paket;
    private Kelas kelas;

    Anggota() {
        nama = "Fulan";
        tinggiBadan = 170;
        paket = new Paket();
        kelas = new Kelas();
    }

    Anggota(String nama, int tanggalLahir, int tinggiBadan, Paket paket, Kelas kelas) {
        this.nama = nama;
        this.tanggalLahir = tanggalLahir;
        this.tinggiBadan = tinggiBadan;
        this.paket = paket;
        this.kelas = kelas;
    }

    String getNama() {
        return nama;
    }

    int getTanggalLahir() {
        return tanggalLahir;
    }

    int getTinggiBadan() {
        return tinggiBadan;
    }

    void setMember(String nama, int tanggalLahir, int tinggiBadan, Paket paket, Kelas kelas) {
        this.nama = nama;
        this.tanggalLahir = tanggalLahir;
        this.tinggiBadan = tinggiBadan;
        this.paket = paket;
        this.kelas = kelas;
    }

    void tampilMember() {
        System.out.println("Nama Anggota    : " + getNama());
        System.out.println("Tanggal Lahir   : " + getTanggalLahir());
        System.out.println("Tinggi Badan    : " + getTinggiBadan() + " cm");
        paket.tampilPaket();
        kelas.tampilKelas();
    }
}