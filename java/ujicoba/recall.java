import java.util.Scanner;
import java.util.ArrayList;

// PERBAIKAN 1: Keluarkan class ini dari dalam class 'recall'
// Biar dia berdiri sendiri dan gampang dipanggil.
class DataMahasiswa { // Nama class sebaiknya HurufBesar (PascalCase)
    private String nama;
    private String nim;
    private int nilai;
    private String jurusan;

    // PERBAIKAN 2: Ini CONSTRUCTOR.
    // Namanya harus SAMA PERSIS dengan nama Class.
    // Tidak pakai 'void', tidak pakai 'return'.
    // Ini yang dipanggil saat kamu ketik command 'new'
    public DataMahasiswa(String nama, String nim, int nilai, String jurusan) {
        this.nama = nama;
        this.nim = nim;
        this.nilai = nilai;
        this.jurusan = jurusan;
    }

    public String getNama() {
        return this.nama;
    }

    public String getNim() {
        return this.nim;
    }

    public int getNilai() {
        return this.nilai;
    }

    public String getJurusan() {
        return this.jurusan;
    }
}

public class recall { // Nama class utama disamakan dengan nama file (Recall)
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<DataMahasiswa> daftarNilai = new ArrayList<DataMahasiswa>();

        boolean lanjut = true;

        // Loop agar program tidak langsung mati
        while (lanjut) {
            System.out.println("\n--- Input Data ---");
            // Sedikit trik visual biar nomornya mulai dari 1 (size + 1)
            System.out.print("Masukkan nama Mahasiswa ke-" + (daftarNilai.size() + 1) + ": ");
            String inputNama = input.nextLine();

            System.out.print("Masukkan NIM: ");
            String inputNim = input.nextLine();
            boolean ulang = true;
            for (DataMahasiswa mhsNIM : daftarNilai) {
                if (inputNim.equals(mhsNIM))
                    System.out.println("Data sudah ada! Ulangi dengan NIM yang berbeda.");
            }

            System.out.print("Masukkan nilai: ");
            int inputNilai = input.nextInt();

            input.nextLine();

            System.out.print("Masukkan jurusan: ");
            String inputJurusan = input.nextLine();

            // PERBAIKAN 3: Cara memasukkan ke ArrayList
            // 'new DataMahasiswa(...)' otomatis memanggil Constructor di atas.
            daftarNilai.add(new DataMahasiswa(inputNama, inputNim, inputNilai, inputJurusan));

            System.out.print("Tambah lagi? (y/n): ");
            String jawab = input.next();

            if (jawab.equalsIgnoreCase("n")) {
                lanjut = false;
            }

            input.nextLine(); // PENTING: Membersihkan buffer enter (Scanner Trap)
        }

        // Menampilkan Hasil (Active Recall Loop/Output)
        System.out.println("\n--- Hasil Rekap Nilai ---");
        int totalNilai = 0;

        for (DataMahasiswa mhs : daftarNilai) {
            System.out.println(
                    "Nama: " + mhs.getNama() + " | Nilai: " + mhs.getNilai() + " | Jurusan: " + mhs.getJurusan());
            totalNilai += mhs.getNilai();
        }

        // Bonus: Rata-rata (Perlu casting double biar presisi)
        if (daftarNilai.size() > 0) {
            double rataRata = (double) totalNilai / daftarNilai.size();
            System.out.println("Rata-rata Kelas: " + rataRata);
        }

        input.close();
    }
}