package carshop.util;

import java.io.*;

public class FileUtilImpl implements FileUtil {
    @Override
    public String readFile(String filePath) throws IOException {
        File file = new File(filePath);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        StringBuilder stringBuilder = new StringBuilder();

        String line;

        while ((line = bufferedReader.readLine())!= null){
            stringBuilder.append(line).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
