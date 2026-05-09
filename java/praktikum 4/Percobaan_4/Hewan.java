package Percobaan_4;
public class Hewan {
    String nama;
    int umur;

    public Hewan(String nama, int umur) {
        this.nama = nama;
        this.umur = umur;
    }

    public void makan() {
        System.out.println(this.nama + " sedang makan.");
    }

    public void tidur() {
        System.out.println(this.nama + " sedang tidur.");
    }
}