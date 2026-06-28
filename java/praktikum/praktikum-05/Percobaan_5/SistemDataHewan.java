package Percobaan_5;

class SistemDataHewan {
    public static void main(String[] args) {
        Kucing daftarKucing[] = {
                new Kucing("Anggora"),
                new Kucing("Persia")
        };
        Anjing daftarAnjing[] = {
                new Anjing("Bulldog"),
                new Anjing("Bulldozer")
        };
        Burung daftarBurung[] = {
                new Burung("Kicau Mania")
        };
        for (Kucing a : daftarKucing) {
            a.info();
        }
        for (Anjing b : daftarAnjing) {
            b.info();
        }
        for (Burung c : daftarBurung) {
            c.info();
        }
    }
}
