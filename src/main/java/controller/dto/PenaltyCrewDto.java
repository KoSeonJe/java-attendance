package controller.dto;

public record PenaltyCrewDto(
        String name,
        int absenceCount,
        int perceptionCount,
        String penaltyName
) {
}
