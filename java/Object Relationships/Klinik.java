import java.util.ArrayList;

class Klinik {
    String namaKlinik;
    ArrayList<Ruangan> listRuangan;
    ArrayList<Dokter> listDokter;

    Klinik(String namaKlinik, int nomorRuangan, ArrayList<Dokter> dokter) {
        this.namaKlinik = namaKlinik;
        this.listRuangan = new ArrayList<Ruangan>(nomorRuangan);
        this.listDokter = dokter;
    }
}
