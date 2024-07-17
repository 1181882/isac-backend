package backend.isac.model.enums;

public enum EAutomationType {
    THIN_CLIENT("Thin Client"),
    THICK_CLIENT("Thick Client");

    private String value;

    EAutomationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
