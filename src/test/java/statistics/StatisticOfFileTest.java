package statistics;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class StatisticOfFileTest {

    private static StatisticOfFile stats;

    private static File FILE;
    @BeforeAll
    public static void setUp() {
        FILE = new File("test.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE.getName()))) {
            List<Long> numbers = List.of(1L, 5L, 2L, 8L, 6L, 9L, 15L, 56L, 78L, 35L, 8L, 3L, 2L, 1L, 456L);
            for (Long n : numbers) {
                writer.write(n + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stats = new StatisticOfFile(FILE.getName());
    }

    @AfterAll
    public static void tearDown() {
        FILE.delete();
    }

    @Test
    void testFindMax_ReturnMax() {
        assertEquals(456, stats.getMax());
    }

    @Test
    void getMin() {
        assertEquals(1, stats.getMin());

    }

    @Test
    void getMedian() {
        assertEquals(8, stats.getMedian());
    }

    @Test
    void getAverage() {
        assertEquals(45.666666666666664, stats.getAverage(), 0.000000000000001);
    }

    @Test
    void getBiggestIncreasingSequence() {
        List<Long> expectedSequence = new ArrayList<>();
        expectedSequence.add(6L);
        expectedSequence.add(9L);
        expectedSequence.add(15L);
        expectedSequence.add(56L);
        expectedSequence.add(78L);

        assertIterableEquals(expectedSequence, stats.getLongestIncreasingSequence());
    }

    @Test
    void getBiggestDecreasingSequence() {
        List<Long> expectedSequence = new ArrayList<>();

        expectedSequence.add(78L);
        expectedSequence.add(35L);
        expectedSequence.add(8L);
        expectedSequence.add(3L);
        expectedSequence.add(2L);
        expectedSequence.add(1L);

        assertIterableEquals(expectedSequence, stats.getLongestDecreasingSequence());
    }
}