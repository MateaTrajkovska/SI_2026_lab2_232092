import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SI2026Lab2Test {

    @Test
    void searchBookEveryStatementTest() {
        Library library = new Library();

        Book b1 = new Book("Clean Code", "Robert C. Martin", "Programming");
        Book b2 = new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy");
        b2.setBorrowed(true);

        library.addBook(b1);
        library.addBook(b2);

        assertThrows(IllegalArgumentException.class,
                () -> library.searchBookByTitle(""));

        List<Book> result = library.searchBookByTitle("Clean Code");
        assertNotNull(result);
        assertEquals(1, result.size());

        assertNull(library.searchBookByTitle("Harry Potter"));

        assertNull(library.searchBookByTitle("The Hobbit"));
    }

    @Test
    void borrowBookEveryBranchTest() {
        Library library = new Library();

        Book b1 = new Book("Clean Code", "Robert C. Martin", "Programming");
        Book b2 = new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy");
        b2.setBorrowed(true);

        library.addBook(b1);
        library.addBook(b2);

        assertThrows(IllegalArgumentException.class,
                () -> library.borrowBook("", "Author"));

        assertThrows(IllegalArgumentException.class,
                () -> library.borrowBook("Clean Code", ""));

        library.borrowBook("Clean Code", "Robert C. Martin");
        assertTrue(b1.isBorrowed());

        assertThrows(RuntimeException.class,
                () -> library.borrowBook("The Hobbit", "J.R.R. Tolkien"));

        assertThrows(RuntimeException.class,
                () -> library.borrowBook("Unknown", "Unknown"));
    }

    @Test
    void searchBookMultipleConditionTest() {
        Library library = new Library();

        Book b1 = new Book("Clean Code", "Robert C. Martin", "Programming");
        Book b2 = new Book("Clean Code", "Someone", "Programming");
        b2.setBorrowed(true);
        Book b3 = new Book("Effective Java", "Joshua Bloch", "Programming");

        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);

        List<Book> result = library.searchBookByTitle("Clean Code");
        assertNotNull(result);
        assertEquals(1, result.size());

        Library lib2 = new Library();
        Book borrowed = new Book("1984", "Orwell", "Dystopian");
        borrowed.setBorrowed(true);
        lib2.addBook(borrowed);
        assertNull(lib2.searchBookByTitle("1984"));

        assertNull(library.searchBookByTitle("Unknown"));

        Library lib3 = new Library();
        Book b4 = new Book("Dune", "Herbert", "SciFi");
        b4.setBorrowed(true);
        lib3.addBook(b4);
        assertNull(lib3.searchBookByTitle("Unknown"));
    }

    @Test
    void borrowBookMultipleConditionTest() {
        assertThrows(IllegalArgumentException.class,
                () -> new Library().borrowBook("", ""));

        assertThrows(IllegalArgumentException.class,
                () -> new Library().borrowBook("", "Robert C. Martin"));

        assertThrows(IllegalArgumentException.class,
                () -> new Library().borrowBook("Clean Code", ""));

        Library library = new Library();
        library.addBook(new Book("Clean Code", "Robert C. Martin", "Programming"));
        assertDoesNotThrow(() -> library.borrowBook("Clean Code", "Robert C. Martin"));
    }
}