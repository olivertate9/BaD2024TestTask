package statistics;

public class Main {
    public static void main(String[] args) {
        if (args.length == 1) {
            String fileName = args[0];
            StatisticOfFile statisticOfFile = new StatisticOfFile(fileName);
            statisticOfFile.printStats();
        } else {
            System.out.println("No file was found.");
        }
    }
}