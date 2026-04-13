public class MainMahasiswa {
    public static void main(String[] args) {
        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.nama = "Akbar";
        mahasiswa.nim = 124140202;
        mahasiswa.prodi = "Teknik Informatika";

        System.out.println("Nama            : " + mahasiswa.nama);
        System.out.println("NIM             : " + mahasiswa.nim);
        System.out.println("Program Studi   : " + mahasiswa.prodi);
    }
}
