package Percobaan_5;

public class Hewan {
    public String nama;

    public Hewan(String nama) {
        this.nama = nama;
    }

    public void suara() {
        System.out.println("Hewan " + this.nama + " bersuara.");
    }
}
