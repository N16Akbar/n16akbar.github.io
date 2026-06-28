abstract class Hewan {
    String nama;
    int umur;

    Hewan(String nama, int umur) {
        this.nama = nama;
        this.umur = umur;
    }

    abstract void suara();

    void tampilInfo() {
        System.out.println("Nama : " + nama);
        System.out.println("Umur : " + umur + " tahun");
    }
}

interface Terbang {
    void terbang();
}

class Kucing extends Hewan {
    String ras;

    Kucing(String nama, int umur, String ras) {
        super(nama, umur);
        this.ras = ras;
    }

    @Override
    void suara() {
        System.out.println(nama + " mengeong: Meow!");
    }

    @Override
    void tampilInfo() {
        super.tampilInfo();
        System.out.println("Jenis: Kucing");
        System.out.println("Ras : " + ras);
    }
}

class Anjing extends Hewan {
    String jenis;

    Anjing(String nama, int umur, String jenis) {
        super(nama, umur);
        this.jenis = jenis;
    }

    @Override
    void suara() {
        System.out.println(nama + " menggonggong: Woof!");
    }

    @Override
    void tampilInfo() {
        super.tampilInfo();
        System.out.println("Jenis: Anjing");
        System.out.println("Tipe : " + jenis);
    }
}

class Burung extends Hewan implements Terbang {
    String warna;

    Burung(String nama, int umur, String warna) {
        super(nama, umur);
        this.warna = warna;
    }

    @Override
    void suara() {
        System.out.println(nama + " berkicau: Cuit... cuit...");
    }

    @Override
    public void terbang() {
        System.out.println(nama + " terbang dengan mengepakkan sayap.");
    }

    @Override
    void tampilInfo() {
        super.tampilInfo();
        System.out.println("Jenis: Burung");
        System.out.println("Warna: " + warna);
    }
}
