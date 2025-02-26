public enum Penalty {
    INTERVIEW,
    WARNING,
    WEEDING;

    private static final int WEEDING_LIMIT_COUNT = 5;

    public static Penalty judge(int absenceCount) {
        if (absenceCount > WEEDING_LIMIT_COUNT) {
            return WEEDING;
        }
        return null;
    }
}
