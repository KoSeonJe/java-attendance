package domain;

import java.time.LocalDateTime;
import java.util.List;

public class Attendance {
    private final String name;
    private final List<LocalDateTime> dateTimes;

    public Attendance(String name, List<LocalDateTime> dateTimes) {
        this.name = name;
        this.dateTimes = dateTimes;
    }

    public String getName() {
        return name;
    }

    public List<LocalDateTime> getDateTimes() {
        return dateTimes;
    }

    public void addAttendance(LocalDateTime dateTime) {
        dateTimes.forEach(time -> {
            if (isEqualsDate(dateTime, time)) {
                throw new IllegalArgumentException("해당 이름의 출석 정보가 이미 존재합니다.");
            }
        });
    }

    private boolean isEqualsDate(LocalDateTime dateTime, LocalDateTime time) {
        return time.getYear() == dateTime.getYear() && time.getMonth() == dateTime.getMonth()
                && time.getDayOfMonth() == dateTime.getDayOfMonth();
    }
}
