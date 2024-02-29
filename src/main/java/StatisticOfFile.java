import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StatisticOfFile {
    private List<Long> allNumbers;
    private List<Long> sorted;

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
            return  0.5 * (n + m);
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
        int indexStart = 0;
        int indexEnd = 0;
        int sequence = 0;
        int tempSequence = 0;
        int tempStart = 0;
        int tempEnd = 0;
        List<Long> res = new ArrayList<>();

        for (int i = 0; i < allNumbers.size() - 1; i++) {

            long current = allNumbers.get(i);
            long next = allNumbers.get(i + 1);

            if (current < next && tempSequence == 0) {
                tempStart = i;
                tempEnd = i + 1;
                tempSequence = tempEnd - tempStart;
            } else if (current < next) {
                tempEnd = i+1;
                tempSequence = tempEnd - tempStart;
            } else if (current > next && tempSequence > sequence) {
                sequence = tempSequence;
                indexStart = tempStart;
                indexEnd = tempEnd;
                tempSequence = 0;
            } else {
                tempSequence = 0;
            }
        }

        //check if longest sequence ends with last integer in the list
        if (tempSequence > sequence) {
            sequence = tempSequence;
            indexStart = tempStart;
            indexEnd = tempEnd;
        }

        for (int i = indexStart; i <= indexEnd; i++) {
            res.add(allNumbers.get(i));
        }

        return res;
    }

    public List<Long> getLongestDecreasingSequence() {
        int indexStart = 0;
        int indexEnd = 0;
        int sequence = 0;
        int tempSequence = 0;
        int tempStart = 0;
        int tempEnd = 0;
        List<Long> res = new ArrayList<>();

        for (int i = 0; i < allNumbers.size() - 1; i++) {

            long current = allNumbers.get(i);
            long next = allNumbers.get(i + 1);

            if (current > next && tempSequence == 0) {
                tempStart = i;
                tempEnd = i + 1;
                tempSequence = tempEnd - tempStart;
            } else if (current > next) {
                tempEnd = i+1;
                tempSequence = tempEnd - tempStart;
            } else if (current < next && tempSequence > sequence) {
                sequence = tempSequence;
                indexStart = tempStart;
                indexEnd = tempEnd;
                tempSequence = 0;
            } else {
                tempSequence = 0;
            }
        }

        //check if longest sequence ends with last integer in the list
        if (tempSequence > sequence) {
            sequence = tempSequence;
            indexStart = tempStart;
            indexEnd = tempEnd;
        }

        for (int i = indexStart; i <= indexEnd; i++) {
            res.add(allNumbers.get(i));
        }

        return res;
    }
}
