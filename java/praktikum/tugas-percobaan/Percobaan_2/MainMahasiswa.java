package tugasPercobaan.Percobaan_2;

class MainMahasiswa {
    public static void main(String[] args) {
        Mahasiswa m = new Mahasiswa();
        m.setNama("Mifthahul Rezki Akbar");
        m.setNim(124140202);
        m.setProdi("Teknik Informatika");

        System.out.println("=== Data Mahasiswa ===");
        System.out.println("Nama            : " + m.getNama());
        System.out.println("NIM             : " + m.getNim());
        System.out.println("Program Studi   : " + m.getProdi());
    }
}
