package com.kampus.akademik;

class Main {
    public static void main(String[] args) {
        Nilai n = new Nilai();
        n.hitung(69);
        n.hitung(1.67);
        n.hitung(67, 41);

        NilaiAkhir na = new NilaiAkhir();
        na.hitung(84);
    }
}
