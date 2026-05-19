import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        ArrayList<Dokter> dokter = new ArrayList<Dokter>();
        Dokter dokter1 = new Dokter("Johny Sins");
        dokter.add(dokter1);

        Pasien pasien1 = new Pasien("RIP BOZO");

        Klinik klinikBaru = new Klinik("Klinik", 2, dokter);

        dokter1.periksaPasien(pasien1);
    }
}
