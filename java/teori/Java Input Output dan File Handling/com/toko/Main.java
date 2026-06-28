package com.toko;

import com.toko.produk.Produk;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Produk> listProduk = new ArrayList<>();

        Produk produk1 = new Produk("mie goreng", 3500, 50);
        Produk produk2 = new Produk("telur ayam", 2000, 100);
        Produk produk3 = new Produk("mie rebus", 3500, 50);

        listProduk.add(produk1);
        listProduk.add(produk2);
        listProduk.add(produk3);

        try (FileWriter fw = new FileWriter("com/toko/produk.txt")) {
            // fw.write("List Produk:\n");
            for (Produk a : listProduk) {
                fw.write(a.getNama() + ",");
                fw.write(String.valueOf(a.getHarga()) + ",");
                fw.write(a.getStok() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try (FileReader fr = new FileReader("com/toko/produk.txt")) {
            int karakter;
            while ((karakter = fr.read()) != -1)
                System.err.print((char) karakter);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
