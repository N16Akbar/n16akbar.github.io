package com.kampus.akademik;

class Nilai {
    void hitung(int angka) {
        System.out.println("Method hitung(int): " + angka);
    }
    
    // Overload
    void hitung(double angka) {
        System.out.println("Method hitung(double): " + (angka * angka));
    }
    void hitung(int angka1, int angka2) {
        System.out.println("Method hitung integer (rata-rata): " + ((angka1 + angka2) / 2));
    }

}
