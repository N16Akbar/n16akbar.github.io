class RekeningBank {
    private String namaPemilik;
    private double saldo;

    private RekeningBank(String nama, double saldoAwal) {
        this.namaPemilik = nama;
        this.saldo = saldoAwal;
    }

    // public double getSaldo() {
    //     return this.saldo;
    // }

    // public void setSaldo(double saldoBaru) {
    //     if (saldoBaru >= 0)
    //         this.saldo = saldoBaru;
    //     else
    //         System.out.println("Error: Saldo tidak boleh negatif!");
    // }

    // public String getNamaPemilik() {
    //     return this.namaPemilik;
    // }

    // public void setNamaPemilik(String nama) {
    //     if (nama != "")
    //         this.namaPemilik = nama;
    //     else
    //         System.out.println("Error: Nama tidak boleh kosong!");
    // }

    public static RekeningBank createData(String nama, double saldo) {
        RekeningBank tempRekening = new RekeningBank(nama, saldo);
        return tempRekening;
    }

    public static void viewData(RekeningBank tempRekening) {
        System.out.println(tempRekening.namaPemilik);
        System.out.println(tempRekening.saldo);
    }
}

public class LatihanEncapsulation {
    public static void main(String[] args) {
        String namaAkun = "Fulan";
        double saldo = 100000;

        System.out.println("Pemanggilan class RekeningBank");
        RekeningBank rekening = RekeningBank.createData(namaAkun, saldo);
        RekeningBank.viewData(rekening);
    }
}