package statistics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that converts a file containing numbers to a list of long values.
 */
public class FileToListConverter {

    /**
     * Convert the file which contains numbers to List with Long values.
     *
     * @param fileName the name of the file containing numbers.
     * @return list of Long values converted from the file.
     * @throws FileNotFoundException if the file not found.
     * @throws NumberFormatException if the file contains anything other than numbers
     * @throws IOException           if an error occurs while reading the file
     */
    public List<Long> getListFromFile(String fileName) throws IOException {
        List<Long> allNumbers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                allNumbers.add(Long.parseLong(line));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            throw e;
        } catch (NumberFormatException e) {
            System.out.println("Exception parsing to Long " + e.getMessage());
            throw e;
        }

        return allNumbers;
    }
}
