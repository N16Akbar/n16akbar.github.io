package tugasPercobaan.Percobaan_4;

public class MataKuliah {
    private String nama;
    private int SKS;
    private boolean wajibITERA;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getSKS() {
        return SKS;
    }

    public void setSKS(int SKS) {
        if (!(SKS < 1))
            this.SKS = SKS;
    }

    public boolean isWajibITERA() {
        return wajibITERA;
    }

    public void setWajibITERA(boolean wajibITERA) {
        this.wajibITERA = wajibITERA;
    }

}
