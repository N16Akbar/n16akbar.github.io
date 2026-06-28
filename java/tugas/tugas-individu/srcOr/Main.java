import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Anggota[] listMember = new Anggota[100];
        int totalMember = 0;
        boolean keepRun = true;

        while (keepRun) {
            System.out.println("=== Sistem Manajemen Gym ===");
            System.out.println("1. Daftarkan Anggota Baru");
            System.out.println("2. Tampilkan Semua Anggota");
            System.out.println("3. Keluar");
            System.out.print("Pilihan: ");
            int pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    if (totalMember < 100) {
                        listMember[totalMember] = new Anggota();

                        System.out.print("Masukkan nama: ");
                        String nama = input.nextLine();
                        System.out.print("Masukkan tanggal lahir: ");
                        int tanggalLahir = input.nextInt();
                        System.out.print("Masukkan tinggi badan (cm): ");
                        int tinggiBadan = input.nextInt();
                        System.out.print("Masukkan paket yang dipilih (1. Basic, 2. Standard, 3. Premium): ");
                        int pilihanPaket = input.nextInt();

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
                                System.out.println("Pilihan tidak valid!");
                                break;
                        }

                        System.out.print(
                                "Masukkan kelas yang dipilih (1. Strength Class, 2. Cardio Class, 3. Yoga Class): ");
                        int pilihanKelas = input.nextInt();

                        Kelas kelas = new Kelas();
                        switch (pilihanKelas) {
                            case 1:
                                break;
                            case 2:
                                kelas = Kelas.Cardio;
                                break;
                            case 3:
                                kelas = Kelas.Yoga;
                                break;
                            default:
                                System.out.println("Pilihan tidak valid!");
                                break;
                        }

                        listMember[totalMember].setMember(nama, tanggalLahir, tinggiBadan, paket, kelas);
                        totalMember++;
                    } else {
                        System.out.println("Kapasitas member penuh!");
                    }
                    break;
                case 2:
                    for (int i = 0; i < totalMember; i++) {
                        System.out.println("=== Member ke-" + (i + 1) + " ===");
                        listMember[i].tampilMember();
                        System.out.println();
                    }
                    break;
                case 3:
                    keepRun = false;
                    System.out.println("Keluar dari sistem...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
        input.close();
    }
}