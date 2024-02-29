import java.io.*;
import java.net.URL;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        StatisticOfFile stats = new StatisticOfFile("10m.txt");
        System.out.println("Max: " + stats.getMax());
        System.out.println("Min: " + stats.getMin());
        System.out.printf("Median: %.2f%n", stats.getMedian());
        System.out.printf("Average: %.2f%n", stats.getAverage());
        List<Long> longestIncreasingSequence = stats.getLongestIncreasingSequence();
        System.out.print("Increasing sequence: ");
        longestIncreasingSequence.forEach(e -> System.out.print(e + "  "));
        System.out.println();
        List<Long> longestDecreasingSequence = stats.getLongestDecreasingSequence();
        System.out.print("Decreasing sequence: ");
        longestDecreasingSequence.forEach(e -> System.out.print(e + "  "));
        System.out.println();
    }

}
