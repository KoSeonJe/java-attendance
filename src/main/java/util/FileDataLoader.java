package util;

import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileDataLoader {

    private FileDataLoader() {
    }

    public static List<String> loadLines(String filePath) {
        try {
            return Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException("[ERROR] 파일을 읽던 중 오류가 발생하였습니다");
        }
    }
}
