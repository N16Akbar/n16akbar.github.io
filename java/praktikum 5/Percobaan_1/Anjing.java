package Percobaan_1;

public class Anjing extends Hewan {
    public Anjing(String nama) {
        super(nama);
    }

    @Override
    public void suara() {
        System.out.println("Anjing " + super.nama + " sedang menggonggong.");
    }
}
