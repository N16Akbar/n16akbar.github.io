package com.toko.exception;

import com.toko.produk.Produk;

public class StokHabisException extends Exception {
    public StokHabisException(Produk p, int qty) {
        super("Stok " + p.getNama() + " sudah habis!");
    }
}
