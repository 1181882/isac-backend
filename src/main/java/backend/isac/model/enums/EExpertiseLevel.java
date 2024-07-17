package backend.isac.model.enums;

public enum EExpertiseLevel {
    BEGINNER("Beginner"),
    INTERMEDIATE("Intermediate"),
    ADVANCED("Advanced");

    private String value;

    EExpertiseLevel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
