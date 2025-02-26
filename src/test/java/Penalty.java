public enum Penalty {
    WARNING,
    INTERVIEW,
    WEEDING;

    private static final int WEEDING_LIMIT_COUNT = 5;
    private static final int INTERVIEW_LIMIT_COUNT = 3;

    public static Penalty judge(int absenceCount) {
        if (absenceCount > WEEDING_LIMIT_COUNT) {
            return WEEDING;
        }

        if (absenceCount >= INTERVIEW_LIMIT_COUNT) {
            return INTERVIEW;
        }
        return null;
    }
}
