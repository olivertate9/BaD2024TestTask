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

class StatisticsTest {

    private static StatisticOfFile stats;

    @BeforeAll
    public static void setUp() throws IOException {
        File file = new File("test.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.getName()))) {
            List<Long> numbers = List.of(1L, 5L, 2L, 8L, 6L, 9L, 15L, 56L, 78L, 35L, 8L, 3L, 2L, 1L, 456L);
            for (Long n : numbers) {
                writer.write(n + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Exception in setUp");
        }
        stats = new StatisticOfFile(file.getName());
    }

    @AfterAll
    public static void tearDown() throws IOException {
        Files.delete(Path.of("test.txt"));
    }

    @Test
    void testFindMax() {
        assertEquals(456, stats.getMax());
    }

    @Test
    void testFindMin() {
        assertEquals(1, stats.getMin());
    }

    @Test
    void testFindMedianWhenOddNumbersInList() {
        assertEquals(8, stats.getMedian());
    }

    @Test
    void testFindMedianWhenEvenNumbersInList() throws IOException {
        File file = new File("even.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.getName()))) {
            List<Long> numbers = List.of(1L, 5L, 2L, 8L, 6L, 9L, 15L, 56L, 78L, 35L, 8L, 3L, 2L, 1L, 456L, 45L);
            for (Long n : numbers) {
                writer.write(n + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Exception in creating List");
        }
        stats = new StatisticOfFile(file.getName());
        assertEquals(8.0, stats.getMedian());
        Files.delete(Path.of(file.getName()));
    }

    @Test
    void testFindAverage() {
        assertEquals(45.666666666666664, stats.getAverage(), 0.000000000000001);
    }

    @Test
    void testFindLongestIncreasingSequence() {
        List<Long> expectedSequence = List.of(6L, 9L, 15L, 56L, 78L);
        assertIterableEquals(expectedSequence, stats.getLongestIncreasingSequence());
    }

    @Test
    void testFindLongestIncreasingSequenceEndsWithList() throws IOException {
        File file = new File("end.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.getName()))) {
            List<Long> numbers = List.of(1L, 5L, 2L, 8L, 6L, 9L, 15L, 56L, 1L, 2L, 3L, 4L, 67L, 686L, 6666L, 134234L);
            for (Long n : numbers) {
                writer.write(n + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Exception in creating List");
        }

        stats = new StatisticOfFile(file.getName());
        List<Long> expectedSequence = List.of(1L, 2L, 3L, 4L, 67L, 686L, 6666L, 134234L);

        assertIterableEquals(expectedSequence, stats.getLongestIncreasingSequence());
        Files.delete(Path.of(file.getName()));
    }

    @Test
    void testFindLongestDecreasingSequence() {
        List<Long> expectedSequence = List.of(78L, 35L, 8L, 3L, 2L, 1L);
        assertIterableEquals(expectedSequence, stats.getLongestDecreasingSequence());
    }

    @Test
    void testFindLongestDecreasingSequenceEndsWithList() throws IOException {
        File file = new File("end.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.getName()))) {
            List<Long> numbers = List.of(1L, 5L, 2L, 8L, 6L, 9L, 15L, 134234L, 6666L, 686L, 67L, 4L, 3L, 2L, 1L, -23L);
            for (Long n : numbers) {
                writer.write(n + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Exception in creating List");
        }

        stats = new StatisticOfFile(file.getName());
        List<Long> expectedSequence = List.of(134234L, 6666L, 686L, 67L, 4L, 3L, 2L, 1L, -23L);

        assertIterableEquals(expectedSequence, stats.getLongestDecreasingSequence());
        Files.delete(Path.of(file.getName()));
    }
}