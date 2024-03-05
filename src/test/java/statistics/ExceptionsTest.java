package statistics;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExceptionsTest {
    private static final FileToListConverter CONVERTER = new FileToListConverter();
    private static File file;

    @BeforeAll
    public static void setUp() {
        file = new File("exception.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.getName()))) {
            writer.write("4");
            writer.newLine();
            writer.write("56R");
            writer.newLine();
            writer.write("null");
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Exception in setUp " + e.getMessage());
        }
    }

    @AfterAll
    public static void tearDown() throws IOException {
        Files.delete(Path.of(file.getName()));
    }

    @Test
    void testFileNotFoundExceptionThrown() {
        assertThrows(FileNotFoundException.class, () -> CONVERTER.getListFromFile("exception"));
    }

    @Test
    void testNumberFormatExceptionThrown() {
        assertThrows(NumberFormatException.class, () -> CONVERTER.getListFromFile(file.getName()));
    }

    @Test
    void testWhenFileEmptyExceptionThrown() throws IOException {
        File emptyFile = new File("empty.txt");
        Files.createFile(Path.of(emptyFile.getName()));
        assertThrows(RuntimeException.class, () -> new StatisticOfFile(emptyFile.getName()));
        Files.delete(Path.of(emptyFile.getName()));
    }
}
