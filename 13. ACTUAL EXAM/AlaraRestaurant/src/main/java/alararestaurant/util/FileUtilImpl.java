package alararestaurant.util;

import java.io.*;

public class FileUtilImpl implements FileUtil {

    @Override
    public String readFile(String filePath) throws IOException {
        File file = new File(filePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));

        StringBuilder sb = new StringBuilder();

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line).append(System.lineSeparator());
        }

        return sb.toString();

    }
}
