class Kelas {
    private String nama;
    private String ruang;
    private int kapasitasOrang;

    Kelas() {
        nama = "Strength Class";
        ruang = "R01";
        kapasitasOrang = 40;
    }

    Kelas(String nama, String ruang, int kapasitasOrang) {
        this.nama = nama;
        this.ruang = ruang;
        this.kapasitasOrang = kapasitasOrang;
    }

    static Kelas Strength = new Kelas();
    static Kelas Cardio = new Kelas("Cardio Class", "R03", 50);
    static Kelas Yoga = new Kelas("Yoga Class", "R04", 30);

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
        System.out.println("Nama            : " + getNama());
        System.out.println("Ruang           : " + getRuang());
        System.out.println("Kapasitas Orang : " + getKapasitasOrang() + " orang");
    }
}