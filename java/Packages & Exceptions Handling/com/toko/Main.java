package com.toko;

import com.toko.produk.Produk;

public class Main {

    public static void main(String[] args) {
        Produk produk1 = new Produk("Mie Bungkus", 3500, 20);

        System.out.println("--- Info Produk ---");
        System.out.println("Nama Produk         : " + produk1.getNama());
        System.out.println("Harga Produk        : " + produk1.getHarga());
        System.out.println("Jumlah Stok Produk  : " + produk1.getStok());
    }
}
