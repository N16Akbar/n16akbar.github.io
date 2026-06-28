package lat2;

class Main {
    public static void main(String[] args) {
        // Upcast
        Dosen d = new DosenTetap();
        d.mengajar("Fulan");

        // Downcast
        if (d instanceof DosenTetap) {
            DosenTetap a = (DosenTetap) d;
            a.mengajar("Fulan");
            a.meneliti("Fulan");
        }

        // Invalid downcast
        Dosen e = new DosenTamu();
        DosenTetap b = (DosenTetap) e;
    }
}
