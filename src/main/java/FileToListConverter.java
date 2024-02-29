import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileToListConverter {
    public List<Long> getListFromFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        List<Long> allNumbers = new ArrayList<>();

        try (InputStream stream = classLoader.getResourceAsStream(fileName);
             InputStreamReader streamReader = new InputStreamReader(stream);
             BufferedReader reader = new BufferedReader(streamReader))
        {
            String line;
            while ((line = reader.readLine()) != null) {
                allNumbers.add(Long.parseLong(line));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return allNumbers;
    }
}
