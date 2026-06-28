package com.toko;

import com.toko.exception.StokHabisException;
import com.toko.produk.Produk;

public class TokoService {
    int qty;

    public void beliProduk(Produk p, int qty) throws StokHabisException {
        if (qty > p.getStok())
            throw new StokHabisException(p, qty);
    }
}
