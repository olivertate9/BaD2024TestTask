import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StatisticOfFile {
    private final List<Long> allNumbers;
    private final List<Long> sorted;

    public StatisticOfFile(String fileName) {
        FileToListConverter converter = new FileToListConverter();
        allNumbers = converter.getListFromFile(fileName);
        sorted = new ArrayList<>(allNumbers);
        sorted.sort(Comparator.naturalOrder());
    }

    public long getMax() {
        return sorted.get(allNumbers.size() - 1);
    }

    public long getMin() {
        return sorted.get(0);
    }

    public double getMedian() {
        if (sorted.size() % 2 == 0) {
            long n = sorted.get(sorted.size() / 2);
            long m = sorted.get((sorted.size() / 2) - 1);
            return 0.5 * (n + m);
        } else {
            return sorted.get(sorted.size() / 2);
        }
    }

    public double getAverage() {
        int sum = 0;
        for (Long l : allNumbers) {
            sum += l;
        }
        return (double) sum / allNumbers.size();
    }

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

    public void printStats() {
        System.out.println("Max: " + getMax());
        System.out.println("Min: " + getMin());
        System.out.printf("Median: %.2f%n", getMedian());
        System.out.printf("Average: %.2f%n", getAverage());
        List<Long> longestIncreasingSequence = getLongestIncreasingSequence();
        System.out.print("Increasing sequence: ");
        longestIncreasingSequence.forEach(e -> System.out.print(e + "  "));
        System.out.println();
        List<Long> longestDecreasingSequence = getLongestDecreasingSequence();
        System.out.print("Decreasing sequence: ");
        longestDecreasingSequence.forEach(e -> System.out.print(e + "  "));
        System.out.println();
    }
}
