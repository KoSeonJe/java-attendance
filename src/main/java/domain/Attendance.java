package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Attendance {
    private final List<LocalDateTime> dateTimes;

    public Attendance(List<LocalDateTime> dateTimes) {
        this.dateTimes = new ArrayList<>(dateTimes);
    }

    public void addAttendance(LocalDateTime dateTime) {
        if (isAlreadyExists(dateTime)) {
            throw new IllegalArgumentException("해당 날짜의 출석 정보가 이미 존재합니다.");
        }
        dateTimes.add(dateTime);
    }

    private boolean isAlreadyExists(LocalDateTime dateTime) {
        return dateTimes.stream().anyMatch(time -> isEqualsDate(dateTime, time));
    }

    public LocalDateTime updateAttendance(LocalDateTime updateDateTime) {
        int index = IntStream.range(0, dateTimes.size())
                .filter(i -> isEqualsDate(updateDateTime, dateTimes.get(i)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 날짜의 출석 정보가 없습니다."));

        LocalDateTime beforeTime = dateTimes.get(index);
        dateTimes.set(index, updateDateTime);

        return beforeTime;
    }

    private boolean isEqualsDate(LocalDateTime dateTime, LocalDateTime time) {
        return time.getYear() == dateTime.getYear() &&
                time.getMonth() == dateTime.getMonth() &&
                time.getDayOfMonth() == dateTime.getDayOfMonth();
    }

    public List<LocalDateTime> getDateTimesUntilDate(LocalDateTime dateTime) {
        return dateTimes.stream()
                .filter(time -> time.isBefore(dateTime) || time.isEqual(dateTime))
                .toList();
    }

    public List<LocalDateTime> getDateTimes() {
        return new ArrayList<>(dateTimes);
    }
}
