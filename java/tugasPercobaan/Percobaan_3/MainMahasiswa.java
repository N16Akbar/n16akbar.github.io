package tugasPercobaan.Percobaan_3;

class MainMahasiswa {
    public static void main(String[] args) {
        Mahasiswa m = new Mahasiswa();
        m.setNama("Mifthahul Rezki Akbar");
        m.setNim(124140202);
        m.setProdi("Teknik Informatika");
        m.setSemester(4);

        System.out.println("=== Data Mahasiswa ===");
        System.out.println("Nama            : " + m.getNama());
        System.out.println("NIM             : " + m.getNim());
        System.out.println("Program Studi   : " + m.getProdi());
        System.out.println("Semester        : " + m.getSemester());
    }
}
