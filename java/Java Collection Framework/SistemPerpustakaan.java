import java.util.*;
import java.time.Instant;

class SistemPerpustakaan {
    HashMap<String, String> kodeBuku;
    HashSet<String> pinjamKodeBuku;
    ArrayList<String> riwayatPeminjaman;

    SistemPerpustakaan(HashMap<String, String> kodeBuku, HashSet<String> pinjamKodeBuku,
            ArrayList<String> riwayatPeminjaman) {
        this.kodeBuku = kodeBuku;
        this.pinjamKodeBuku = pinjamKodeBuku;
        this.riwayatPeminjaman = riwayatPeminjaman;
    }

    void pinjamBuku(String ambilKodeBuku) {
        for (Map.Entry<String, String> a : kodeBuku.entrySet()) {
            if (ambilKodeBuku == a.getKey()) {
                this.pinjamKodeBuku.add(ambilKodeBuku);
                System.out.println("Buku " + this.kodeBuku.get(ambilKodeBuku) + " berhasil dipinjam.");
                riwayatPeminjaman.add("Pinjam " + ambilKodeBuku + " (" + Instant.now() + ")");
            }
        }
    }

    void kembalikanBuku(String ambilKodeBuku) {
        pinjamKodeBuku.removeIf(a -> a.equals(ambilKodeBuku));
        System.out.println("Buku " + this.kodeBuku.get(ambilKodeBuku) + " berhasil dikembalikan.");
        riwayatPeminjaman.add("Kembalikan " + ambilKodeBuku + " (" + Instant.now() + ")");
    }

    void tampilkanTersedia() {
        System.out.println("List buku:");
        for (Map.Entry<String, String> a : kodeBuku.entrySet()) {
            if (!pinjamKodeBuku.contains(a.getKey()))
                System.out.println("- (" + a.getKey() + ") " + a.getValue());
        }
    }
}

class Main {
    public static void main(String[] args) {
        HashMap<String, String> kodeBuku = new HashMap<>();
        kodeBuku.put("BK102", "10 Dosa Besar Jokowi");
        kodeBuku.put("BK103", "Gibran The Next Presiden");
        kodeBuku.put("BK201", "Tel Aviv Impressed");

        HashSet<String> ambilKodeBuku = new HashSet<>();
        ArrayList<String> riwayatPeminjaman = new ArrayList<>();

        SistemPerpustakaan kampus = new SistemPerpustakaan(kodeBuku, ambilKodeBuku, riwayatPeminjaman);
        kampus.tampilkanTersedia();
        kampus.pinjamBuku("BK201");
        kampus.pinjamBuku("BK103");
        kampus.tampilkanTersedia();
        kampus.kembalikanBuku("BK201");
        kampus.tampilkanTersedia();
    }
}