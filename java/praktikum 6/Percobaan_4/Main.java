package Percobaan_4;

class Main {
    public static void main(String[] args) {
        Hewan[] daftarHewan = {
                new Kucing("Sarimi", 4),
                new Kucing("Bakwan Jagung", 4),
                new Anjing("Bull", 4),
                new Burung("Falcon", 2),
                new Burung("Eagle", 2)
        };

        for (Hewan h : daftarHewan) {
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            h.detailHewan();
            h.suara();

            if (h instanceof Terbang) {
                Terbang t = (Terbang) h;
                t.terbang();
            }

            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println();
        }
    }
}
