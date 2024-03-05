package statistics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A class that provides statistics of a file containing numbers.
 * The class reads the numbers from the file and calculates various statistics
 * such as max, min, median, average and longest increasing/decreasing sequences.
 */
public class StatisticOfFile {
    private final List<Long> allNumbers;
    private final List<Long> sorted;

    /**
     * Constructs a StatisticOfFile object from a given file name.
     *
     * @param fileName the name of the file containing numbers
     * @throws IOException if an error occurs while reading the file
     */
    public StatisticOfFile(String fileName) throws IOException {
        FileToListConverter converter = new FileToListConverter();
        allNumbers = converter.getListFromFile(fileName);
        if (allNumbers.isEmpty()) {
            throw new RuntimeException("File is empty");
        }
        sorted = new ArrayList<>(allNumbers);
        sorted.sort(Comparator.naturalOrder());
    }

    /**
     * Returns the maximum number in the list.
     *
     * @return the maximum number in the list.
     */
    public long getMax() {
        return sorted.get(allNumbers.size() - 1);
    }

    /**
     * Returns the minimum number in the list.
     *
     * @return the minimum number in the list.
     */
    public long getMin() {
        return sorted.get(0);
    }

    /**
     * Returns the median of the list.
     * The median is the middle value if the list is odd,
     * or the average of the middle two values if the list is even.
     *
     * @return the median of the list.
     */
    public double getMedian() {
        if (sorted.size() % 2 == 0) {
            long n = sorted.get(sorted.size() / 2);
            long m = sorted.get((sorted.size() / 2) - 1);
            return 0.5 * (n + m);
        } else {
            return sorted.get(sorted.size() / 2);
        }
    }

    /**
     * Returns the average of the list.
     *
     * @return the average of the list
     */
    public double getAverage() {
        int sum = 0;
        for (Long l : allNumbers) {
            sum += l;
        }
        return (double) sum / allNumbers.size();
    }

    /**
     * Returns the longest increasing sequence in the list.
     * An increasing sequence is a subsequence of consecutive numbers that are
     * strictly greater than their previous ones. If there are multiple such sequences,
     * returns the first one found.
     *
     * @return the longest increasing sequence in the list.
     */
    public List<Long> getLongestIncreasingSequence() {
        List<Long> longest = new ArrayList<>();
        List<Long> current = new ArrayList<>();

        for (long num : allNumbers) {
            if (current.isEmpty() || num > current.get(current.size() - 1)) {
                current.add(num);
            } else {
                if (current.size() > longest.size()) {
                    longest = new ArrayList<>(current);
                }
                current.clear();
                current.add(num);
            }
        }

        if (current.size() > longest.size()) {
            longest = new ArrayList<>(current);
        }

        return longest;
    }

    /**
     * Returns the longest decreasing sequence in the list.
     * A decreasing sequence is a subsequence of consecutive numbers that are
     * strictly less than their previous ones. If there are multiple such sequences,
     * returns the first one found.
     *
     * @return the longest decreasing sequence in the list.
     */
    public List<Long> getLongestDecreasingSequence() {
        List<Long> longest = new ArrayList<>();
        List<Long> current = new ArrayList<>();

        for (long num : allNumbers) {
            if (current.isEmpty() || num < current.get(current.size() - 1)) {
                current.add(num);
            } else {
                if (current.size() > longest.size()) {
                    longest = new ArrayList<>(current);
                }
                current.clear();
                current.add(num);
            }
        }

        if (current.size() > longest.size()) {
            longest = new ArrayList<>(current);
        }

        return longest;
    }

    /**
     * Prints the statistics of the file to standard output.
     */
    public void printStats() {
        System.out.println("Max: " + getMax());
        System.out.println("Min: " + getMin());
        System.out.printf("Median: %.2f%n", getMedian());
        System.out.printf("Average: %.2f%n", getAverage());
        List<Long> longestIncreasingSequence = getLongestIncreasingSequence();
        System.out.print("Increasing sequence: ");
        longestIncreasingSequence.forEach(e -> System.out.print(e + ";  "));
        System.out.println();
        List<Long> longestDecreasingSequence = getLongestDecreasingSequence();
        System.out.print("Decreasing sequence: ");
        longestDecreasingSequence.forEach(e -> System.out.print(e + ";  "));
        System.out.println();
    }
}
