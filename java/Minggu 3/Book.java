public class Book {
    String title;
    String author;
    int year;

    public Book(String x, String y, int z) {
        title = x;
        author = y;
        year = z;
    }

    public static void displayInfo(Book something) {
        System.out.println("=== Detail Buku ===");
        System.out.println("Judul: " + something.title);
        System.out.println("Penulis: " + something.author);
        System.out.println("Tahun: " + something.year);
    }

    public static void main(String[] args) {
        Book buku = new Book("Judul", "Fulan", 2006);
        displayInfo(buku);
    }
}