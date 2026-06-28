class Kelas {
    private String nama;
    private String ruang;
    private int kapasitasOrang;

    Kelas() {
        nama = "Ketahanan";
        ruang = "R01";
        kapasitasOrang = 40;
    }

    Kelas(String nama, String ruang, int kapasitasOrang) {
        this.nama = nama;
        this.ruang = ruang;
        this.kapasitasOrang = kapasitasOrang;
    }

    String getNama() {
        return nama;
    }

    String getRuang() {
        return ruang;
    }

    int getKapasitasOrang() {
        return kapasitasOrang;
    }

    void setKelas(String nama, String ruang, int kapasitasOrang) {
        this.nama = nama;
        this.ruang = ruang;
        this.kapasitasOrang = kapasitasOrang;
    }

    void tampilKelas() {
        System.out.println("Nama Kelas      : " + getNama());
        System.out.println("Ruang           : " + getRuang());
        System.out.println("Kapasitas Orang : " + getKapasitasOrang() + " orang");
    }
}