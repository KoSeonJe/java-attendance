import domain.CrewAttendanceBook;

public class Applicaiton {

    public static void main(String[] args) {
        AttendanceConfig attendanceConfig = new AttendanceConfig();

        DataInitializer dataInitializer = attendanceConfig.dataInitializer();
        AttendanceApplication attendanceApplication = attendanceConfig.attendanceApplication(
                dataInitializer.initCrewAttendanceBook()
        );

        attendanceApplication.execute();
    }
}
