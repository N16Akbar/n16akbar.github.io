public class SistemHewan {
    public static void main(String[] args) {
        Hewan[] daftarHewan = {
                new Kucing("Milo", 2, "Persia"),
                new Anjing("Buddy", 3, "Golden Retriever"),
                new Burung("Rio", 1, "Hijau")
        };
        for (Hewan h : daftarHewan) {
            h.tampilInfo();
            h.suara();
            if (h instanceof Terbang) {
                Terbang t = (Terbang) h;
                t.terbang();
            }
            System.out.println("--------------------");
        }
    }
}