import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

class Barang {
	private String nama;
	private int harga;
	private int jumlah;

	public String getNama() {
		return this.nama;
	}
	public int getHarga() {
		return this.harga;
	}
	public int getJumlah() {
		return this.jumlah;
	}

	public Barang(String x, int y, int z) {
		this.nama = x;
		this.harga = y;
		this.jumlah = z;
	}

	public int hitungTotal() {
		return this.harga * this.jumlah;
	}
}

public class javaTesting {

	public static void sambutan() {
		System.out.println("Selamat Datang di Ngawi Store~");
	}

	public static void transaksi(Scanner input) {
		boolean lanjutTambah = true;
		ArrayList<Barang> keranjang = new ArrayList<Barang>();

		while (lanjutTambah) {
			System.out.print("Masukkan nama barang ke-" + (keranjang.size() + 1) + ": ");
			String namaInput = input.nextLine();
			System.out.print("Masukkan harga satuan: ");
			int hargaInput = input.nextInt();
			System.out.print("Masukkan jumlah beli: ");
			int jumlahInput = input.nextInt();

			keranjang.add(new Barang(namaInput, hargaInput, jumlahInput));

			System.out.print("Tambah lagi? (y/n): ");
			String jawaban = input.next();
			if (jawaban.equalsIgnoreCase("n"))
				lanjutTambah = false;

			input.nextLine();
		}

		int totalBayar = 0;
		for (Barang belanjaan : keranjang) {
			totalBayar += belanjaan.hitungTotal();
		}

		if (hitungDiskon(totalBayar) != 0)
			System.out.println("Selamat! Anda mendapatkan diskon 10%!");
		totalBayar -= hitungDiskon(totalBayar);

		for (Barang belanjaan : keranjang) {
			System.out.println("Anda membeli " + belanjaan.getJumlah() + " " + belanjaan.getNama());
		}
		NumberFormat kursIndo = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
		System.out.println("Total yang harus dibayar: " + kursIndo.format(totalBayar));
	}

	public static int hitungDiskon(int x) {
		if (x > 50000) {
			return (int) (x * 0.1);
		} else {
			return 0;
		}
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean lanjut = true;

		sambutan();
		while (lanjut) {
			transaksi(input);

			System.out.print("Apakah Anda ingin melanjutkan transaksi? (y/n): ");
			String jawaban = input.next();
			if (jawaban.equalsIgnoreCase("n")) {
				lanjut = false;
			}

			input.nextLine();
		}
		input.close();
	}
}