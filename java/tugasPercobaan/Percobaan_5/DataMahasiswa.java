package tugasPercobaan.Percobaan_5;

import tugasPercobaan.Percobaan_3.Mahasiswa;
import tugasPercobaan.Percobaan_4.MataKuliah;

class DataMahasiswa {
    public static void main(String[] args) {
        Mahasiswa m1 = new Mahasiswa();
        Mahasiswa m2 = new Mahasiswa();
        Mahasiswa m3 = new Mahasiswa();

        MataKuliah mk1 = new MataKuliah();
        MataKuliah mk2 = new MataKuliah();
        MataKuliah mk3 = new MataKuliah();

        m1.setNama("Mifthahul Rezki Akbar");
        m1.setNim(124140202);
        m1.setProdi("Teknik Informatika");

        m2.setNama("R. Askarrofi Prabularizda Anggoro");
        m2.setNim(124140070);
        m2.setProdi("Teknik Informatika");

        m3.setNama("Eric Rusdy Harits");
        m3.setNim(124140666);
        m3.setProdi("Teknik Informatika");

        mk1.setNama("Agama");
        mk1.setSKS(2);
        mk1.setWajibITERA(true);

        mk2.setNama("Sistem Operasi");
        mk2.setSKS(3);
        mk2.setWajibITERA(false);

        mk3.setNama("Pancasila");
        mk3.setSKS(2);
        mk3.setWajibITERA(true);

        System.out.println("=== Data Mahasiswa ===");
        System.out.println("Nama: " + m1.getNama());
        System.out.println("NIM: " + m1.getNim());
        System.out.println("Program Studi: " + m1.getProdi());
        System.out.println("Mata Kuliah 1: " + mk1.getNama());
        System.out.println("Mata Kuliah 2: " + mk3.getNama());
        System.out.println();

        System.out.println("Nama: " + m2.getNama());
        System.out.println("NIM: " + m2.getNim());
        System.out.println("Program Studi: " + m2.getProdi());
        System.out.println("Mata Kuliah 1: " + mk2.getNama());
        System.out.println("Mata Kuliah 2: " + mk3.getNama());
        System.out.println();

        System.out.println("Nama: " + m3.getNama());
        System.out.println("NIM: " + m3.getNim());
        System.out.println("Program Studi: " + m3.getProdi());
        System.out.println("Mata Kuliah 1: " + mk1.getNama());
        System.out.println("Mata Kuliah 2: -");
    }
}
