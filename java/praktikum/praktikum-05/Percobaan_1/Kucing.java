package Percobaan_1;

public class Kucing extends Hewan {
    public Kucing(String nama) {
        super(nama);
    }

    @Override
    public void suara() {
        System.out.println("Kucing " + super.nama + " sedang mengeong.");
    }
}
