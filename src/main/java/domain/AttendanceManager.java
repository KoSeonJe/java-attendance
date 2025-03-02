package domain;

import java.time.LocalDate;
import java.util.List;

public record AttendanceManager(
        CrewAttendanceBook crewAttendanceBook
) {

    public void processAttendance(String crewName, LocalDate attendanceDate, AttendanceTime attendanceTime) {
        SchoolDay.validateSchoolDay(attendanceDate);
        if (!crewAttendanceBook.existCrew(crewName)) {
            Attendance attendance = createAttendance(attendanceDate, attendanceTime);
            crewAttendanceBook.createCrewAttendance(crewName, attendance);
            return;
        }
        AttendanceRecords attendanceRecords = crewAttendanceBook.retrieveAttendanceRecordsByName(crewName);
        Attendance attendance = createAttendance(attendanceDate, attendanceTime);
        attendanceRecords.add(attendance);
    }

    public List<Attendance> retrieveFilledAttendanceUntilDate(String crewName, LocalDate todayDate) {
        AttendanceRecords attendanceRecords = crewAttendanceBook.retrieveAttendanceRecordsByName(crewName);

        LocalDate oldestDayInBook = crewAttendanceBook.retrieveOldestDayInBook();
        List<Attendance> filledAttendances = attendanceRecords.retrieveAllFilledNonExistingDay(oldestDayInBook, todayDate);

        filledAttendances.removeIf(attendance -> !SchoolDay.isPossibleAttendance(attendance.getAttendanceDate()));

        return filledAttendances;
    }

    private Attendance createAttendance(LocalDate attendanceDate, AttendanceTime attendanceTime) {
        int startHour = SchoolDay.retrieveStartHourByDate(attendanceDate);
        AttendanceStatus attendanceStatus = AttendanceStatus.findByStartHourAndAttendanceTime(startHour, attendanceTime);
        return Attendance.create(attendanceDate, attendanceTime, attendanceStatus);
    }
}
