import java.util.Scanner;
import com.toko.TokoService;
import com.toko.exception.StokHabisException;
import com.toko.produk.Produk;

public class MainKetiga {
    public static void main(String[] args) {
        Produk barang = new Produk("Micin", 15000, 4);

        Scanner sc = new Scanner(System.in);
        System.out.println("--- List Produk --- ");
        System.out.println("1. " + barang.getNama() + " (" + barang.getStok() + " stok)");
        System.out.print("Masukkan jumlah " + barang.getNama() + " yang ingin dibeli: ");
        int qty = sc.nextInt();

        TokoService toko = new TokoService();

        try {
            toko.beliProduk(barang, qty);
            System.out.println("Berhasil!");
        } catch (StokHabisException e) {
            System.out.println(e.getMessage());
        } finally {
            sc.close();
        }
    }
}
