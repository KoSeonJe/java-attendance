package view;

import controller.dto.AfterAttendanceDto;
import controller.dto.AttendanceRecodeDto;
import controller.dto.AttendanceResultDto;
import controller.dto.BeforeAttendanceDto;
import controller.dto.PenaltyCrewDto;
import domain.Date;
import domain.AttendanceDateTime;
import domain.Penalty;
import domain.Time;
import domain.WorkDay;
import java.util.List;
import java.util.Objects;

public class OutputView {

    public void printArriveResult(AttendanceDateTime attendanceDateTime, String attendanceStatusName) {
        Date date = attendanceDateTime.getDate();
        WorkDay workDay = date.getWorkDay();
        Time time = attendanceDateTime.getTime();

        System.out.printf("%s월 %s일 %s요일 %s (%s)\n", date.getMonthValue(),
                date.getDayValue(), workDay.getDayOfWeekKorean(),
                convertTime(time), attendanceStatusName);
    }

    public void printUpdateResult(BeforeAttendanceDto beforeAttendanceDto, AfterAttendanceDto afterAttendanceDto) {
        Date date = beforeAttendanceDto.attendanceDateTime().getDate();
        WorkDay workDay = date.getWorkDay();
        Time beforeTime = beforeAttendanceDto.attendanceDateTime().getTime();
        Time afterTime = afterAttendanceDto.attendanceDateTime().getTime();
        System.out.printf("%s월 %s일 %s요일 %s (%s) -> %s (%s) 수정 완료!\n",
                date.getMonthValue(), date.getDayValue(), workDay.getDayOfWeekKorean(),
                convertTime(beforeTime), beforeAttendanceDto.attendanceStatus().getName(),
                convertTime(afterTime), afterAttendanceDto.attendanceStatus().getName());
    }

    public void printTotalAttendanceStatus(List<AttendanceRecodeDto> attendanceRecodeDto,
            AttendanceResultDto attendanceResultDto) {
        System.out.printf("이번 달 %s의 출석 기록입니다.\n", attendanceResultDto.name());
        attendanceRecodeDto.forEach(attendanceRecode -> {
            Date date = attendanceRecode.attendanceDateTime().getDate();
            WorkDay workDay = date.getWorkDay();
            Time time = attendanceRecode.attendanceDateTime().getTime();

            System.out.printf("%s월 %s일 %s요일 %s (%s)\n", date.getMonthValue(),
                    date.getDayValue(), workDay.getDayOfWeekKorean(),
                    convertTime(time),
                    attendanceRecode.attendanceStatusName());
        });

        System.out.println();
        System.out.printf("출석: %d회\n", attendanceResultDto.attendanceCount());
        System.out.printf("지각: %d회\n", attendanceResultDto.perceptionCount());
        System.out.printf("결석: %d회\n", attendanceResultDto.absenceCount());

        if (!Objects.equals(attendanceResultDto.penaltyName(), Penalty.NONE.getName())) {
            System.out.printf("%s 대상자입니다.\n", attendanceResultDto.penaltyName());
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

    public void printMessage(String message) {
        System.out.println(message);
    }

    private String convertTime(Time time) {
        if (time == null) {
            return "--:--";
        }
        return convertTime(time.getHour()) + ":" + convertTime(time.getMinute());
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
}
