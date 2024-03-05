package statistics;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileToListConverter {
    public List<Long> getListFromFile(String fileName) {
        List<Long> allNumbers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                allNumbers.add(Long.parseLong(line));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return allNumbers;
    }
}
