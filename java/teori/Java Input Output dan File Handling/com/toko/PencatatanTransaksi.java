package com.toko;

import com.toko.produk.Produk;
import java.io.*;
import java.util.*;

public class PencatatanTransaksi {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("com/toko/produk.txt"))) {
            String outputString;
            while ((outputString = br.readLine()) != null) {
                System.out.println(outputString);
            }
        } catch (IOException e) {
            System.out.println("Error: File tidak ditemukan.");
        }

        List<Produk> listProduk = new ArrayList<>();

        Produk produk1 = new Produk("mie goreng", 3500, 50);
        Produk produk2 = new Produk("telur ayam", 2000, 100);
        Produk produk3 = new Produk("mie rebus", 3500, 45);

        listProduk.add(produk1);
        listProduk.add(produk2);
        listProduk.add(produk3);

        try (FileWriter fw = new FileWriter("com/toko/produk.txt")) {
            for (Produk a : listProduk) {
                fw.write(a.getNama() + ",");
                fw.write(String.valueOf(a.getHarga()) + ",");
                fw.write(a.getStok() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("com/toko/log_transaksi.txt", true))) {
            bw.write("stok mie rebus -5");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
