package tugasPercobaan.Percobaan_3;

public class Mahasiswa {
    private String nama;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    private int nim;

    public int getNim() {
        return nim;
    }

    public void setNim(int nim) {
        this.nim = nim;
    }

    private String prodi;

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    private int semester;

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        if (!(semester < 1))
            this.semester = semester;
    }

}
