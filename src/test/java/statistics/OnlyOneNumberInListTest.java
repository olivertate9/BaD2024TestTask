package statistics;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class OnlyOneNumberInListTest {

    private static StatisticOfFile stats;

    @BeforeAll
    public static void setUp() throws IOException {
        File file = new File("one.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.getName()))) {
            writer.write("1");
        } catch (IOException e) {
            throw new RuntimeException("Exception in setUp");
        }
        stats = new StatisticOfFile(file.getName());
    }

    @AfterAll
    public static void tearDown() throws IOException {
        Files.delete(Path.of("one.txt"));
    }

    @Test
    void testFindMedianWithOnlyOneNumberInList() {
        assertEquals(1, stats.getMedian());
    }

    @Test
    void testFindAverageWithOnlyOneNumberInList() {
        assertEquals(1, stats.getAverage());
    }

    @Test
    void testFindLongestIncreasingSequenceWithOneNumber() {
        List<Long> list = List.of(1L);
        assertIterableEquals(list, stats.getLongestIncreasingSequence());
    }

    @Test
    void testFindLongestDecreasingSequenceWithOneNumber() {
        List<Long> list = List.of(1L);
        assertIterableEquals(list, stats.getLongestDecreasingSequence());
    }
}
