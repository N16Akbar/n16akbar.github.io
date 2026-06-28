package com.toko.produk;

import java.io.Serializable;

public class Produk implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nama;
    private double harga;
    protected int stok;

    public Produk(String nama, double harga, int stok) {
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }
}
