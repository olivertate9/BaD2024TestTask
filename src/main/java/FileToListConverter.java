import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
