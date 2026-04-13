public class overloading {
    public static int side;
    public static int length;
    public static int width;

    // Persegi
    public static int area(int side) {
        return side * side;
    }

    // Persegi Panjang
    public static int area(int length, int width) {
        return length * width;
    }

    public static void main(String[] args) {
        // Contoh
        side = 4;
        length = 7;
        width = 8;

        System.out.println(area(side));
        System.out.println(area(length, width));
    }
}
