package view;

import controller.dto.PenaltyCrewDto;
import domain.AttendanceStatus;
import domain.Date;
import domain.DateTime;
import domain.Penalty;
import domain.Time;
import domain.WorkDay;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OutputView {
    public void printArriveResult(DateTime dateTime, AttendanceStatus attendanceStatus) {
        Date date = dateTime.getDate();
        WorkDay workDay = date.getWorkDay();
        Time time = dateTime.getTime();

        System.out.printf("%s월 %s일 %s요일 %s:%s (%s)\n", date.getMonthValue(),
                date.getDayValue(), workDay.getDayOfWeekKorean(),
                convertTime(time.getHour()), convertTime(time.getMinute()), attendanceStatus.getName());
    }

    public void printUpdateResult(DateTime beforeDateTime, AttendanceStatus beforeAttendanceStatus,
                                  DateTime afterDateTime, AttendanceStatus afterAttendanceStatus) {
        Date date = beforeDateTime.getDate();
        WorkDay workDay = date.getWorkDay();
        Time beforeTime = beforeDateTime.getTime();
        Time afterTime = afterDateTime.getTime();
        System.out.printf("%s월 %s일 %s요일 %s:%s (%s) -> %s:%s (%s) 수정 완료!\n",
                date.getMonthValue(), date.getDayValue(), workDay.getDayOfWeekKorean(),
                convertTime(beforeTime.getHour()),
                convertTime(beforeTime.getMinute()), beforeAttendanceStatus.getName(),
                convertTime(afterTime.getHour()),
                convertTime(afterTime.getMinute()), afterAttendanceStatus.getName());
    }

    private String convertTime(Integer time) {
        if (time == null) {
            return "--";
        }

        String before = String.valueOf(time);

        if (before.length() < 2) {
            return "0" + before;
        }

        return before;
    }

    public void printTotalAttendanceStatus(List<DateTime> dateTimes) {
        dateTimes = new ArrayList<>(dateTimes);
        Collections.sort(dateTimes);
        List<AttendanceStatus> attendanceStatuses = new ArrayList<>();

        dateTimes.forEach(dateTime -> {
            AttendanceStatus attendanceStatus = AttendanceStatus.findByDateTime(dateTime);
            attendanceStatuses.add(attendanceStatus);
            printArriveResult(dateTime, attendanceStatus);
        });

        Map<AttendanceStatus, Integer> attendanceStatusCount = AttendanceStatus.calculateAttendanceStatusCount(
                attendanceStatuses);
        attendanceStatusCount.forEach((attendanceStatus, count) -> {
            System.out.printf("%s: %d회\n", attendanceStatus.getName(), count);
        });

        Penalty penalty = Penalty.calculatePenalty(attendanceStatuses);
        if (penalty != null) {
            System.out.printf("%s 대상자입니다.\n", penalty.getName());
        }
    }

    public void printPenaltyCrews(List<PenaltyCrewDto> penaltyCrewDtos) {
        System.out.println("제적 위험자 조회 결과");
        penaltyCrewDtos.forEach(penaltyCrewDto -> {
            System.out.printf("- %s: 결석 %d회, 지각 %d회 (%s)\n",
                    penaltyCrewDto.name(), penaltyCrewDto.absenceCount(),
                    penaltyCrewDto.perceptionCount(), penaltyCrewDto.penaltyName());
        });
    }
}
