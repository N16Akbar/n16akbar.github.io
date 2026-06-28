public class MainDosen {
    public static void main(String[] args) {
        Dosen dosen1 = new Dosen();
        dosen1.nama = "Eko Dwi Nugroho, S.Kom., M.Cs.";
        dosen1.nidn = "0509029102";
        dosen1.prodi = "Teknik Informatika";

        dosen1.tampilkanData();

        Dosen dosen2 = new Dosen();
        dosen2.nama = "Arkham Zahri Rakhman, S.Kom., M.Eng.";
        dosen2.nidn = "0604049001";
        dosen2.prodi = "Teknik Informatika";

        dosen2.tampilkanData();
    }
}
