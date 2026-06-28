import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // berfungsi seperti "cin >>" di C++
        Anggota[] listMember = new Anggota[100];
        int totalMember = 0;
        boolean jalan = true;

        System.out.println("Penjelasan:");
        System.out.println("* Object Anggota dibuat untuk merepresentasikan satu anggota gym");
        System.out.println("* Object Paket digunakan untuk menyimpan data paket membership");
        System.out.println("* Object Kelas merepresentasikan ruangan dan aktivitas yang dipilih");
        System.out.println("* Method tampilMember, tampilPaket, dan tampilKelas digunakan");
        System.out.println("  untuk menampilkan informasi atribut dari masing-masing object");
        System.out.println("* Constructor pada object Paket memproses diskon sebesar 15% jika");
        System.out.println("  input tanggal lahir sesuai dengan tanggal lahir saya, yaitu tanggal 9");

        while (jalan) {
            System.out.println("\n\n=== Sistem Manajemen Gym ===");
            System.out.println("1. Daftarkan Anggota Baru");
            System.out.println("2. Tampilkan Semua Anggota");
            System.out.println("3. Keluar");
            System.out.print("Pilihan: ");
            int pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    if (totalMember < 100) {
                        System.out.print("Masukkan nama: ");
                        String nama = input.nextLine();
                        System.out.print("Masukkan tanggal lahir (angka): ");
                        int tanggalLahir = input.nextInt();
                        input.nextLine();
                        System.out.print("Masukkan tinggi badan (cm): ");
                        int tinggiBadan = input.nextInt();
                        input.nextLine();

                        System.out.print("Masukkan paket yang dipilih (1. Basic, 2. Standard, 3. Premium): ");
                        int pilihanPaket = input.nextInt();
                        input.nextLine();

                        Paket paket = new Paket();
                        switch (pilihanPaket) {
                            case 1:
                                paket = new Paket("Basic", 4, 45000, tanggalLahir);
                                break;
                            case 2:
                                paket = new Paket("Standard", 12, 58000, tanggalLahir);
                                break;
                            case 3:
                                paket = new Paket("Premium", 18, 70000, tanggalLahir);
                                break;
                            default:
                                System.out.println("Pilihan paket tidak valid!");
                                break;
                        }

                        System.out.print("Masukkan kelas yang dipilih (1. Ketahanan, 2. Kardio, 3. Yoga): ");
                        int pilihanKelas = input.nextInt();
                        input.nextLine();

                        Kelas kelas = new Kelas();
                        switch (pilihanKelas) {
                            case 1:
                                kelas = new Kelas();
                                break;
                            case 2:
                                kelas = new Kelas("Kardio", "R03", 50);
                                break;
                            case 3:
                                kelas = new Kelas("Yoga", "R04", 30);
                                break;
                            default:
                                System.out.println("Pilihan kelas tidak valid!");
                                break;
                        }

                        listMember[totalMember] = new Anggota(nama, tanggalLahir, tinggiBadan, paket, kelas);
                        totalMember++;
                        System.out.println("Anggota berhasil didaftarkan.");
                    } else {
                        System.out.println("Kapasitas member penuh!");
                    }
                    break;

                case 2:
                    if (totalMember == 0) {
                        System.out.println("Tidak ada anggota.");
                    } else {
                        for (int i = 0; i < totalMember; i++) {
                            System.out.println("=== Member ke-" + (i + 1) + " ===");
                            listMember[i].tampilMember();
                        }
                        System.out.println();
                    }
                    break;

                case 3:
                    jalan = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
        input.close();
    }
}