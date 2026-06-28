package com.kampus.akademik;

class NilaiAkhir extends Nilai {
    // Override
    @Override
    void hitung(int angka) {
        System.out.println("(Override) Method hitung(int): " + angka);
    }
}
