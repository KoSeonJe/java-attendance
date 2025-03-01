public class CrewPenaltyManager {

    public int calculateTotalAbsence(int late, int absence) {
        int convertAbsence = late / 3;
        return absence + convertAbsence;
    }
}
