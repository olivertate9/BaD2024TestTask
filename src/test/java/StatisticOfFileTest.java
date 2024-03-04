import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import statistics.StatisticOfFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class StatisticOfFileTest {

    private StatisticOfFile stats;

    @BeforeEach
    public void setUp() {
        stats = new StatisticOfFile("test.txt");
    }

    @Test
    void getMax() {
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