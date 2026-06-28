package com.toko;

import java.lang.ClassNotFoundException;
import java.io.*;
import java.util.*;

import com.toko.produk.Produk;

public class TokoManager {
    public static void main(String[] args) {
        List<Produk> listProduk = new ArrayList<>();

        Produk produk1 = new Produk("mie goreng", 3500, 50);
        Produk produk2 = new Produk("telur ayam", 2000, 100);
        Produk produk3 = new Produk("mie rebus", 3500, 50);

        listProduk.add(produk1);
        listProduk.add(produk2);
        listProduk.add(produk3);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("com/toko/produk.dat"))) {
            oos.writeObject(listProduk);
            System.out.println("Produk berhasil disimpan.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("com/toko/produk.dat"))) {
            int i = 1;
            List<Produk> readListProduk = (ArrayList<Produk>) ois.readObject();
            for (Produk a : readListProduk) {
                System.out.println("Produk " + i);
                System.out.println("Nama  : " + a.getNama());
                System.out.println("Harga : " + a.getHarga());
                System.out.println("Stok  : " + a.getStok());
                i++;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
