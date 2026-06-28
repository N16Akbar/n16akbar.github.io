package Percobaan_4;

class OperasiMatematika {
    int tambah(int a, int b) {
        return a + b;
    }

    int tambah(int a, int b, int c) {
        return a + b + c;
    }

    double tambah(double a, double b) {
        return a + b;
    }

    public static void main(String[] args) {
        OperasiMatematika operasi = new OperasiMatematika();
        System.out.println(operasi.tambah(6, 7));
        System.out.println(operasi.tambah(3, 6, 9));
        System.out.println(operasi.tambah(4.0, 1.0));
    }
}