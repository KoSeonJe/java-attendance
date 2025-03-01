package util;

import domain.CrewAttendanceBook;
import java.util.List;

public class DataInitializer {

    private final FileLoader fileLoader;

    public DataInitializer(FileLoader fileLoader) {
        this.fileLoader = fileLoader;
    }

    public CrewAttendanceBook initCrewAttendanceBook(String filePath) {
        List<String> rawAttendanceInfo = fileLoader.loadLines(filePath);
        return null;
    }
}
