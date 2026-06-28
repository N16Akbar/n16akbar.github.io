package lat3;

import java.util.ArrayList;
import java.util.List;

class Main {
    public static void main(String[] args) {
        List<MataKuliah> mk = new ArrayList<>();
        mk.add(new MataKuliah());
        mk.add(new MK_Wajib());
        mk.add(new MK_Pilihan());

        for (MataKuliah matakuliah : mk) {
            // Jika dipanggil seperti ini, JVM akan memanggil seluruh method info() dari seluruh list (MataKuliah(), MK_Wajib(), MK_Pilihan()),
            // karena tidak secara eksplisit menggunakan index list.
            matakuliah.info("PBO", 3);
            matakuliah.info("Pancasila", 2);
            matakuliah.info("Interaksi Manusia", 3);
        }
    }
}
