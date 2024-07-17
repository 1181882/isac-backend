package backend.isac.model.enums;

public enum EImpact {
    LOW("Low"),
    MODERATE("Moderate "),
    HIGH("High");

    private String value;

    EImpact(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
